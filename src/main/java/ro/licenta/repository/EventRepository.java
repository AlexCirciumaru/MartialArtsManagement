package ro.licenta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ro.licenta.model.Event;

@Repository("eventRepository")
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

	Page<Event> findByNameIgnoreCaseContainingOrLocationIgnoreCaseContaining(
			Pageable pageRequest, String query1, String query2);
}
