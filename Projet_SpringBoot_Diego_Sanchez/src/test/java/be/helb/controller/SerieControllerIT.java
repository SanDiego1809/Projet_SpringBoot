package be.helb.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class SerieControllerIT
{
    private String getToken()
    {
        return Jwts.builder()
                .setSubject("user")
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
                .signWith(SignatureAlgorithm.HS512,"SecretKeyToGenJWTsSecretKeyToGenJWTs".getBytes())
                .compact();
    }

    @Test
    public void whenRequestGetAll_ThenOK()
    {
        RestAssured.with()
                .auth()
                .oauth2(getToken())
                .when()
                .request("GET", "/series")
                .then()
                .statusCode(200);
    }
    @Test
    public void whenRequestGetSeriesByGenre_ThenOK()
    {
        RestAssured.with()
                .auth()
                .oauth2(getToken())
                .when()
                .request("GET", "/seriesByGenre/test")
                .then()
                .statusCode(200);
    }

    @Test
    public void whenRequestPost_thenCreated()
    {
        String serie = "{ \"genre\": \"string\",\"id\": 0,\"language\": \"string\",\"name\": \"test\",\"numberOfVolumes\": 0,\"origin\": \"string\" }";

        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .body(serie)
                .when()
                .post("/serie")
                .then()
                .statusCode(200);
    }
    @Test
    public void whenRequestGetAuthorByName_ThenOK()
    {
        RestAssured.with()
                .auth()
                .oauth2(getToken())
                .when()
                .request("GET", "/serie/test")
                .then()
                .statusCode(200);
    }
    @Test
    public void whenRequestDeleteByName_thenDeleted() {

        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .when()
                .delete("/serieName/test")
                .then().statusCode(200);
    }
}
