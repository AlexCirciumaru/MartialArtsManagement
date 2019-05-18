package ro.licenta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ro.licenta.model.KaratekaDegree;

@Repository("karatekaDegreeRepository")
public interface KaratekaDegreeRepository extends JpaRepository<KaratekaDegree, Long>, JpaSpecificationExecutor<KaratekaDegree>{
	
	Page<KaratekaDegree> findByKaratekaId(Pageable pageRequest, Long karatekaId);		
}
