package ro.licenta.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ro.licenta.dto.KaratekaDTO;
import ro.licenta.dto.SearchDTO;
import ro.licenta.exception.DuplicateRecordException;
import ro.licenta.model.Club;
import ro.licenta.model.Karateka;
import ro.licenta.model.KaratekaDegree;
import ro.licenta.service.ClubService;
import ro.licenta.service.KaratekaDegreeService;
import ro.licenta.service.KaratekaService;

@Controller
public class KaratekaController {

	private static Logger logger = LoggerFactory.getLogger(KaratekaController.class);

	@Autowired
	private KaratekaService karatekaService;

	@Autowired
	private ClubService clubService;

	@Autowired
	private KaratekaDegreeService karatekaDegreeService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "/karatekas")
	public String getKaratekas(@ModelAttribute SearchDTO searchDTO, Model model) {
		Page<Karateka> karatekas = this.karatekaService.search(searchDTO);
		model.addAttribute("karatekas", karatekas);
		return "karatekas";
	}

	@GetMapping(value = "/karateka/{karatekaId}/history")
	public String getHistory(@ModelAttribute SearchDTO searchDTO, @PathVariable("karatekaId") Long karatekaId,
			Model model) {
		Page<KaratekaDegree> karatekaDegrees = karatekaDegreeService.findKaratekaDegrees(searchDTO, karatekaId);
		Karateka karateka = karatekaService.findKarateka(karatekaId);
		model.addAttribute("karatekaDegrees", karatekaDegrees);
		model.addAttribute("karateka", karateka);
		return "karateka-degrees";
	}

	@GetMapping(value = "/karateka/add")
	public String getAddKaratekaForm(Model model) {
		// Add list of clubs to model to populate the clubs selector.
		List<Club> clubs = clubService.getAllClubs();
		model.addAttribute("clubs", clubs);

		KaratekaDTO karatekaDTO = new KaratekaDTO();
		model.addAttribute("karatekaDTO", karatekaDTO);
		return "add-karateka";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving task in database. It also validates the task input
	 */
	@PostMapping(value = "/karateka/add")
	public String addKaratekaForm(@Valid @ModelAttribute KaratekaDTO karatekaDTO, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Add Karateka error : " + result.getAllErrors());
			// Add list of clubs to model to populate the clubs selector.
			List<Club> clubs = clubService.getAllClubs();
			model.addAttribute("clubs", clubs);

			return "add-karateka";
		} else {
			try {
				Karateka karateka = new Karateka();
				karateka.setLastName(karatekaDTO.getLastName());
				karateka.setFirstName(karatekaDTO.getFirstName());
				karateka.setAge(karatekaDTO.getAge());
				karateka.setBeginningYear(karatekaDTO.getBeginningYear());
				if (karateka.checkDifferenceBetweenAgeAndBeginningYear()) {
					result.rejectValue("age", "input-error", "Years of practice are bigger than karateka's age");
					logger.error("Add karateka error : " + result.getAllErrors());
					return "add-karateka";
				}
				karateka.setEmail(karatekaDTO.getEmail());
				// Before save encode password.
				karateka.setPassword(passwordEncoder.encode(karatekaDTO.getPassword()));
				karateka.setTrainer(karatekaDTO.isTrainer());
				karateka.setClub(new Club(karatekaDTO.getClubId()));
				karatekaService.addKarateka(karateka);
			} catch (DuplicateRecordException e) {
				result.rejectValue("email", "duplicate", "Email already used");
				logger.error("Add karateka error : " + result.getAllErrors());
				return "add-karateka";
			}
			redirectAttributes.addFlashAttribute("message", "Successfully added.");
			return "redirect:/karatekas";
		}
	}

	@GetMapping(value = "/karateka/update")
	public String getKaratekaEditForm(@RequestParam(value = "id", required = true) Long id, Model model,
			RedirectAttributes redirectAttributes) {
		Karateka karateka = karatekaService.findKarateka(id);
		logger.debug("Edit karateka : ", karateka);
		if (karateka != null) {
			// Create and put a KaratekaDTO needed to edit karateka.
			KaratekaDTO karatekaDTO = new KaratekaDTO();
			karatekaDTO.setId(karateka.getId());
			karatekaDTO.setLastName(karateka.getLastName());
			karatekaDTO.setFirstName(karateka.getFirstName());
			karatekaDTO.setAge(karateka.getAge());
			karatekaDTO.setBeginningYear(karateka.getBeginningYear());
			karatekaDTO.setEmail(karateka.getEmail());
			karatekaDTO.setPassword(null); // Do not send password
			karatekaDTO.setTrainer(karateka.isTrainer());
			karatekaDTO.setClubId(karateka.getClub().getId());

			model.addAttribute("karatekaDTO", karatekaDTO);

			// Add to model supplementary attributes needed to construct the edit form.
			// Add list of clubs to model to populate the clubs selector.
			List<Club> clubs = clubService.getAllClubs();
			model.addAttribute("clubs", clubs);
			return "update-karateka";
		} else {
			logger.error("Edit error: Karateka with id {} not found.", id);
			redirectAttributes.addFlashAttribute("errorMessage", "Karateka with specified id not found.");
			return "redirect:/karatekas";
		}
	}

	@PostMapping(value = "/karateka/update")
	public String updateKarateka(@Valid @ModelAttribute KaratekaDTO karatekaDTO, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Update karateka validation error : " + result.getAllErrors());
			// Add to model supplementary attributes needed to construct the edit form.
			// Add list of clubs to model to populate the clubs selector.
			List<Club> clubs = clubService.getAllClubs();
			model.addAttribute("clubs", clubs);
			return "update-karateka";
		} else {
			try {
				Karateka karateka = new Karateka();
				karateka.setId(karatekaDTO.getId());
				karateka.setFirstName(karatekaDTO.getFirstName());
				karateka.setLastName(karatekaDTO.getLastName());
				karateka.setAge(karatekaDTO.getAge());
				karateka.setBeginningYear(karatekaDTO.getBeginningYear());
				if (karateka.checkDifferenceBetweenAgeAndBeginningYear()) {
					result.rejectValue("age", "input-error", "Years of practice are bigger than karateka's age");
					logger.error("Add karateka error : " + result.getAllErrors());
					return "add-karateka";
				}
				karateka.setEmail(karatekaDTO.getEmail());
				if (karatekaDTO.getPassword() != null && !karatekaDTO.getPassword().trim().isEmpty()) {
					// If the password is not null encode it.
					karateka.setPassword(passwordEncoder.encode(karatekaDTO.getPassword()));
				} else {
					karateka.setPassword(null);
				}
				karateka.setTrainer(karatekaDTO.isTrainer());
				karateka.setClub(new Club(karatekaDTO.getClubId()));

				karatekaService.updateKarateka(karateka);
			} catch (DuplicateRecordException e) {
				result.rejectValue("email", "duplicate", "New Email already used by another user.");
				logger.error("Update karateka error : " + result.getAllErrors());
				return "update-karateka";
			}
			redirectAttributes.addFlashAttribute("message", "Karateka updated successfully.");
			return "redirect:/karatekas";
		}
	}

	@GetMapping(value = "/karateka/delete")
	public String deleteKarateka(@Valid @ModelAttribute("id") Long id, BindingResult result,
			RedirectAttributes redirectAttributes) {
		try {
			karatekaService.deleteKarateka(id);
			redirectAttributes.addFlashAttribute("message", "Successfully deleted.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Delete error : " + e.getMessage());
		}

		return "redirect:/karatekas";
	}
}
