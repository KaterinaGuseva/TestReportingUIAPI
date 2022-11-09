package data;

public enum Attributes {

    INNER_TEXT("innerText"),
    SEARCH("search");

    private String attributeType;

    Attributes(String attributeType) {
        this.attributeType = attributeType;
    }

    public String getAttribute() {
        return attributeType;
    }
}
