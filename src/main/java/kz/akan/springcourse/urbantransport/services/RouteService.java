package kz.akan.springcourse.urbantransport.services;

import kz.akan.springcourse.urbantransport.models.Route;
import kz.akan.springcourse.urbantransport.repositories.RouteRepository;
import kz.akan.springcourse.urbantransport.specifications.RouteSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
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
        Route existingRoute = routeRepository.findById(routeNo).orElse(null);
        if (existingRoute != null) {
            if (routeRepository.existsById(routeDetails.getRouteNo())) {
                return null;
            }
            deleteRoute(routeNo);
            return saveRoute(routeDetails);
        }else
            return null;
    }

    public void deleteRoute(String routeNo) {
        routeRepository.deleteById(routeNo);
    }

    public List<Route> getRoutesWithFilters(String searchText, Boolean busChecked, Boolean trolleybusChecked, LocalTime time) {
        Specification<Route> specification = Specification
                .where(RouteSpecification.hasRouteNoContaining(searchText))
                .and(RouteSpecification.hasTransportTypes(busChecked, trolleybusChecked))
                .and(RouteSpecification.isWithinTimeRange(time));
        return routeRepository.findAll(specification);
    }
}
