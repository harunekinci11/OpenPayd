package com.OpenPayd.stepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class APIStepDefs {

    @When("I get a list of blog posts using the API endpoint")
    public void i_get_a_list_of_blog_posts_using_the_API_endpoint() {

    }

    @Then("user {string} should have {string} posts")
    public void user_should_have_posts(String string1, String string2) {
        int int_post_num = Integer.parseInt(string2);
                given().
                when().
                get("https://jsonplaceholder.typicode.com/posts?userId="+string1).
                then().
                assertThat().body("userId", hasSize(int_post_num));
    }

    @Then("Each blog post should have a unique ID")
    public void each_blog_post_should_have_a_unique_ID() {
        int sum=0;
        for(int a=1;a<11;a++) {

            sum=sum+get_response_size("?userId="+a);

        }
        System.out.println("sum is "+sum);
        System.out.println("array sum  is "+get_response_size(""));

        Assert.assertEquals(sum, get_response_size(""));

    }
    public int get_response_size(String user) {


        Response response = given().
                when().
                get("https://jsonplaceholder.typicode.com/posts"+user);

        List<String> jsonResponse = response.jsonPath().getList("$");

        System.out.println(jsonResponse.size());
        int total=jsonResponse.size();
        return total;

    }


}

