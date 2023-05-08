package com.katia.spring.cookie;

import com.katia.spring.cookie.entities.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
public class UserOperations {
    private static final String BASE_URL = "http://94.198.50.185:7081/api/users";

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        // READ
        ResponseEntity<String> response1 = restTemplate.getForEntity(BASE_URL, String.class);
        String sessionId = response1.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        System.out.println(sessionId);

        // CREATE
        HttpHeaders headers2 = new HttpHeaders();
        headers2.set(HttpHeaders.COOKIE, sessionId);
        com.katia.spring.cookie.entities.User user = new User(3L, "James", "Brown", (byte) 30);
        HttpEntity<User> request2 = new HttpEntity<>(user, headers2);
        ResponseEntity<String> response2 = restTemplate.postForEntity(BASE_URL, request2, String.class);
        System.out.println(request2);

        // UPDATE
        User updatedUser = new User(3L, "Thomas", "Shelby", (byte) 30);
        HttpEntity<User> request3 = new HttpEntity<>(updatedUser, headers2);
        ResponseEntity<Void> response3 = restTemplate.exchange(BASE_URL, HttpMethod.PUT, request3, Void.class);
        System.out.println(request3);

        // DELETE
        HttpEntity<?> request4 = new HttpEntity<>(headers2);
        ResponseEntity<Void> response4 = restTemplate.exchange(BASE_URL + "/3", HttpMethod.DELETE, request4, Void.class);
        System.out.println(request4);

        // SECRET MESSAGE
        String code = response3.getStatusCode().toString() + response2.getStatusCode()
                + response4.getStatusCode();
        System.out.println("The code is: " + code);
    }
}



