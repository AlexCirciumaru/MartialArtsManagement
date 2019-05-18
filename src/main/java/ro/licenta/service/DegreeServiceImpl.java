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
import ro.licenta.model.Degree;
import ro.licenta.repository.DegreeRepository;

@Service("degreeService")
public class DegreeServiceImpl implements DegreeService{

	private static final Logger logger = LoggerFactory.getLogger(DegreeServiceImpl.class);
	
	@Autowired
	private DegreeRepository degreeRepository;
	
	@Override
	public Degree addDegree(Degree degree) {
		// TODO Auto-generated method stub
		return degreeRepository.save(degree);		
	}

	@Override
	public Degree updateDegree(Degree degree) {
		// TODO Auto-generated method stub
		Degree existingDegree = degreeRepository.findById(degree.getId()).orElse(null);
		if(existingDegree == null) {
			String errorMessage = "Degree with id : " + degree.getId() + " not found.";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
		return degreeRepository.save(degree);
	}

	@Override
	public void deleteDegree(Long degreeId) {
		// TODO Auto-generated method stub
		Degree degree = degreeRepository.findById(degreeId).orElse(null);
		if(degree != null) {
			degreeRepository.deleteById(degreeId);
		} else {
			String errorMessage = "Degree with id : " + degreeId + " not found.";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
	}

	@Override
	public Degree findDegree(Long degreeId) {
		// TODO Auto-generated method stub
		return degreeRepository.findById(degreeId).orElse(null);		
	}

	@Override
	public List<Degree> getAllDegrees() {
		// TODO Auto-generated method stub
		return degreeRepository.findAll();
	}

	@Override
	public Page<Degree> search(SearchDTO searchDTO) {
		// TODO Auto-generated method stub
		Page<Degree> result = null;
		PageRequest pageRequest = PaginationUtils.getPageRequest(searchDTO);
		if(StringUtils.hasText(searchDTO.getQuery())) {
			result = this.degreeRepository.findByBeltColorIgnoreCaseContaining(pageRequest, searchDTO.getQuery());
		} else {
			result = this.degreeRepository.findAll(pageRequest);
		}
		
		return result;
	}
}
