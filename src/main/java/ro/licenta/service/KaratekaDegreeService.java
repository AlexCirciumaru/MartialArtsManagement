package ro.licenta.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ro.licenta.dto.SearchDTO;
import ro.licenta.model.KaratekaDegree;

public interface KaratekaDegreeService {
	
	KaratekaDegree addKaratekaDegree(KaratekaDegree karatekaDegree);
	
	KaratekaDegree updateKaratekaDegree(KaratekaDegree karatekaDegree);
	
	void deleteKaratekaDegree(Long karatekaDegreeId);
	
	KaratekaDegree findKaratekaDegree(Long karatekaDegreeId);
	
	List<KaratekaDegree> getAllKaratekaDegrees();
	
	Page<KaratekaDegree> findKaratekaDegrees(SearchDTO searchDTO, Long karatekaId);
}
