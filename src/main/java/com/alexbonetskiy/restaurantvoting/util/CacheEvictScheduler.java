package com.alexbonetskiy.restaurantvoting.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//https://stackoverflow.com/a/42688520

@Component
@RequiredArgsConstructor
@Slf4j
public class CacheEvictScheduler {
    @Scheduled(cron = "@midnight")
    @CacheEvict(value = "Restaurants", allEntries = true)
    public void clearCache() {
        log.info("Evict restaurants cache at midnight");
    }

}
