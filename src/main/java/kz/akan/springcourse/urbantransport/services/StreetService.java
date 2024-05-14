package kz.akan.springcourse.urbantransport.services;

import kz.akan.springcourse.urbantransport.models.Street;
import kz.akan.springcourse.urbantransport.repositories.StreetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreetService {

    private final StreetRepository streetRepository;

    public StreetService(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

    public List<Street> getAllStreets() {
        return streetRepository.findAll();
    }

    public Optional<Street> getStreetById(Integer id) {
        return streetRepository.findById(id);
    }

    public Street saveStreet(Street street) {
        return streetRepository.save(street);
    }

    public Street updateStreet(Integer id, Street streetDetails) {
        return streetRepository.findById(id)
                .map(street -> {
                    street.setName(streetDetails.getName());
                    return streetRepository.save(street);
                }).orElseThrow(() -> new RuntimeException("Street not found"));
    }

    public void deleteStreet(Integer id) {
        streetRepository.deleteById(id);
    }
}