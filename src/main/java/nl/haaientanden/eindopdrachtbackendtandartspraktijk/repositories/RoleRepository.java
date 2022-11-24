package nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
