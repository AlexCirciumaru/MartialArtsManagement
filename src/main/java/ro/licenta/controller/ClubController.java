package ro.licenta.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ro.licenta.dto.ClubDTO;
import ro.licenta.dto.SearchDTO;
import ro.licenta.model.Club;
import ro.licenta.model.Karateka;
import ro.licenta.service.ClubService;
import ro.licenta.service.KaratekaService;

@Controller
public class ClubController {

	private static Logger logger = LoggerFactory.getLogger(ClubController.class);

	@Autowired
	private ClubService clubService;
	
	@Autowired
	private KaratekaService karatekaService;

	@GetMapping(value = "/clubs")
	public String getClubs(@ModelAttribute SearchDTO searchDTO, Model model) {
		Page<Club> clubs = clubService.search(searchDTO);
		model.addAttribute("clubs", clubs);
		return "clubs";
	}

	@GetMapping(value = "/club/add")
	public String getAddClubForm(Model model) {
		ClubDTO clubDTO = new ClubDTO();
		model.addAttribute("clubDTO", clubDTO);
		return "add-club";
	}

	@PostMapping(value = "/club/add")
	public String addClubForm(@Valid @ModelAttribute ClubDTO clubDTO, BindingResult result, Model model,
			RedirectAttributes redirectAtttributes) {
		if (result.hasErrors()) {
			logger.error("Add club error : " + result.getAllErrors());
			List<Club> clubs = clubService.getAllClubs();
			model.addAttribute("clubs", clubs);

			return "add-club";
		} else {
			Club club = new Club();
			club.setClubName(clubDTO.getClubName());
			club.setAddress(clubDTO.getAddress());
			club.setDateOfEstablishment(clubDTO.getDateOfEstablishment());

			clubService.addClub(club);
			redirectAtttributes.addFlashAttribute("message", "Successfully added.");
			return "redirect:/clubs";
		}
	}

	@GetMapping(value = "/club/update")
	public String getClubEditForm(@RequestParam(value = "id", required = true) Long id, Model model,
			RedirectAttributes redirectAttributes) {
		Club club = clubService.findClub(id);
		logger.debug("Edit club : ", club);
		if (club != null) {
			// Create and put ClubDTO needed to edit the club.
			ClubDTO clubDTO = new ClubDTO();
			clubDTO.setId(club.getId());
			clubDTO.setClubName(club.getClubName());
			clubDTO.setAddress(club.getAddress());
			clubDTO.setDateOfEstablishment(club.getDateOfEstablishment());

			model.addAttribute("clubDTO", clubDTO);

			return "update-club";
		} else {
			logger.error("Edit error : Club with id {} not found.", id);
			redirectAttributes.addFlashAttribute("message", "Club with specified id not found.");
			return "redirect:/clubs";
		}
	}

	@PostMapping(value = "/club/update")
	public String updateClub(@Valid @ModelAttribute ClubDTO clubDTO, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Update club validation error : " + result.getAllErrors());
			return "update-club";
		} else {
			Club club = new Club();
			club.setId(clubDTO.getId());
			club.setClubName(clubDTO.getClubName());
			club.setAddress(clubDTO.getAddress());
			club.setDateOfEstablishment(clubDTO.getDateOfEstablishment());

			clubService.updateClub(club);

			redirectAttributes.addFlashAttribute("message", "Club successfully updated.");
			return "redirect:/clubs";
		}
	}

	@GetMapping(value = "/club/delete")
	public String deleteClub(@Valid @ModelAttribute("id") Long id, BindingResult result,
			RedirectAttributes redirectAttributes) {
		try {
			clubService.deleteClub(id);
			redirectAttributes.addFlashAttribute("message", "Successfully deleted.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Delete error : " + e.getMessage());
		}

		return "redirect:/clubs";
	}
	
	@GetMapping(value = "/club/{clubId}/members")
	public String getClubMembers(@ModelAttribute SearchDTO searchDTO, @PathVariable Long clubId, Model model) {
		Page<Karateka> karatekas = karatekaService.findClubMembers(searchDTO, clubId);
		model.addAttribute("karatekas", karatekas);
		return "karatekas";
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
