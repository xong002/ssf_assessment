package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.Credentials;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
@RequestMapping("/")
public class FrontController {

	@Autowired
	AuthenticationService svc;

	@GetMapping
	public String landingPage(Model model){
		model.addAttribute("credentials", new Credentials());
		return "view0";
	}
	
	@PostMapping(path = "/login", consumes = "application/x-www-form-urlencoded", produces = "text/html")
	public String login(HttpSession session, @Valid Credentials cred, BindingResult br, Model model){
		if(br.hasErrors()){
			return "view0";
		}
		//svc to authenticate
		try{
		 svc.authenticate(cred.getUsername(), cred.getPassword());
		} catch (HttpClientErrorException.Unauthorized uae) {
			System.out.println("Unauthorised");
			//handle login attempts
			br.addError(new FieldError("credentials", "global", "Unauthorised"));
			return "view0";
		}
		catch (Exception e){
			e.printStackTrace();
			//add internal server error to model
			System.out.println("Internal server error");
			br.addError(new FieldError("credentials", "global", "Internal Server Error"));
			return "view0";
		}
		
		return "view1";
	}

	// TODO: Task 2, Task 3, Task 4, Task 6

	
	
	
}
