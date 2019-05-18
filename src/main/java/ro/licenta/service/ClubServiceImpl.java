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
import ro.licenta.model.Club;
import ro.licenta.repository.ClubRepository;

@Service("clubService")
public class ClubServiceImpl implements ClubService {

	private static final Logger logger = LoggerFactory.getLogger(ClubServiceImpl.class);

	@Autowired
	private ClubRepository clubRepository;

	@Override
	public Club addClub(Club club) {
		return clubRepository.save(club);
	}

	@Override
	public Club updateClub(Club club) {
		clubRepository.findById(club.getId())
				.orElseThrow(() -> new RecordNotFoundException("Club with id " + club.getId() + " not found."));

		return clubRepository.save(club);
	}

	@Override
	public void deleteClub(Long clubId) {
		// TODO Auto-generated method stub
		Club club = clubRepository.findById(clubId).orElse(null);
		logger.debug("Deleting the club with id : " + clubId);
		if (club != null) {
			clubRepository.deleteById(clubId);
		} else {
			String errorMessage = "Club with id " + clubId + " not found.";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
	}

	@Override
	public Club findClub(Long clubId) {
		// TODO Auto-generated method stub
		return clubRepository.findById(clubId).orElse(null);
	}

	@Override
	public List<Club> getAllClubs() {
		// TODO Auto-generated method stub
		return clubRepository.findAll();
	}

	@Override
	public Page<Club> search(SearchDTO searchDTO) {
		Page<Club> result = null;
		PageRequest pageRequest = PaginationUtils.getPageRequest(searchDTO);
		if (StringUtils.hasText(searchDTO.getQuery())) {
			result = this.clubRepository.findByClubNameIgnoreCaseContaining(pageRequest, searchDTO.getQuery());
		} else {
			result = this.clubRepository.findAll(pageRequest);
		}

		return result;
	}
}
