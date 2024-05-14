package kz.akan.springcourse.urbantransport.controllers;

import kz.akan.springcourse.urbantransport.models.Stop;
import kz.akan.springcourse.urbantransport.services.StopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stops")
@CrossOrigin(origins = "*")
public class StopController {

    private final StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @GetMapping
    public ResponseEntity<List<Stop>> getAllStops(@RequestParam(required = false) String searchText) {
        try {
            List<Stop> stops;
            if (searchText != null && !searchText.isEmpty()) {
                stops = stopService.searchStops(searchText);
            } else {
                stops = stopService.getAllStops();
            }
            if (stops.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(stops);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stop> getStopById(@PathVariable Integer id) {
        Optional<Stop> stop = stopService.getStopById(id);
        return stop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Stop> createStop(@RequestBody Stop stop) {
        try {
            Stop savedStop = stopService.saveStop(stop);
            return ResponseEntity.ok(savedStop);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stop> updateStop(@PathVariable Integer id, @RequestBody Stop stopDetails) {
        try {
            Stop updatedStop = stopService.updateStop(id, stopDetails);
            return ResponseEntity.ok(updatedStop);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStop(@PathVariable Integer id) {
        try {
            stopService.deleteStop(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
