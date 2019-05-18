package ro.licenta.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ro.licenta.dto.DegreeDTO;
import ro.licenta.dto.SearchDTO;
import ro.licenta.model.Degree;
import ro.licenta.service.DegreeService;

@Controller
public class DegreeController {
	
	private static Logger logger = LoggerFactory.getLogger(DegreeController.class);
	
	@Autowired
	private DegreeService degreeService;
	
	@GetMapping(value = "/degrees")
	public String getDegrees(@ModelAttribute SearchDTO searchDTO , Model model) {
		Page<Degree> degrees = degreeService.search(searchDTO);
		model.addAttribute("degrees", degrees);			
		return "degrees";
	}
	
	@GetMapping(value = "/degree/add")
	public String getAddDegreeForm(Model model) {
		DegreeDTO degreeDTO = new DegreeDTO();
		model.addAttribute("degreeDTO", degreeDTO);
		return "add-degree";
	}
	
	@PostMapping(value = "/degree/add")
	public String addDegreeForm(@Valid @ModelAttribute DegreeDTO degreeDTO, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			logger.error("Add degree error : " + result.getAllErrors());
			return "add-degree";
		} else {
			Degree degree = new Degree();
			degree.setDegreeRank(degreeDTO.getDegreeRank());
			degree.setDegreeType(degreeDTO.getDegreeType());
			degree.setBeltColor(degreeDTO.getBeltColor());
			
			degreeService.addDegree(degree);
			redirectAttributes.addFlashAttribute("message", "Successfully added.");			
			return "redirect:/degrees";
		}
	}
	
	@GetMapping(value = "/degree/update")
	public String getDegreeEditForm(@RequestParam(value = "id", required = true) Long id, Model model,
			RedirectAttributes redirectAttributes) {
		Degree degree = degreeService.findDegree(id);
		logger.debug("Edit degree : " + degree);
		if(degree != null) {
			//Create and put DegreeDTO needed to edit degree.
			DegreeDTO degreeDTO = new DegreeDTO();
			degreeDTO.setId(degree.getId());
			degreeDTO.setDegreeRank(degree.getDegreeRank());
			degreeDTO.setDegreeType(degree.getDegreeType());
			degreeDTO.setBeltColor(degree.getBeltColor());
			
			model.addAttribute("degreeDTO", degreeDTO);
			
			return "update-degree";
		} else {
			logger.error("Edit error : Degree with id {} not found." + id);
			redirectAttributes.addFlashAttribute("errorMessage", "Degree with specified it not found.");
			return "redirect:/degrees";
		}
	}
	
	@PostMapping(value = "/degree/update")
	public String updateDegree(@Valid @ModelAttribute DegreeDTO degreeDTO, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			logger.error("Update degree validation error." + result.getAllErrors());
			return "update-degree";
		} else {
			Degree degree = new Degree();
			degree.setId(degreeDTO.getId());
			degree.setDegreeRank(degreeDTO.getDegreeRank());
			degree.setDegreeType(degreeDTO.getDegreeType());
			degree.setBeltColor(degreeDTO.getBeltColor());
			
			degreeService.updateDegree(degree);
			
			redirectAttributes.addFlashAttribute("message", "Degree updated successfully.");
			return "redirect:/degrees";
		}
	}
	
	@GetMapping(value = "/degree/delete")
	public String deleteDegree(@Valid @ModelAttribute("id") Long id, BindingResult result,
			RedirectAttributes redirectAttributes) {
		try {
			degreeService.deleteDegree(id);
			redirectAttributes.addFlashAttribute("message", "Degree deleted successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Delete error : " + e.getMessage());
		}
		
		return "redirect:/degrees";
	}		
}
