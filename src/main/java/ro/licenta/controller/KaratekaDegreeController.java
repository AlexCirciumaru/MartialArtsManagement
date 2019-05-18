package ro.licenta.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ro.licenta.dto.KaratekaDegreeDTO;
import ro.licenta.model.Club;
import ro.licenta.model.Degree;
import ro.licenta.model.Karateka;
import ro.licenta.model.KaratekaDegree;
import ro.licenta.service.ClubService;
import ro.licenta.service.DegreeService;
import ro.licenta.service.KaratekaDegreeService;
import ro.licenta.service.KaratekaService;

@Controller
public class KaratekaDegreeController {

	private static Logger logger = LoggerFactory.getLogger(KaratekaDegreeController.class);

	@Autowired
	private KaratekaDegreeService karatekaDegreeService;

	@Autowired
	private KaratekaService karatekaService;

	@Autowired
	private DegreeService degreeService;

	@Autowired
	private ClubService clubService;

	@GetMapping(value = "/karatekaDegree/add")
	public String getAddKaratekaDegreeForm(@RequestParam(value = "id", required = true) Long id, Model model) {

		// Add to model supplementary attributes needed to construct the add form.

		Karateka karateka = karatekaService.findKarateka(id);
		model.addAttribute("karateka", karateka);

		// Add list of degrees to populate the degrees selector.
		List<Degree> degrees = degreeService.getAllDegrees();
		model.addAttribute("degrees", degrees);

		// Add list of clubs to populate the clubs selector.
		List<Club> clubs = clubService.getAllClubs();
		model.addAttribute("clubs", clubs);

		// Add list to populate the trainer selector.
		List<Karateka> trainers = karatekaService.getAllTrainers();
		model.addAttribute("trainers", trainers);

		KaratekaDegreeDTO karatekaDegreeDTO = new KaratekaDegreeDTO();
		model.addAttribute("karatekaDegreeDTO", karatekaDegreeDTO);

		return "add-karateka-degree";
	}

