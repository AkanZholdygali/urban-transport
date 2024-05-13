package kz.akan.springcourse.urbantransport.controllers;


import kz.akan.springcourse.urbantransport.models.Stop;
import kz.akan.springcourse.urbantransport.repositories.StopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class StopController {

    private final StopRepository stopRepository;
    public StopController(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    @GetMapping("/stops")
    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }

    @GetMapping("/stops/{stopId}")
    public ResponseEntity<Stop> getStopById(@PathVariable Integer stopId) {
        try {
            Optional<Stop> stop = stopRepository.findById(stopId);
            if (stop.isPresent()) {
                return ResponseEntity.ok(stop.get());
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/stops/{stopId}")
    public ResponseEntity<?> deleteStopById(@PathVariable Integer stopId) {
        Optional<Stop> stop = stopRepository.findById(stopId);
        if (stop.isPresent()) {
            stopRepository.delete(stop.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
