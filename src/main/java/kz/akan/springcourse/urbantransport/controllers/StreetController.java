package kz.akan.springcourse.urbantransport.controllers;

import kz.akan.springcourse.urbantransport.models.Street;
import kz.akan.springcourse.urbantransport.repositories.StreetRepository;
import kz.akan.springcourse.urbantransport.services.StreetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/streets")
@CrossOrigin("*")
public class StreetController {

    private final StreetService streetService;

    public StreetController(StreetService streetService) {
        this.streetService = streetService;
    }

    @GetMapping
    public ResponseEntity<List<Street>> getAllStreets(@RequestParam(required = false) String searchText) {
        try {
            List<Street> streets;
            if (searchText != null && !searchText.isEmpty()) {
                streets = streetService.searchStreets(searchText);
            } else {
                streets = streetService.getAllStreets();
            }
            if (streets.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(streets);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Street> getStreetById(@PathVariable Integer id) {
        Optional<Street> street = streetService.getStreetById(id);
        return street.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Street> createStreet(@RequestBody Street street) {
        try {
            Street savedStreet = streetService.saveStreet(street);
            return ResponseEntity.ok(savedStreet);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Street> updateStreet(@PathVariable Integer id, @RequestBody Street streetDetails) {
        try {
            Street updatedStreet = streetService.updateStreet(id, streetDetails);
            return ResponseEntity.ok(updatedStreet);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreet(@PathVariable Integer id) {
        try {
            streetService.deleteStreet(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
