package com.gorest.gorestinfo;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SerenityRunner.class)
public class PostsCRUDTestWithSteps extends TestBase {

    static int user_id = 6029856;
    static String title = "I miss the dinner and the cost of the car both ways.";
    static String body = "I afflict those cattle. I'm hoping for some help. Like a bladder. I deny the caveat of the spike. They say a pile of drill. Hardly including inflammation. I light the thirty treasure. I create one beard. I help Ocer to be beautiful. Which of the following is a security agreement? Study who is voracious.";

    static int postId;

    @Steps
    PostsSteps steps;

    @Title("This will create new post")
    @Test
    public void test001(){

        ValidatableResponse response = steps.createPost(user_id, title, body).statusCode(201);
        postId = response.extract().path("id");
    }

    @Title("Verify the post added to the application")
    @Test
    public void test002(){

        String postList = steps.getPostByIdAndVerifyTitle(postId);

        Assert.assertEquals(title, postList);
    }

    @Title("Update and verify post information")
    @Test
    public void test003(){
        user_id = 6029857;
        title = title + "_updated";
        body = body + "_updated";

        steps.updatePost(postId,user_id, title, body).statusCode(200);

        String postList = steps.getPostByIdAndVerifyTitle(postId);

        Assert.assertEquals(title, postList);
    }

    @Title("Delete the post and verify if the post deleted")
    @Test
    public void test004(){

        steps.deletePost(postId).statusCode(204);
        steps.verifyingPostDeletedById(postId).statusCode(404);
    }
}
