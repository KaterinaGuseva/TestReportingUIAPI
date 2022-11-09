package data;

public enum ContentType {

    TYPE_IMAGE("image/png"),
    TYPE_TEXT("text/html");

    private String contentType;

    ContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
