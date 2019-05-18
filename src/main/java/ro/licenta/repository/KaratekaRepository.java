package ro.licenta.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ro.licenta.model.Karateka;

@Repository("karatekaRepository")
public interface KaratekaRepository extends JpaRepository<Karateka, Long>, JpaSpecificationExecutor<Karateka> {

	Page<Karateka> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrEmailContaining(
			Pageable pageRequest, String query, String query2, String query3);

	List<Karateka> findByTrainerTrue();

	Page<Karateka> findByClubId(Pageable pageRequest, Long clubId);

	Page<Karateka> findByClubIdAndLastNameIgnoreCaseContainingOrClubIdAndFirstNameIgnoreCaseContainingOrClubIdAndEmailContaining(
			Pageable pageRequest, Long clubId, String query, Long clubId2, String query2, Long clubId3, String query3);
}
