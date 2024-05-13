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
    public List<Street> getStreets() {
        return streetRepository.findAll();
    }

    @GetMapping("/streets/{streetId}")
    public ResponseEntity<Street> getStreetById(@PathVariable("streetId") Integer streetId) {
        try {
            Optional<Street> street = streetRepository.findById(streetId);
            if (street.isPresent()) {
                return ResponseEntity.ok(street.get());
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
