package com.gorest.gorestinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UsersPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class UsersSteps {


    @Step("creating user with name : {0}, Email : {1}, Gender : {2}, status : {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {
        UsersPojo usersPojo = new UsersPojo();
        usersPojo.setName(name);
        usersPojo.setEmail(email);
        usersPojo.setGender(gender);
        usersPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5ccd79c4b626c0178c68155757d8e72e7172d4baf3380a31e4e23632db81a6a0")
                .body(usersPojo)
                .when()
                .post(EndPoints.GET_ALL_USERS)
                .then().log().all();
    }

    @Step("Getting user information with UserId : {0}")
    public String getUserInfoByName(int userId) {

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5ccd79c4b626c0178c68155757d8e72e7172d4baf3380a31e4e23632db81a6a0")
                .pathParam("userId", userId)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_Id)
                .then().log().all().statusCode(200)
                .extract().path("name");

    }

    @Step("Updating user with UserId : {0}, name : {1}, Email : {2}, Gender : {3}, status : {4}")
    public ValidatableResponse partiallyUpdateUserInformation(int userId, String name, String email, String gender,String status) {

        UsersPojo usersPojo = new UsersPojo();
        usersPojo.setName(name);
        usersPojo.setEmail(email);
        usersPojo.setGender(gender);
        usersPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5ccd79c4b626c0178c68155757d8e72e7172d4baf3380a31e4e23632db81a6a0")
                .pathParam("userId", userId)
                .body(usersPojo)
                .when()
                .patch(EndPoints.UPDATE_USER_BY_Id)
                .then().log().all();
    }

    @Step("Deleting user information with UserId : {0}")
    public ValidatableResponse deleteUser(int userId) {

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5ccd79c4b626c0178c68155757d8e72e7172d4baf3380a31e4e23632db81a6a0")
                .pathParam("userId", userId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then().log().all();
    }

    @Step("Verifying user information with UserId : {0}")
    public ValidatableResponse getUserById(int userId) {

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 5ccd79c4b626c0178c68155757d8e72e7172d4baf3380a31e4e23632db81a6a0")
                .pathParam("userId", userId)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_Id)
                .then().log().all();

    }
}
