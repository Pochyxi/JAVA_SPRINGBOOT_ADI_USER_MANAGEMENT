package com.adi.usermanagement.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "app.security")
public class SecurityProperties {

    private Apikey apikey;

    @Setter
    @Getter
    public static class Apikey {

        private String read;

        private String write;

        private Endpoint endpoint;

        @Getter
        @Setter
        public static class Endpoint {

            private String base;
        }
    }
}
