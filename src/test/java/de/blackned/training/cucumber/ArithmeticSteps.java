package de.blackned.training.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class ArithmeticSteps {
    private Arithmetic arithmetic;
    private int lastResult;

    @Before
    public void setup() {
        arithmetic = new Arithmetic();
    }

    @After
    public void tearDown() {
        arithmetic = null;
    }

    @When("^I add ([-]{0,1}\\d+) and ([-]{0,1}\\d+)$")
    public void i_add_and(int arg1, int arg2) {
        lastResult = arithmetic.add(arg1, arg2);
    }

    @Then("^the result is ([-]{0,1}\\d+)$")
    public void the_result_is(int arg1) {
        Assert.assertEquals(lastResult, arg1);
    }

    @Then("^the sum is ([-]{0,1}\\d+)$")
    public void the_sum_is(int arg1) {
        the_result_is(arg1);
    }
}
