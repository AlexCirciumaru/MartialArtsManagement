package ro.licenta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ro.licenta.controller.utils.PaginationUtils;
import ro.licenta.dto.SearchDTO;
import ro.licenta.exception.RecordNotFoundException;
import ro.licenta.model.Karateka;
import ro.licenta.repository.KaratekaRepository;

@Service("karatekaService")
public class KaratekaServiceImpl implements KaratekaService {

	private static final Logger logger = LoggerFactory.getLogger(KaratekaServiceImpl.class);

	@Autowired
	private KaratekaRepository karatekaRepository;

	@Override
	public Karateka addKarateka(Karateka karateka) {
		return karatekaRepository.save(karateka);
	}

	@Override
	public Karateka updateKarateka(Karateka karateka) {
		Karateka existingKarateka = karatekaRepository.findById(karateka.getId()).orElse(null);
		if (existingKarateka == null) {
			String errorMessage = "Karateka with id : " + karateka.getId() + " not found.";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
		return karatekaRepository.save(karateka);
	}

	@Override
	public void deleteKarateka(Long karatekaId) {
		Karateka karateka = karatekaRepository.findById(karatekaId).orElse(null);
		if (karateka != null) {
			karatekaRepository.deleteById(karatekaId);
		} else {
			String errorMessage = "Karateka with id : " + karatekaId + " not found.";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
	}

	@Override
	public Karateka findKarateka(Long karatekaId) {
		return karatekaRepository.findById(karatekaId).orElse(null);
	}

	@Override
	public List<Karateka> getAllTrainers() {
		return karatekaRepository.findByTrainerTrue();
	}

	@Override
	public Page<Karateka> search(SearchDTO searchDTO) {
		Page<Karateka> result = null;
		PageRequest pageRequest = PaginationUtils.getPageRequest(searchDTO);
		if (StringUtils.hasText(searchDTO.getQuery())) {
			result = this.karatekaRepository
					.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrEmailContaining(pageRequest,
							searchDTO.getQuery(), searchDTO.getQuery(), searchDTO.getQuery());
		} else {
			result = this.karatekaRepository.findAll(pageRequest);
		}
		return result;
	}

	@Override
	public Page<Karateka> findClubMembers(SearchDTO searchDTO, Long clubId) {
		Page<Karateka> result = null;
		PageRequest pageRequest = PaginationUtils.getPageRequest(searchDTO);
		if (StringUtils.hasText(searchDTO.getQuery())) {
			result = this.karatekaRepository
					.findByClubIdAndLastNameIgnoreCaseContainingOrClubIdAndFirstNameIgnoreCaseContainingOrClubIdAndEmailContaining(
							pageRequest, clubId, searchDTO.getQuery(), clubId, searchDTO.getQuery(), clubId,
							searchDTO.getQuery());
		} else {
			result = this.karatekaRepository.findByClubId(pageRequest, clubId);
		}
		return result;
	}	
}
