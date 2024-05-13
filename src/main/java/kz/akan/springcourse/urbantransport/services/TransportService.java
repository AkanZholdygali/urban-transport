package kz.akan.springcourse.urbantransport.services;

import kz.akan.springcourse.urbantransport.models.Transport;
import kz.akan.springcourse.urbantransport.repositories.TransportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportService {

    private final TransportRepository transportRepository;

    public TransportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    public List<Transport> getAllTransports() {
        return transportRepository.findAll();
    }

    public Optional<Transport> getTransportById(String licensePlateNo) {
        return transportRepository.findById(licensePlateNo);
    }

    public Transport saveTransport(Transport transport) {
        return transportRepository.save(transport);
    }

    public Transport updateTransport(String licensePlateNo, Transport transportDetails) {
        return transportRepository.findById(licensePlateNo)
                .map(transport -> {
                    transport.setManufactureYear(transportDetails.getManufactureYear());
                    transport.setNumRepairs(transportDetails.getNumRepairs());
                    transport.setIsFunctional(transportDetails.getIsFunctional());
                    transport.setType(transportDetails.getType());
                    transport.setRouteNo(transportDetails.getRouteNo());
                    return transportRepository.save(transport);
                }).orElseThrow(() -> new RuntimeException("Transport not found"));
    }

    public void deleteTransport(String licensePlateNo) {
        transportRepository.deleteById(licensePlateNo);
    }

    //custom methods
    public List<Transport> getTransportsByLicensePlateNo(String licensePlateNo) {
        return transportRepository.findByLicensePlateNoContainingIgnoreCase(licensePlateNo);
    }

    public List<Transport> getTransportsByTypeTitle(String typeTitle) {
        return transportRepository.findByType_Title(typeTitle);
    }

    public List<Transport> getTransportsByLicensePlateNoAndTypeTitle(String licensePlateNo, String typeTitle) {
        return transportRepository.findByLicensePlateNoContainingIgnoreCaseAndType_Title(licensePlateNo, typeTitle);
    }


}

