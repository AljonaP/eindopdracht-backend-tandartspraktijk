package nl.haaientanden.eindopdrachtbackendtandartspraktijk.controllers;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.RoleDto;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.RoleRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static nl.haaientanden.eindopdrachtbackendtandartspraktijk.utils.UtilityMethodes.getErrorMessage;

@RestController
@RequestMapping("/haaientanden/roles")
public class RoleController {

    private final RoleRepository roleRepository;

    private final RoleService roleService;

    public RoleController(RoleRepository roleRepository, RoleService roleService) {

        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addRole(@Valid @RequestBody RoleDto roleDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(getErrorMessage(bindingResult), HttpStatus.BAD_REQUEST);
        }
        RoleDto dto = roleService.saveRole(roleDto);
        return ResponseEntity.created(null).body(dto);
    }

    @GetMapping("")
    public ResponseEntity<List<RoleDto>> getAllRoles() {

        List<RoleDto> dtos = roleService.getRoles();

        return ResponseEntity.ok().body(dtos);
    }

    @DeleteMapping("/{rolename}")
    public void deleteRole(@PathVariable(name = "rolename") String rolename) {

        roleService.deleteRoleByIdRolename(rolename);
    }
}
