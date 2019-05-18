package ro.licenta.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ro.licenta.dto.SearchDTO;
import ro.licenta.model.Club;

public interface ClubService {
	
	Club addClub(Club club);
	
	Club updateClub(Club club);
	
	void deleteClub(Long clubId);
	
	Club findClub(Long clubId);
	
	List<Club> getAllClubs();
	
	Page<Club> search(SearchDTO searchDTO);
}