	@PostMapping(value = "/karatekaDegree/add")
	public String addKaratekaDegreeForm(@Valid @ModelAttribute KaratekaDegreeDTO karatekaDegreeDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Add Karateka Degree error : " + result.getAllErrors());

			// Add to model supplementary attributes needed to construct the add form.

			Karateka currentKarateka = karatekaService.findKarateka(karatekaDegreeDTO.getKaratekaId());
			model.addAttribute("karateka", currentKarateka);

			// Add list of degrees to populate the degrees selector.
			List<Degree> degrees = degreeService.getAllDegrees();
			model.addAttribute("degrees", degrees);

			// Add list of clubs to populate the clubs selector.
			List<Club> clubs = clubService.getAllClubs();
			model.addAttribute("clubs", clubs);

			// Add list to populate the trainer selector.
			List<Karateka> trainers = karatekaService.getAllTrainers();
			model.addAttribute("trainers", trainers);

			return "add-karateka-degree";
		} else {
			KaratekaDegree karatekaDegree = new KaratekaDegree();
			karatekaDegree.setKarateka(new Karateka(karatekaDegreeDTO.getKaratekaId()));
			karatekaDegree.setDegree(new Degree(karatekaDegreeDTO.getDegreeId()));
			karatekaDegree.setDateOfReceipt(karatekaDegreeDTO.getDateOfReceipt());
			karatekaDegree.setClub(new Club(karatekaDegreeDTO.getClubId()));
			karatekaDegree.setTrainer(new Karateka(karatekaDegreeDTO.getTrainerId()));

			karatekaDegreeService.addKaratekaDegree(karatekaDegree);
			redirectAttributes.addFlashAttribute("message", "Successfully added.");
			return "redirect:/karateka/" + karatekaDegree.getKarateka().getId() + "/history";
		}
	}

	@GetMapping(value = "/karatekaDegree/update")
	public String getKaratekaDegreeEditForm(@RequestParam(value = "id", required = true) Long id, Model model,
			RedirectAttributes redirectAttributes) {
		KaratekaDegree karatekaDegree = karatekaDegreeService.findKaratekaDegree(id);
		logger.debug("Edit Karateka Degree : ", karatekaDegree);
		if (karatekaDegree != null) {
			// Create and put KaratekaDegreeDTO to edit the KaratekaDegree.
			KaratekaDegreeDTO karatekaDegreeDTO = new KaratekaDegreeDTO();
			karatekaDegreeDTO.setId(karatekaDegree.getId());
			karatekaDegreeDTO.setKaratekaId(karatekaDegree.getKarateka().getId());
			karatekaDegreeDTO.setDegreeId(karatekaDegree.getDegree().getId());
			karatekaDegreeDTO.setDateOfReceipt(karatekaDegree.getDateOfReceipt());
			karatekaDegreeDTO.setClubId(karatekaDegree.getClub().getId());
			karatekaDegreeDTO.setTrainerId(karatekaDegree.getTrainer().getId());

			model.addAttribute("karatekaDegreeDTO", karatekaDegreeDTO);

			// Add to model supplementary attributes needed to construct the add form.

			Karateka karateka = karatekaService.findKarateka(karatekaDegree.getKarateka().getId());
			model.addAttribute("karateka", karateka);

			// Add list of degrees to populate the degrees selector.
			List<Degree> degrees = degreeService.getAllDegrees();
			model.addAttribute("degrees", degrees);

			// Add list of clubs to populate the clubs selector.
			List<Club> clubs = clubService.getAllClubs();
			model.addAttribute("clubs", clubs);

			// Add list to populate the trainer selector.
			List<Karateka> trainers = karatekaService.getAllTrainers();
			model.addAttribute("trainers", trainers);

			return "update-karateka-degree";
		} else {
			logger.error("Edit error : Karateka Degree with id {} not found.", id);
			redirectAttributes.addFlashAttribute("message", "Karateka Degree with specified id not found.");
			return "redirect:/karatekas";
		}
	}

	@PostMapping(value = "/karatekaDegree/update")
	public String updateKaratekaDegree(@Valid @ModelAttribute KaratekaDegreeDTO karatekaDegreeDTO, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Update Karateka Degree validation error : " + result.getAllErrors());

			// Add to model supplementary attributes needed to construct the add form.

			// Add list of degrees to populate the degrees selector.
			List<Degree> degrees = degreeService.getAllDegrees();
			model.addAttribute("degrees", degrees);

			// Add list of clubs to populate the clubs selector.
			List<Club> clubs = clubService.getAllClubs();
			model.addAttribute("clubs", clubs);

			// Add list to populate the trainers selector.
			List<Karateka> trainers = karatekaService.getAllTrainers();
			model.addAttribute("trainers", trainers);

			return "update-karateka-degree";
		} else {
			KaratekaDegree karatekaDegree = new KaratekaDegree();
			karatekaDegree.setId(karatekaDegreeDTO.getId());
			karatekaDegree.setKarateka(new Karateka(karatekaDegreeDTO.getKaratekaId()));
			karatekaDegree.setDegree(new Degree(karatekaDegreeDTO.getDegreeId()));
			karatekaDegree.setDateOfReceipt(karatekaDegreeDTO.getDateOfReceipt());
			karatekaDegree.setClub(new Club(karatekaDegreeDTO.getClubId()));
			karatekaDegree.setTrainer(new Karateka(karatekaDegreeDTO.getTrainerId()));

			karatekaDegreeService.updateKaratekaDegree(karatekaDegree);

			redirectAttributes.addFlashAttribute("message", "Karateka Degree successfully updated.");
			return "redirect:/karateka/" + karatekaDegree.getKarateka().getId() + "/history";
		}
	}

	@GetMapping(value = "/karatekaDegree/delete")
	public String deleteKarateDegree(@Valid @ModelAttribute("id") Long id, BindingResult result,
			RedirectAttributes redirectAttributes) {
		KaratekaDegree karatekaDegree = karatekaDegreeService.findKaratekaDegree(id);
		try {
			karatekaDegreeService.deleteKaratekaDegree(id);
			redirectAttributes.addFlashAttribute("message", "Successfully deleted.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Delete error : " + e.getMessage());
		}

		return "redirect:/karateka/" + karatekaDegree.getKarateka().getId() + "/history";
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
