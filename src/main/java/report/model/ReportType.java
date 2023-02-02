package report.model;

import javax.ws.rs.core.MediaType;

public enum ReportType {
    CSV(MediaType.TEXT_PLAIN),
    HTML(MediaType.TEXT_HTML),
    JSON(MediaType.APPLICATION_JSON),
    XML(MediaType.APPLICATION_XML);

    private final String contentType;

    public String getContentType() {
        return contentType;
    }

    ReportType(String contentType) {
        this.contentType = contentType;
    }
}
