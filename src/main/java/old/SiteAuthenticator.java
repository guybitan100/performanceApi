/*
package old;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

public class SiteAuthenticator {
    protected static final Logger LOGGER = Logger.getLogger(SiteAuthenticator.class);

    private static SiteAuthenticator authenticator;
    private User currentUser = new User();

    private SiteAuthenticator() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    public static SiteAuthenticator getInstance() {
        if (authenticator == null) {
            authenticator = new SiteAuthenticator();
        }
        return authenticator;
    }

    synchronized public SiteAuthenticator login() {
        String loginCookie = login(user.getUserName(), user.getPassword());
        LOGGER.info(String.format("logged in as user - %s", user.userName));
        return this;
    }


    private String login(String userName, String password) {
        checkLoginPageAvailable();
        RestAssured.baseURI = Configuration.getEnvArg("site_base");
        Response response = RestAssured.given()
                .params("j_username", userName)
                .params("j_password", password)
                .params("redirect_response", "true")
                .params("submit", "Login")
                .contentType("application/x-www-form-urlencoded")
                .when().post("/j_spring_security_check")
                .then().assertThat().header("Location", Matchers.not("login_error=1"))
                .extract().response();
        return response.cookies().get("JSESSIONID");
    }

    private void checkLoginPageAvailable() {
        RestAssured.baseURI = Configuration.getEnvArg("site_base");
        RestAssured.given().
                when().get().
                then().assertThat().statusCode(200);
    }
}*/
