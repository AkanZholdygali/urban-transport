package kz.akan.springcourse.urbantransport.controllers;

import kz.akan.springcourse.urbantransport.models.Route;
import kz.akan.springcourse.urbantransport.repositories.RouteRepository;
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

    @PostMapping("/routes")
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        try {
            List<Route> routes = routeRepository.findAll();
            if (routes.contains(route)) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(routeRepository.save(route));

        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/routes/{routeNo}")
    public ResponseEntity<?> deleteRoute(@PathVariable String routeNo) {
        Optional<Route> route = routeRepository.findById(routeNo);
        if (route.isPresent()) {
            routeRepository.delete(route.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
