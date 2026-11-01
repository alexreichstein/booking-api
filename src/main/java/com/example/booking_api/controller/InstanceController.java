package com.example.booking_api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// En egen controller för instans-information,
// eftersom den inte har med bokningar att göra
@RestController
public class InstanceController {

    // @Value hämtar inställningen app.instance-name från application.properties.
    // Den i sin tur kommer från miljövariabeln INSTANCE_NAME (t.ex. "api-1").
    @Value("${app.instance-name}")
    private String instanceName;

    // GET /instance
    // Returnerar vilken instans som svarar, t.ex. {"instance": "api-1"}.
    // En Map blir automatiskt ett JSON-objekt: nyckeln blir fältnamnet.
    @GetMapping("/instance")
    public Map<String, String> getInstance() {
        return Map.of("instance", instanceName);
    }
}