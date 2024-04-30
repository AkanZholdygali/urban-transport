package kz.akan.springcourse.urbantransport.controller;

import kz.akan.springcourse.urbantransport.model.Street;
import kz.akan.springcourse.urbantransport.repository.StreetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StreetController {
    StreetRepository streetRepository;
    public StreetController(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

    @GetMapping("/streets")
    public List<Street> getStreets() {
        return streetRepository.findAll();
    }
}
