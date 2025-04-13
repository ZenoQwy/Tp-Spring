package com.thomas.tp_spring.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    public void registerToArena() {
        String studentName = "Thomas";
        String baseUrl = "http://[IP_VOTRE_APP_HEROS]:[PORT]";
        String jsonPayload = String.format("{\"studentName\": \"%s\", \"baseUrl\": \"%s\"}", studentName, baseUrl);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        try {
            String arenaUrl = "http://51.210.251.137/arena/register";
            ResponseEntity<String> response = restTemplate.exchange(arenaUrl, HttpMethod.POST, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                logger.info("Enregistrement réussi auprès de l'Arène !");
            } else {
                logger.info("Erreur lors de l'enregistrement sur l'Arène : {}", response.getStatusCode());
            }
            checkHealth();

        } catch (Exception e) {
            logger.info("Erreur lors de l'appel au serveur Arène : {}", e.getMessage());
        }
    }

    private void checkHealth() {
        RestTemplate restTemplate = new RestTemplate();
        String healthUrl = "http://[IP_VOTRE_APP_HEROS]:[PORT]/actuator/health";

        try {
            ResponseEntity<String> response = restTemplate.exchange(healthUrl, HttpMethod.GET, null, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                logger.info("Le service est sain, prêt pour le combat !");
            } else {
                logger.info("Erreur de santé de l'application : {}", response.getStatusCode());
            }
        } catch (Exception e) {
            logger.info("Erreur lors de la vérification de la santé de l'application : {}", e.getMessage());
        }
    }
}
