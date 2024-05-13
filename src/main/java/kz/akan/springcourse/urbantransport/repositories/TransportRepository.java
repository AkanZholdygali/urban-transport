package kz.akan.springcourse.urbantransport.repositories;

import kz.akan.springcourse.urbantransport.models.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransportRepository extends JpaRepository<Transport, String> {
    List<Transport> findByLicensePlateNoContainingIgnoreCase(String licensePlateNo);
    List<Transport> findByType_Title(String typeTitle);
    List<Transport> findByLicensePlateNoContainingIgnoreCaseAndType_Title(String licensePlateNo, String typeTitle);
}