package kz.akan.springcourse.urbantransport.repository;

import kz.akan.springcourse.urbantransport.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, String> {
}
