package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vttp2023.batch3.ssf.frontcontroller.model.Credentials;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
@RequestMapping("/protected")
public class ProtectedController {

	@Autowired
	AuthenticationService svc;

	// TODO Task 5
	// Write a controller to protect resources rooted under /protected
	@GetMapping("/**")
	public String getProtectedResource(HttpSession session, HttpServletRequest request, Model model) {
		String uri = request.getRequestURI();
		if (session.getAttribute("credentials") == null) {
			model.addAttribute("credentials", new Credentials());
			return "view0";
		}
		return svc.accessResource((Credentials) session.getAttribute("credentials"), uri, model);
	}

}
