package kz.akan.springcourse.urbantransport.controller;

import kz.akan.springcourse.urbantransport.model.Route;
import kz.akan.springcourse.urbantransport.repository.RouteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RouteController {
    private RouteRepository routeRepository;
    public RouteController(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @GetMapping("routes")
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
}
