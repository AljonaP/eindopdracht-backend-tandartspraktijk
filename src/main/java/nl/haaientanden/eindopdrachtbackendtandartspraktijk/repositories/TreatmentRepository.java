package nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
