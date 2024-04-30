package kz.akan.springcourse.urbantransport.controller;


import kz.akan.springcourse.urbantransport.model.Stop;
import kz.akan.springcourse.urbantransport.repository.StopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StopController {

    private final StopRepository stopRepository;
    public StopController(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    @GetMapping("/stops")
    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }
}
