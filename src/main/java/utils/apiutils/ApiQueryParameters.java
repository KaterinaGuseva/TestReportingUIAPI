package utils.apiutils;

import constants.ApiParamName;

import java.util.HashMap;
import java.util.Map;

public class ApiQueryParameters {

    public static Map<String, String> getMapParamsPostedTest(String sessionId, String projectName,
                                                             String testName, String methodName, String env) {
        Map<String, String> queryParamsPostedTest = new HashMap<>();
        queryParamsPostedTest.put(ApiParamName.SID, sessionId);
        queryParamsPostedTest.put(ApiParamName.PROJECT_NAME, projectName);
        queryParamsPostedTest.put(ApiParamName.TEST_NAME, testName);
        queryParamsPostedTest.put(ApiParamName.METHOD_NAME, methodName);
        queryParamsPostedTest.put(ApiParamName.ENVIRONMENT, env);
        return queryParamsPostedTest;
    }

    public static Map<String, String> getMapParamsSendApplication(String testId, String contentBase64,
                                                                  String contentType) {
        Map<String, String> queryParamsSendApplication = new HashMap<>();
        queryParamsSendApplication.put(ApiParamName.TEST_ID, testId);
        queryParamsSendApplication.put(ApiParamName.CONTENT, contentBase64);
        queryParamsSendApplication.put(ApiParamName.CONTENT_TYPE, contentType);
        return queryParamsSendApplication;
    }
}
