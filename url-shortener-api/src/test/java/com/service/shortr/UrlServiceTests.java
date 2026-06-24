package com.service.shortr;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.service.shortr.persistance.entity.Url;
import com.service.shortr.persistance.repository.UrlRepository;
import com.service.shortr.service.UrlService;

@SpringBootTest
@Import(TestCacheConfig.class)
public class UrlServiceTests {
    @MockitoBean
    private UrlRepository userRepository;

    @Autowired
    private UrlService urlService;

    @Test
    void validFindUrlById() {
        Url url = new Url("goo", "https://google.com", "tmp");
        Mockito.when( userRepository.findById("goo") ).thenReturn( Optional.of(url) );

        Url result = assertDoesNotThrow(() -> urlService.findById("goo"));
        assertEquals("goo", result.getHash());
    }

    @Test 
    void invalidFindUrlById() {
        Mockito.when(userRepository.findById("bar")).thenReturn(null);
        assertThrows(Exception.class, () -> urlService.findById("bar")); 
    }


    @Test
    void validDeleteById() {
        Url url = new Url("goo", "https://google.com", "tmp");
        Mockito.when(userRepository.existsById("goo")).thenReturn(true);
        Mockito.when(userRepository.findById("goo")).thenReturn(Optional.of(url));

        assertDoesNotThrow(() -> urlService.deleteById("goo"));
        
        Mockito.when(userRepository.existsById("goo")).thenReturn(false);
        Mockito.when(userRepository.findById("goo")).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> urlService.findById("goo")); 
    }

    @Test 
    void invalidDeleteById() {
        Mockito.when(userRepository.existsById("foo")).thenReturn(false);
        assertThrows(Exception.class, () -> urlService.deleteById("foo")); 
    }
}
