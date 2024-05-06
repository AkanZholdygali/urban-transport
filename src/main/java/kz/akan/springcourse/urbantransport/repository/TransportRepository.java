package kz.akan.springcourse.urbantransport.repository;

import kz.akan.springcourse.urbantransport.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransportRepository extends JpaRepository<Transport, String> {
    List<Transport> findAllByLicensePlateNoContaining(String licensePlateNo);
    Optional<List<Transport>> findByTypeTitle(String title);

    List<Transport> findByLicensePlateNoContainingIgnoreCaseAndType_Title(String searchText, String typeTitle);

    List<Transport> findByLicensePlateNoContainingIgnoreCase(String searchText);

    List<Transport> findByType_Title(String typeTitle);
}
