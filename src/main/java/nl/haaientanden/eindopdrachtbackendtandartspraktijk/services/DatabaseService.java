package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos.FileUploadResponse;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.FileDocument;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.FileDocumentRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;


@Service
public class DatabaseService {

    private final FileDocumentRepository fileDocumentRepository;

    public DatabaseService(FileDocumentRepository fileDocumentRepository) {

        this.fileDocumentRepository = fileDocumentRepository;
    }


    public Collection<FileDocument> getALlFromDB() {

        return fileDocumentRepository.findAll();
    }

    public FileDocument uploadFileDocument(MultipartFile file) throws IOException {

        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        if (!(fileDocumentRepository.existsByFileName(name))) {
            FileDocument fileDocument = new FileDocument();
            fileDocument.setFileName(name);
            fileDocument.setDocFile(file.getBytes());

            fileDocumentRepository.save(fileDocument);

            return fileDocument;
        }

        throw new RuntimeException("The file is already exist in the Database.");
    }

    public ResponseEntity<byte[]> singleFileDownload(String fileName, HttpServletRequest request) {

        FileDocument document = fileDocumentRepository.findByFileName(fileName);
        String mimeType = request.getServletContext().getMimeType(document.getFileName());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + document.getFileName()).body(document.getDocFile());
    }

    public List<FileUploadResponse> createMultipleUpload(MultipartFile[] files) {

        List<FileUploadResponse> uploadResponseList = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {

            String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            FileDocument fileDocument = new FileDocument();
            fileDocument.setFileName(name);
            try {
                fileDocument.setDocFile(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            fileDocumentRepository.save(fileDocument);

            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/haaientanden/downloadFromDB/").path(name).toUriString();

            String contentType = file.getContentType();

            FileUploadResponse response = new FileUploadResponse(name, contentType, url);

            uploadResponseList.add(response);
        });

        return uploadResponseList;
    }

    public Resource downLoadFileDatabase(String fileName) {

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(fileName).toUriString();
        Resource resource;
        try {
            resource = new UrlResource(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if (resource.exists() && resource.isReadable()) {

            return resource;
        } else {
            throw new RuntimeException("the file doesn't exist or not readable");
        }
    }
}
