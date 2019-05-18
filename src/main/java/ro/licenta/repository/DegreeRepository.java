package ro.licenta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ro.licenta.model.Degree;

@Repository("degreeRepository")
public interface DegreeRepository extends JpaRepository<Degree, Long>, JpaSpecificationExecutor<Degree>{
	
	Page<Degree> findByBeltColorIgnoreCaseContaining(Pageable pageable, String query);
}
