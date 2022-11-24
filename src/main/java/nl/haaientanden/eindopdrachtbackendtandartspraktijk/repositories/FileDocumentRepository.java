package nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import javax.transaction.Transactional;

@Transactional
public interface FileDocumentRepository extends JpaRepository<FileDocument, Long> {
    FileDocument findByFileName(String fileName);
    Boolean existsByFileName(String fileName);
}
