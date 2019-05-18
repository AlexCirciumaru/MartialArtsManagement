package ro.licenta.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ro.licenta.dto.SearchDTO;
import ro.licenta.model.Karateka;

public interface KaratekaService {
	
	Karateka addKarateka(Karateka karateka);
	
	Karateka updateKarateka(Karateka karateka);
	
	void deleteKarateka(Long karatekaId);
	
	Karateka findKarateka(Long karatekaId);
	
	List<Karateka> getAllTrainers();

	Page<Karateka> search(SearchDTO searchDTO);
	
	Page<Karateka> findClubMembers(SearchDTO searchDTO, Long clubId);
}
