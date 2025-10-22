package com.example.studentmgr.web;

public class HealthController {
    @org.springframework.web.bind.annotation.RestController
    public static class HealthEndpoint {
        @org.springframework.web.bind.annotation.GetMapping(path = "/healthz")
        public org.springframework.http.ResponseEntity<String> health() {
            return org.springframework.http.ResponseEntity
                    .ok()
                    .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                    .body("{\"status\":\"ok\"}");
        }
    }
}
