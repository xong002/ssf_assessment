package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.Credentials;

@Controller
@RequestMapping("/")
public class FrontController {

	@GetMapping
	public String landingPage(Model model){
		model.addAttribute("credentials", new Credentials());
		return "view0";
	}
	
	@PostMapping(path = "/login", consumes = "application/x-www-form-urlencoded", produces = "text/html")
	public String login(HttpSession session, @Valid Credentials cred, BindingResult br){
		
		if(br.hasErrors()){
			return "view0";
		}
		
		return "view1";
	}

	// TODO: Task 2, Task 3, Task 4, Task 6

	
	
	
}
