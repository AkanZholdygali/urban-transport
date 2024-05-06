package kz.akan.springcourse.urbantransport.controller;

import kz.akan.springcourse.urbantransport.model.Transport;
import kz.akan.springcourse.urbantransport.model.TransportType;
import kz.akan.springcourse.urbantransport.repository.TransportRepository;
import kz.akan.springcourse.urbantransport.repository.TransportTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<Transport>> getAllTransports(@RequestParam(required = false) String searchText, @RequestParam(required = false) String typeTitle) {
        try {
            List<Transport> transports = new ArrayList<>();

            if (searchText != null && !searchText.isEmpty() && typeTitle != null && !typeTitle.isEmpty()) {
                // Если присутствуют оба параметра, выполняем фильтрацию по обоим
                transports = transportRepository.findByLicensePlateNoContainingIgnoreCaseAndType_Title(searchText, typeTitle);
            } else if (searchText != null && !searchText.isEmpty()) {
                // Если присутствует только searchText, выполняем фильтрацию по нему
                transports = transportRepository.findByLicensePlateNoContainingIgnoreCase(searchText);
            } else if (typeTitle != null && !typeTitle.isEmpty()) {
                // Если присутствует только typeTitle, выполняем фильтрацию по нему
                transports = transportRepository.findByType_Title(typeTitle);
            } else {
                // Если ни один из параметров не указан, получаем все транспорты
                transports = transportRepository.findAll();
            }
            if (transports.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(transports);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/transports/{id}")
    public ResponseEntity<Transport> getTransportById(@PathVariable String id) {
        Optional<Transport> optionalTransport = transportRepository.findById(id);
        if (optionalTransport.isEmpty()) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(optionalTransport.get());
    }


    @GetMapping("/types")
    public List<TransportType> getAllTransportTypes() {
        return transportTypeRepository.findAll();
    }

    @GetMapping("/types/{typeTitle}/transports")
    public ResponseEntity<List<Transport>> getTransportsByType(@PathVariable String  typeTitle) {
        Optional<List<Transport>> optionalTransports = transportRepository.findByTypeTitle(typeTitle);
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

    @PutMapping("/transports/{licensePlateNo}")
    public ResponseEntity<Transport> updateTransport(@PathVariable String licensePlateNo, @RequestBody Transport transport) {
        Optional<Transport> existingTransport = transportRepository.findById(licensePlateNo);
        if (existingTransport.isPresent()) {
            Transport transportToUpdate = existingTransport.get();
            try {
                transportToUpdate.setLicensePlateNo(transport.getLicensePlateNo());
                transportToUpdate.setManufactureYear(transport.getManufactureYear());
                transportToUpdate.setNumRepairs(transport.getNumRepairs());
                return ResponseEntity.ok(transportRepository.save(transportToUpdate));
            }catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/transports/{licensePlateNo}")
    public ResponseEntity<?> deleteTransport(@PathVariable String licensePlateNo) {
        Optional<Transport> optionalTransport = transportRepository.findById(licensePlateNo);
        if (optionalTransport.isPresent()) {
            transportRepository.delete(optionalTransport.get());
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.notFound().build();
    }
}
