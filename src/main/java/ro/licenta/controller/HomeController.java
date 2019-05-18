package ro.licenta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ro.licenta.dto.SearchDTO;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(@ModelAttribute SearchDTO searchDTO, Model model) {
		searchDTO = new SearchDTO();
		model.addAttribute("searchDTO", searchDTO);
		return "home";
	}
}
