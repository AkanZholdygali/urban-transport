package kz.akan.springcourse.urbantransport.services;

import kz.akan.springcourse.urbantransport.models.Stop;
import kz.akan.springcourse.urbantransport.repositories.StopRepository;
import kz.akan.springcourse.urbantransport.specifications.StopSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopService {

    private final StopRepository stopRepository;

    public StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }

    public Optional<Stop> getStopById(Integer id) {
        return stopRepository.findById(id);
    }

    public Stop saveStop(Stop stop) {
        return stopRepository.save(stop);
    }

    public Stop updateStop(Integer id, Stop stopDetails) {
        return stopRepository.findById(id)
                .map(stop -> {
                    stop.setStreet(stopDetails.getStreet());
                    stop.setName(stopDetails.getName());
                    return stopRepository.save(stop);
                }).orElseThrow(() -> new RuntimeException("Stop not found"));
    }

    public void deleteStop(Integer id) {
        stopRepository.deleteById(id);
    }

    public List<Stop> searchStops(String searchText) {
        Specification<Stop> specification = Specification
                .where(StopSpecification.hasNameContaining(searchText))
                .or(StopSpecification.hasStreetNameContaining(searchText));
        return stopRepository.findAll(specification);
    }
}
