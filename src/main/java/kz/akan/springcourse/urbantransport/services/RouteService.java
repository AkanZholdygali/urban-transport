package kz.akan.springcourse.urbantransport.services;

import kz.akan.springcourse.urbantransport.models.Route;
import kz.akan.springcourse.urbantransport.repositories.RouteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteById(String routeNo) {
        return routeRepository.findById(routeNo);
    }

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    @Transactional
    public Route updateRoute(String routeNo, Route routeDetails) {
        Optional<Route> optionalRoute = routeRepository.findById(routeNo);
        if (optionalRoute.isPresent()) {
            Route existingRoute = optionalRoute.get();
            existingRoute.setRouteNo(routeDetails.getRouteNo());
            existingRoute.setType(routeDetails.getType());
            existingRoute.setFirstTrip(routeDetails.getFirstTrip());
            existingRoute.setLastTrip(routeDetails.getLastTrip());
            existingRoute.setIntervalMins(routeDetails.getIntervalMins());
            existingRoute.setPassengersDay(routeDetails.getPassengersDay());
            return routeRepository.save(existingRoute);
        } else {
            throw new RuntimeException("Route not found");
        }
    }

    public void deleteRoute(String routeNo) {
        routeRepository.deleteById(routeNo);
    }
}
