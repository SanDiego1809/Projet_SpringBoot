package be.helb.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class UserControllerIT
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
                    .request("GET", "/users")
                    .then()
                    .statusCode(200);
    }

    @Test
    public void whenRequestPost_thenCreated()
    {
        String user = "{  \"password\": \"test\", \"username\": \"test\"}";

        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .body(user)
                .when()
                .post("/signup")
                .then()
                .statusCode(200);
    }
    @Test
    public void whenRequestGetUserByName_ThenOK()
    {
        RestAssured.with()
                .auth()
                .oauth2(getToken())
                .when()
                .request("GET", "/user/test")
                .then()
                .statusCode(200);
    }

    @Test
    public void whenRequestUpdate_thenCreated() {
        String user = "{  \"password\": \"test\", \"username\": \"test\"}";
        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .body(user)
                .when()
                .put("/username/test")
                .then().statusCode(200);
    }
    @Test
    public void whenRequestDeleteByName_thenDeleted() {

        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(getToken())
                .when()
                .delete("/username/test")
                .then().statusCode(200);
    }
}
