package kz.akan.springcourse.urbantransport.controllers;

import kz.akan.springcourse.urbantransport.models.Route;
import kz.akan.springcourse.urbantransport.services.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public ResponseEntity<List<Route>> getAllRoutes(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) Boolean busChecked,
            @RequestParam(required = false) Boolean trolleybusChecked,
            @RequestParam(required = false) String time) {
        try {
            LocalTime localTime = time != null ? LocalTime.parse(time) : null;
            List<Route> routes = routeService.getRoutesWithFilters(searchText, busChecked, trolleybusChecked, localTime);
            if (routes.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(routes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/routes/{routeNo}")
    public ResponseEntity<Route> getRouteById(@PathVariable String routeNo) {
        Optional<Route> route = routeService.getRouteById(routeNo);
        return route.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/routes")
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        try {
            Route savedRoute = routeService.saveRoute(route);
            return ResponseEntity.ok(savedRoute);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/routes/{routeNo}")
    public ResponseEntity<Route> updateRoute(@PathVariable String routeNo, @RequestBody Route routeDetails) {
        try {
            Route updatedRoute = routeService.updateRoute(routeNo, routeDetails);
            if (updatedRoute == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(updatedRoute);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{routeNo}")
    public ResponseEntity<Void> deleteRoute(@PathVariable String routeNo) {
        try {
            routeService.deleteRoute(routeNo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}