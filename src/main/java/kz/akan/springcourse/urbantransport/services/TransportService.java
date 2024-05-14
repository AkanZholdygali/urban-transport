package kz.akan.springcourse.urbantransport.services;

import kz.akan.springcourse.urbantransport.models.Transport;
import kz.akan.springcourse.urbantransport.repositories.TransportRepository;
import kz.akan.springcourse.urbantransport.specifications.TransportSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Transport updateTransport(String licensePlateNo, Transport transportDetails) {
        Transport existingTransport = transportRepository.findById(licensePlateNo).orElse(null);
        if (existingTransport != null) {
            if (transportRepository.existsById(transportDetails.getLicensePlateNo())) {
                return null;
            }
            deleteTransport(licensePlateNo);
            return saveTransport(transportDetails);
        }else
            return null;
    }

    public void deleteTransport(String licensePlateNo) {
        transportRepository.deleteById(licensePlateNo);
    }

    public List<Transport> getTransportsWithFilters(String searchText, Boolean busChecked, Boolean trolleybusChecked, Integer fromYear, Integer toYear, Boolean functional) {
        Specification<Transport> specification = Specification
                .where(TransportSpecification.hasLicensePlateNoContaining(searchText))
                .and(TransportSpecification.hasTransportTypes(busChecked, trolleybusChecked))
                .and(TransportSpecification.hasManufactureYearBetween(fromYear, toYear))
                .and(TransportSpecification.isFunctional(functional));
        return transportRepository.findAll(specification);
    }
}

