package kz.akan.springcourse.urbantransport.controllers;

import kz.akan.springcourse.urbantransport.models.Street;
import kz.akan.springcourse.urbantransport.repositories.StreetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class StreetController {
    StreetRepository streetRepository;
    public StreetController(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

    @GetMapping("/streets")
    public ResponseEntity<List<Street>> getAllStreets() {
        try {
            List<Street> streets = streetRepository.findAll();
            if (streets.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(streets);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/streets/{streetId}")
    public ResponseEntity<Street> getStreetById(@PathVariable("streetId") Integer streetId) {
        try {
            Optional<Street> street = streetRepository.findById(streetId);
            return street.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
