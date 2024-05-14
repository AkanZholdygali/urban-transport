package kz.akan.springcourse.urbantransport.controllers;

import kz.akan.springcourse.urbantransport.models.Transport;
import kz.akan.springcourse.urbantransport.services.TransportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TransportController {
    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @GetMapping("/transports")
    public ResponseEntity<List<Transport>> getAllTransports(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) Boolean busChecked,
            @RequestParam(required = false) Boolean trolleybusChecked,
            @RequestParam(required = false) Integer fromYear,
            @RequestParam(required = false) Integer toYear,
            @RequestParam(required = false) Boolean functional) {
        try {
            List<Transport> transports = transportService.getTransportsWithFilters(searchText, busChecked, trolleybusChecked, fromYear, toYear, functional);
            if (transports.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(transports);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/transports/{licensePlateNo}")
    public ResponseEntity<Transport> getTransportById(@PathVariable String licensePlateNo) {
        Optional<Transport> transport = transportService.getTransportById(licensePlateNo);
        return transport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/transports")
    public ResponseEntity<Transport> createTransport(@RequestBody Transport transport) {
        try {
            Transport savedTransport = transportService.saveTransport(transport);
            return ResponseEntity.ok(savedTransport);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/transports/{licensePlateNo}")
    public ResponseEntity<Transport> updateTransport(@PathVariable String licensePlateNo, @RequestBody Transport transportDetails) {
        try {
            Transport updatedTransport = transportService.updateTransport(licensePlateNo, transportDetails);
            if (updatedTransport == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(updatedTransport);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/transports/{licensePlateNo}")
    public ResponseEntity<Void> deleteTransport(@PathVariable String licensePlateNo) {
        try {
            transportService.deleteTransport(licensePlateNo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
