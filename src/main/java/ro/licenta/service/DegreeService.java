package ro.licenta.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ro.licenta.dto.SearchDTO;
import ro.licenta.model.Degree;

public interface DegreeService {
	
	Degree addDegree(Degree degree);
	
	Degree updateDegree(Degree degree);
	
	void deleteDegree(Long degreeId);
	
	Degree findDegree(Long degreeId);
	
	List<Degree> getAllDegrees();
	
	Page<Degree> search(SearchDTO searchDTO);
}
