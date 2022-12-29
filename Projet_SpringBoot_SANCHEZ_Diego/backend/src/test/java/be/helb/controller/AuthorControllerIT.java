package be.helb.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class AuthorControllerIT
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
                    .request("GET", "/authors")
                    .then()
                    .statusCode(200);
    }
    @Test
    public void whenRequestGetAlbumsByCountry_ThenOK()
    {
        RestAssured.with()
                .auth()
                .oauth2(getToken())
                .when()
                .request("GET", "/albumsByCountry/test")
                .then()
                .statusCode(200);
    }
    @Test
    public void whenRequestPost_thenCreated()
    {
        String author = "{ \"id\": 0, \"country\": \"belgium\", \"dateOfBirth\": \"2022-12-24\", \"dateOfDeath\": \"2022-12-25\", \"firstName\": \"test\", \"name\": \"test\" }";

        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .body(author)
                .when()
                .post("/author")
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
                .request("GET", "/author/test")
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
                .delete("/authorName/test")
                .then().statusCode(200);
    }

}
