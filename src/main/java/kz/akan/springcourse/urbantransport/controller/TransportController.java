package kz.akan.springcourse.urbantransport.controller;

import kz.akan.springcourse.urbantransport.model.Transport;
import kz.akan.springcourse.urbantransport.model.TransportType;
import kz.akan.springcourse.urbantransport.repository.TransportRepository;
import kz.akan.springcourse.urbantransport.repository.TransportTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransportController {
    private TransportRepository transportRepository;
    private TransportTypeRepository transportTypeRepository;

    public TransportController(TransportRepository transportRepository, TransportTypeRepository transportTypeRepository) {
        this.transportRepository = transportRepository;
        this.transportTypeRepository = transportTypeRepository;
    }

    @GetMapping("/transports")
    public List<Transport> getAllTransports() {
        return transportRepository.findAll();
    }

    @GetMapping("/types")
    public List<TransportType> getAllTransportTypes() {
        return transportTypeRepository.findAll();
    }
}
