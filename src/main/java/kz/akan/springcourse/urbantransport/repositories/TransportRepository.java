package kz.akan.springcourse.urbantransport.repositories;

import kz.akan.springcourse.urbantransport.models.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, String>, JpaSpecificationExecutor<Transport> {
}