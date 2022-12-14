package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.Pattern;
import java.util.List;

public class UserDto {
    @Pattern(regexp = "^[A-Z][A-Z,a-z]*[0-9]{0,2}$",
            message = "Entered value isn't correct. The username should: \n 1) begin with a capital letter;\n 2) then uppercase and lowercase letters may be used;\n 3) last two signs should be digits.")
    public String username;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",
            message = "Entered password isn't correct. A password should: \n 1) a digit must occur at least once;\n 2) a lower case alphabet must occur at least once;\n 3) an upper case alphabet that must occur at least once;\n 4) a special character that must occur at least once;\n 5) white spaces don’t allowed;\n 6) at least 8 characters and at most 20 characters")
    public String password;
    public List<String> roles;

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
