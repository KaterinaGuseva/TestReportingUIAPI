package data;

public enum Endpoints {

    GET_TOKEN("token/get"),
    GET_TESTS("test/get/json"),
    GET_TEST("test/put"),
    SEND_APPLICATION("test/put/attachment"),
    SEND_LOG("test/put/log");
    
    private String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
