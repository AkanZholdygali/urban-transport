package kz.akan.springcourse.urbantransport.controller;

import kz.akan.springcourse.urbantransport.model.Route;
import kz.akan.springcourse.urbantransport.repository.RouteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class RouteController {
    private final RouteRepository routeRepository;
    public RouteController(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @GetMapping("/routes")
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @GetMapping("/routes/{routeNo}")
    public ResponseEntity<Route> getRoute(@PathVariable String routeNo) {
        try {
            Optional<Route> route = routeRepository.findById(routeNo);
            if (route.isPresent()) {
                return ResponseEntity.ok(route.get());
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/routes/{routeNo}")
    public ResponseEntity<?> deleteRoute(@PathVariable String routeNo) {
        try {
            Optional<Route> route = routeRepository.findById(routeNo);
            if (route.isPresent()) {
                routeRepository.delete(route.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
