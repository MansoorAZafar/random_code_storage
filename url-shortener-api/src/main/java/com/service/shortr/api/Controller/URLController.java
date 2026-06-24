package com.service.shortr.api.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.shortr.api.DTO.ResponseRecord;
import com.service.shortr.api.DTO.URLRecord;
import com.service.shortr.persistance.entity.Url;
import com.service.shortr.service.UrlService;
import com.service.shortr.util.Hash;
import com.service.shortr.util.UrlUtility;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/url")
public class URLController {

    private UrlService urlService;
    
    public URLController(UrlService urlService) {
        this.urlService = urlService;
    }


    @PostMapping
    public ResponseRecord shortenURL(@RequestBody URLRecord record) {
        // Canonicalize the URL
        // - Remove the https:// and trailing /
        final String canonicalURL = UrlUtility.canonicalizeURL(record.url());
        final String uniqueId = record.alias() == null ? Hash.generateSHA256(canonicalURL) : record.alias();
        
        try {
            final Url url = new Url(uniqueId, record.url(), "root");
            this.urlService.save(url);
            
            ResponseRecord response = new ResponseRecord(true, uniqueId, null);
            return response;
        } catch (Exception e) {
 
            ResponseRecord response = new ResponseRecord(false, null, "alias already exists");
            return response;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> translateShortURL(@PathVariable String id) { 
        try {
            final Url record = this.urlService.findById(id);
            return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(record.getUrl()))
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();    
        }
    }

    @DeleteMapping("/{id}")
    public ResponseRecord deleteShortenedUrl(@PathVariable String id) {
        try {             
            this.urlService.deleteById(id);
            return new ResponseRecord(true, null, id + ": Record Deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseRecord(false, null, "No record found with: " + id + " id");
        }
    }

}
