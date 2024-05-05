package kz.akan.springcourse.urbantransport.controller;

import kz.akan.springcourse.urbantransport.model.Transport;
import kz.akan.springcourse.urbantransport.model.TransportType;
import kz.akan.springcourse.urbantransport.repository.TransportRepository;
import kz.akan.springcourse.urbantransport.repository.TransportTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
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

    @GetMapping("/transports/{id}")
    public ResponseEntity<Transport> getTransportById(@PathVariable String id) {
        Optional<Transport> optionalTransport = transportRepository.findById(id);
        if (optionalTransport.isPresent()) return ResponseEntity.ok(optionalTransport.get());
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/types")
    public List<TransportType> getAllTransportTypes() {
        return transportTypeRepository.findAll();
    }

    @GetMapping("/types/{id}/transports")
    public ResponseEntity<List<Transport>> getTransportsByType(@PathVariable Integer id) {
        Optional<List<Transport>> optionalTransports = transportRepository.findByTypeId(id);
        if (optionalTransports.isPresent()) {
            return ResponseEntity.ok(optionalTransports.get());
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/transports")
    public ResponseEntity<?> createTransport(@RequestBody Transport transport) {
        try {
            Optional<Transport> existingTransport = transportRepository.findById(transport.getLicensePlateNo());
            if (existingTransport.isPresent()) {
                return ResponseEntity.badRequest().build();
            } else {
                transportRepository.save(transport);
                return ResponseEntity.ok().build();
            }
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PutMapping("/transports/{id}")
    public ResponseEntity<Transport> updateTransport(@PathVariable String id, @RequestBody Transport transport) {
        Optional<Transport> existingTransport = transportRepository.findById(id);
        if (existingTransport.isPresent()) {
            Transport transportToUpdate = existingTransport.get();
            try {
                transportToUpdate.setLicensePlateNo(transport.getLicensePlateNo());
                transportToUpdate.setType(transport.getType());
                transportToUpdate.setRoutes(transport.getRoutes());
                transportToUpdate.setManufactureYear(transport.getManufactureYear());
                transportToUpdate.setNumRepairs(transport.getNumRepairs());
                return ResponseEntity.ok(transportRepository.save(transportToUpdate));
            }catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        }
        else return ResponseEntity.notFound().build();
    }

}
