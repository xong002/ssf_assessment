package vttp2023.batch3.ssf.frontcontroller.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vttp2023.batch3.ssf.frontcontroller.model.Credentials;

@Service
public class AuthenticationService {

	@Value("${authentication.url}")
	String authUrl;

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
	public void disableUser(String username) {
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}
}
