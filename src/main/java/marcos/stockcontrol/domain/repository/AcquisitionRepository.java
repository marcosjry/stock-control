package marcos.stockcontrol.domain.repository;

import marcos.stockcontrol.domain.model.Acquisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquisitionRepository extends JpaRepository<Acquisition, Long> {


}
