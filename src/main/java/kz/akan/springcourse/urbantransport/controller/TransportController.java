package kz.akan.springcourse.urbantransport.controller;

import kz.akan.springcourse.urbantransport.model.Transport;
import kz.akan.springcourse.urbantransport.model.TransportType;
import kz.akan.springcourse.urbantransport.repository.TransportRepository;
import kz.akan.springcourse.urbantransport.repository.TransportTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TransportController {
    private final TransportRepository transportRepository;
    private final TransportTypeRepository transportTypeRepository;

    public TransportController(TransportRepository transportRepository, TransportTypeRepository transportTypeRepository) {
        this.transportRepository = transportRepository;
        this.transportTypeRepository = transportTypeRepository;
    }

    @GetMapping("/transports")
    public ResponseEntity<List<Transport>> getAllTransports() {
        try {
            List<Transport> transports = transportRepository.findAll();

            if (transports.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(transports);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/types")
    public List<TransportType> getAllTransportTypes() {
        return transportTypeRepository.findAll();
    }
}
