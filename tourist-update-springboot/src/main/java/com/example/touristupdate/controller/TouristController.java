package com.example.touristupdate.controller;

import com.example.touristupdate.model.Tourist;
import com.example.touristupdate.repository.TouristRepository;
import com.example.touristupdate.service.TouristService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {
    private final TouristService service;
    private final TouristRepository repository;

    // Demo token for this lab. Replace with real JWT verification in production.
    private static final String VALID_TOKEN = "demo-valid-token";

    public TouristController(TouristService service, TouristRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTourist(
            @PathVariable Long id,
            @RequestHeader(value = "Token", required = false) String token,
            @Valid @RequestBody TouristRequest request) {

        if (token == null || !VALID_TOKEN.equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or missing token"));
        }

        Tourist updated = new Tourist(
                request.name(), request.visitedDestination(), request.contactDetails());
        Tourist saved = service.updateTourist(id, updated);
        return ResponseEntity.ok(Map.of(
                "message", "Tourist Updated Successfully",
                "id", saved.getId(),
                "name", saved.getName(),
                "visitedDestination", saved.getVisitedDestination(),
                "contactDetails", saved.getContactDetails()
        ));
    }

    // Convenience endpoint to create sample records for testing the PUT request.
    @PostMapping
    public ResponseEntity<Tourist> createTourist(@Valid @RequestBody TouristRequest request) {
        Tourist tourist = new Tourist(request.name(), request.visitedDestination(), request.contactDetails());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tourist));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tourist> getTourist(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public record TouristRequest(
            @NotBlank String name,
            @NotBlank String visitedDestination,
            @NotBlank String contactDetails) {}
}
