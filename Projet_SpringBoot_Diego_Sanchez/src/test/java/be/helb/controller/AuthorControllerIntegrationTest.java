package be.helb.controller;

import be.helb.model.Author;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class AuthorControllerIntegrationTest
{
    @Test
    public void whenRequestGetAll_ThenOK()
    {
        RestAssured.with().when().request("GET", "/authors").then().statusCode(200);
    }

    @Test
    public void whenRequestGetAuthorById_ThenOK()
    {
        RestAssured.with().when().request("GET", "/authors/19").then().statusCode(200);
    }
    @Test
    public void whenRequestGetAuthorByName_ThenOK()
    {
        RestAssured.with().when().request("GET", "/author/Goscinny").then().statusCode(200);
    }
    /*@Test
    public void whenRequestPost_thenCreated()
    {
        RestAssured.with().body(new Author())
                .when()
                .request("POST", "/author")
                .then()
                .statusCode(201);
    }*/

}
