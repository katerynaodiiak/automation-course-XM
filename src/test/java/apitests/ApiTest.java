package apitests;

import api.StarWarsFilmsResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

public class ApiTest {

    private static final String BASE_URL = "https://swapi.dev";
    private static final String STAR_WARS_FILMS_ENDPOINT = "/api/films";
    private static final String ID = "1";

    @BeforeClass
    public void beforeClass(){
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void test(){
        StarWarsFilmsResponse responseBody = RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(STAR_WARS_FILMS_ENDPOINT)
                .pathParam("id", ID)
                //.log().all()
                .when()
                .get("/{id}")
                .then()
                //.log().all()
                .statusCode(SC_OK)
                .body("title", is("A New Hope")) //to try RestAssured validation :)
                .extract()
                .as(StarWarsFilmsResponse.class); //as you have this class in your suite, probably you wanted to do something like that :)
        assertFalse(responseBody.getPlanets().isEmpty());
    }

}
