package com.ApiClima.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BucketConfig {

    @Bean
    public Bucket bucket() {
        Bandwidth limit = Bandwidth.classic(10, Refill.intervally(10, Duration.ofDays(1)));
        return Bucket.builder().addLimit(limit).build();
    }
}
