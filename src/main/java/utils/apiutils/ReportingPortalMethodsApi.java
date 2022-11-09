package utils.apiutils;

import constants.ApiParamName;
import data.Endpoints;
import io.restassured.http.ContentType;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class ReportingPortalMethodsApi {

    public static String getToken(String taskVariant) {
        return given()
                .queryParam(ApiParamName.GET_TOKEN_VARIANT, taskVariant)
                .when()
                .post(Endpoints.GET_TOKEN.getEndpoint())
                .then()
                .statusCode(SC_OK)
                .extract().asString();
    }

    public static <T> List<T> getTests(String projectId, Class<T> namePojoClass) {
        return given()
                .queryParam(ApiParamName.PROJECT_ID, projectId)
                .when()
                .contentType(ContentType.JSON)
                .post(Endpoints.GET_TESTS.getEndpoint())
                .then()
                .statusCode(SC_OK)
                .extract().jsonPath().getList("", namePojoClass);
    }

    public static String getIdPostedTest(Map<String, String> queryParamsPostedTestMap) {
        return given()
                .queryParams(queryParamsPostedTestMap)
                .when()
                .post(Endpoints.GET_TEST.getEndpoint())
                .then()
                .statusCode(SC_OK)
                .extract().asString();
    }

    public static void sendApplication(Map<String, String> queryParamsSendApplication) {
                given()
                .queryParams(queryParamsSendApplication)
                .when()
                .post(Endpoints.SEND_APPLICATION.getEndpoint())
                .then()
                .statusCode(SC_OK);
    }
}
