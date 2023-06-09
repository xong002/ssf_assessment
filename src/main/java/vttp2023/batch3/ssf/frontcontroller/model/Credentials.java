package vttp2023.batch3.ssf.frontcontroller.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Credentials {
    
    @NotNull(message = "Please enter a username.")
    @Size(min = 2, message = "Please enter at least 2 characters.")
    private String username;

    @NotNull(message = "Please enter a password.")
    @Size(min = 2, message = "Please enter at least 2 characters.")
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
