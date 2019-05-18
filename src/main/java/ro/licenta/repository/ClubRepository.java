package ro.licenta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ro.licenta.model.Club;

@Repository("clubRepository")
public interface ClubRepository extends JpaRepository<Club, Long>, JpaSpecificationExecutor<Club>{
	
	Page<Club> findByClubNameIgnoreCaseContaining(Pageable pageRequest, String query);
}
