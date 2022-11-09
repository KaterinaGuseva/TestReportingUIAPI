package utils.jsutils;

public enum ScriptMethods {

    CLOSE_WINDOW("window.close()");

    private String methodName;

    ScriptMethods(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }
}
