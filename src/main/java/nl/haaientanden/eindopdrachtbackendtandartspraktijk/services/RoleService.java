package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.RoleDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Role;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDto saveRole(RoleDto roleDto) {
        Role role = transferToRole(roleDto);
        roleRepository.save(role);

        return transferToDto(role);
    }

    public List<RoleDto> getRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            roleDtos.add(transferToDto(role));
        }
        return roleDtos;
    }

    public void deleteRoleByIdRolename(@RequestBody String rolename) {
        roleRepository.deleteById(rolename);
    }

    public static Role transferToRole(RoleDto roleDto) {
        var role = new Role();
        String rolename = role.getRolename();
        role.setRolename(roleDto.getRolename());

        return role;
    }

    public static RoleDto transferToDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRolename(role.getRolename());

        return roleDto;
    }
}
