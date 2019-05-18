package ro.licenta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ro.licenta.controller.utils.PaginationUtils;
import ro.licenta.dto.SearchDTO;
import ro.licenta.exception.RecordNotFoundException;
import ro.licenta.model.KaratekaDegree;
import ro.licenta.repository.KaratekaDegreeRepository;

@Service("karatekaDegreeService")
public class KaratekaDegreeServiceImpl implements KaratekaDegreeService {

	private static final Logger logger = LoggerFactory.getLogger(KaratekaDegreeServiceImpl.class);

	@Autowired
	private KaratekaDegreeRepository karatekaDegreeRepository;

	@Override
	public KaratekaDegree addKaratekaDegree(KaratekaDegree karatekaDegree) {
		return karatekaDegreeRepository.save(karatekaDegree);
	}

	@Override
	public KaratekaDegree updateKaratekaDegree(KaratekaDegree karatekaDegree) {
		KaratekaDegree existingKaratekaDegree = karatekaDegreeRepository.findById(karatekaDegree.getId()).orElse(null);
		if (existingKaratekaDegree == null) {
			String errorMessage = "Karateka Degree with id " + karatekaDegree.getId() + " not found.";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
		return karatekaDegreeRepository.save(karatekaDegree);
	}

	@Override
	public void deleteKaratekaDegree(Long karatekaDegreeId) {
		KaratekaDegree karatekaDegree = karatekaDegreeRepository.findById(karatekaDegreeId).orElse(null);
		logger.debug("Deleting the Karateka Degree with id : " + karatekaDegreeId);
		if (karatekaDegree != null) {
			karatekaDegreeRepository.deleteById(karatekaDegreeId);
		} else {
			String errorMessage = "Karateka Degree with id : " + karatekaDegreeId + " not found.";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
	}

	@Override
	public KaratekaDegree findKaratekaDegree(Long karatekaDegreeId) {
		return karatekaDegreeRepository.findById(karatekaDegreeId).orElse(null);
	}

	@Override
	public List<KaratekaDegree> getAllKaratekaDegrees() {
		return karatekaDegreeRepository.findAll();
	}

	@Override
	public Page<KaratekaDegree> findKaratekaDegrees(SearchDTO searchDTO, Long karatekaId) {
		PageRequest pageRequest = PaginationUtils.getPageRequest(searchDTO);
		return this.karatekaDegreeRepository.findByKaratekaId(pageRequest, karatekaId);
	}

}
