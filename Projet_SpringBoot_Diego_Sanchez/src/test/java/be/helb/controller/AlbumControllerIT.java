package be.helb.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class AlbumControllerIT
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
                        .request("GET", "/albums")
                        .then()
                        .statusCode(200);
        }
        @Test
        public void whenRequestGetAlbumBySerieId_ThenOK()
        {
            RestAssured.with()
                    .auth()
                    .oauth2(getToken())
                    .when()
                    .request("GET", "/albumBySerieId/1")
                    .then()
                    .statusCode(200);
        }

        @Test
        public void whenRequestPost_thenCreated()
        {
            String album = "{\"authors\": [ {\"country\": \"string\",\"dateOfBirth\": \"2022-12-25\",\"dateOfDeath\": \"2022-12-25\",\"firstName\": \"string\",\"id\": 19,\"name\": \"test\"}],\"dateOfPublication\": \"2022-12-25\",\"editor\": \"string\",\"id\": 0,\"name\": \"test\",\"number\": 0,\"numberOfPages\": 0,\"serie\": {\"genre\": \"string\",\"id\": 8,\"language\": \"string\",\"name\": \"string\",\"numberOfVolumes\": 0,\"origin\": \"string\"}}";

            given().auth()
                    .oauth2(getToken())
                    .contentType(ContentType.JSON)
                    .body(album)
                    .when()
                    .post("/album")
                    .then()
                    .statusCode(200);
        }

        @Test
        public void whenRequestGetAlbumByName_ThenOK()
        {
            RestAssured.with()
                    .auth()
                    .oauth2(getToken())
                    .when()
                    .request("GET", "/album/test")
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
                    .delete("/albumName/test")
                    .then().statusCode(200);
        }
}
