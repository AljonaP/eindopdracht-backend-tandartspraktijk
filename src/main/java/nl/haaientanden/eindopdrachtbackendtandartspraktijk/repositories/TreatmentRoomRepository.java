package nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.TreatmentRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRoomRepository extends JpaRepository<TreatmentRoom, Long> {
}
