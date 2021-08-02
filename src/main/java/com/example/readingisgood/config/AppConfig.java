package com.example.readingisgood.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class AppConfig {

    private Api api = new Api();

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public class Api {
        private String allowedOrigin;
        private String secretKey;
        private long validity;

        public String getAllowedOrigin() {
            return allowedOrigin;
        }

        public void setAllowedOrigin(String allowedOrigin) {
            this.allowedOrigin = allowedOrigin;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public long getValidity() {
            return validity;
        }

        public void setValidity(long validity) {
            this.validity = validity;
        }
    }

}