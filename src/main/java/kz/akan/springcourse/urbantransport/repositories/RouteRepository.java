package kz.akan.springcourse.urbantransport.repositories;

import kz.akan.springcourse.urbantransport.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, String>, JpaSpecificationExecutor<Route> {
}
