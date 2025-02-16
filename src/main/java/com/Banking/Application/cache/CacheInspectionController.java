package com.Banking.Application.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to inspect cache details in the Spring Boot application.
 * Provides an endpoint to retrieve the available cache names.
 */
@RestController
public class CacheInspectionController {

    @Autowired
    private CacheManager cacheManager;

    /**
     * Retrieves the available cache names managed by the CacheManager.
     *
     * @return A string containing the list of cache names.
     */
    @GetMapping("/cache-info")
    public String cache() {
        return "Available cache: " + cacheManager.getCacheNames();
    }
}