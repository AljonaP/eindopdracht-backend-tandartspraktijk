package nl.haaientanden.eindopdrachtbackendtandartspraktijk.dtos;

public class FileUploadResponse {
    String filename;
    String contentType;
    String url;

    public FileUploadResponse(String filename, String contentType, String url) {
        this.filename = filename;
        this.contentType = contentType;
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
