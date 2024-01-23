package com.gorest.gorestinfo;

import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SerenityRunner.class)
public class UsersCRUDTestWithSteps extends TestBase {

    static String name = TestUtils.getRandomValue() + "Raveena";
    static String email = TestUtils.getRandomValue() + "raveena@gmail.com";
    static String gender = "female";
    static String status = "active";

    static int userId;

    @Steps
    UsersSteps steps;
    @Title("This will create new user")
    @Test
    public void test001(){

        ValidatableResponse response = steps.createUser(name, email,gender,status).statusCode(201);

        userId = response.extract().jsonPath().getInt("id");
        System.out.println(userId);
    }

    @Title("Verify the user added to the application")
    @Test
    public void test002(){

        String userList = steps.getUserInfoByName(userId);

        Assert.assertEquals(name, userList);
    }

    @Title("Update and verify user information")
    @Test
    public void test003(){

        steps.partiallyUpdateUserInformation(userId, name, email, gender, status).statusCode(200);

        String userList = steps.getUserInfoByName(userId);

        Assert.assertEquals(name, userList);
    }

    @Title("Delete the user and verify if the user deleted")
    @Test
    public void test004(){

        steps.deleteUser(userId).statusCode(204);
        steps.getUserById(userId).statusCode(404);
    }
}
