package com.Banking.Application.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheInspectionController {

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/cache-info")
    public String cacheInfo() {
        return "Available caches: " + cacheManager.getCacheNames();
    }
}
