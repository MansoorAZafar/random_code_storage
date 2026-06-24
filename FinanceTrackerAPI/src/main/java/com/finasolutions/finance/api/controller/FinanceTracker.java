package com.finasolutions.finance.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finasolutions.finance.api.dto.FinanicalResponse;
import com.finasolutions.finance.persistence.entity.FinanicalRecord;
import com.finasolutions.finance.service.FinanicalRecordService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/record")
public class FinanceTracker {
    
    private final FinanicalRecordService finanicalRecordService;

    public FinanceTracker(FinanicalRecordService finanicalRecordService) {
        this.finanicalRecordService = finanicalRecordService;
    }


    @GetMapping
    public ResponseEntity<FinanicalResponse> getRecords() {
        final FinanicalResponse response
            = new FinanicalResponse(
                200,
                this.finanicalRecordService.getAllRecords(),
                "Success"
            );
        
        return ResponseEntity
            .status(200)
            .body(response);
    }
    
    @PostMapping
    public ResponseEntity<FinanicalResponse> createRecord( @RequestBody FinanicalRecord entity ) {
        try {
            final FinanicalRecord record = this.finanicalRecordService.save(entity);
            final FinanicalResponse response 
                = new FinanicalResponse(200, record, "Successfully added record");

            return ResponseEntity
                .status(200)
                .body(response);        
        } catch (Exception e) {
            final FinanicalResponse response 
                = new FinanicalResponse(404, entity, "Entity cannot be null");
            
            return ResponseEntity
                .status(404)
                .body(response);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<FinanicalResponse> deleteRecord(@PathVariable long id) {
        try {
            this.finanicalRecordService.deleteById(id);
            final FinanicalResponse response 
                = new FinanicalResponse(200, true, "Successfully removed record: " + id);

            return ResponseEntity
                .status(200)
                .body(response);        
        } catch (Exception e) {
            final FinanicalResponse response 
                = new FinanicalResponse(404, id, "Entity with " + id + " does not exist");
            
            return ResponseEntity
                .status(404)
                .body(response);
        }
    }
}
