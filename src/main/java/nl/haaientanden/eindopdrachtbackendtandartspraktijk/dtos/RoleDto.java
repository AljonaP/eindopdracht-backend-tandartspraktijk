package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RoleDto {

    @Size(min = 5, max = 15)
    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename.toUpperCase();
    }
}


