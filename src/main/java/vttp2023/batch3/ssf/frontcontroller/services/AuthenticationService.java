package vttp2023.batch3.ssf.frontcontroller.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import vttp2023.batch3.ssf.frontcontroller.model.Credentials;
import vttp2023.batch3.ssf.frontcontroller.respositories.AuthenticationRepository;

@Service
public class AuthenticationService {

	@Value("${authentication.url}")
	String authUrl;

	@Autowired
	AuthenticationRepository repo;

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public void authenticate(String username, String password) throws Exception {

		RestTemplate template = new RestTemplate();
		Credentials cred = new Credentials(username, password);
		HttpEntity<Credentials> request = new HttpEntity<>(cred);
		ResponseEntity<Credentials> response = template.postForEntity(authUrl, request, Credentials.class);
		if (response.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(201))) {
			cred.setAuthenticated(true);
			System.out.println(cred.toString());
		} else
			throw new Exception();

	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public boolean disableUser(String username) {
		repo.disableLogin(username);
		return repo.checkDisabled(username);
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return repo.checkDisabled(username);
	}

	public String accessResource(Credentials credentials, String resource, Model model){
		if(credentials.isAuthenticated()){
			return "/protected/" + resource;
		} 
		model.addAttribute("credentials", new Credentials());
		return "view0";
	}
}
