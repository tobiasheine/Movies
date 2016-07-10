package com.tobi.movies.steps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.Transform;
import cucumber.api.Transformer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class GherkinSteps {

    @Given("^I have (\\d+) cukes in my belly")
    public void I_have_cukes_in_my_belly(int cukes) {

    }

    @Given("^I enter (\\S+) and (\\S+)$")
    public void I_type_user_and_password(String userName, String password) {

    }

    @Then("^I have an awesome test")
    public void awesome_test() {

    }

    @Given("^I have some employees$")
    public void I_have_employees(DataTable dataTable) throws Throwable {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            int id = Integer.valueOf(row.get("id"));
            String firstName = row.get("firstName");
            String lastName = row.get("lastName");
        }
    }

    @Given("^the following animals in this table:")
    public void the_following_animals_in_table(List<String> animals) {

    }

    @Given("^I want to transform this (\\S+) to a date")
    public void transform_string_to_date(@Transform(DateFormatter.class) Date date) {

    }


    public static class DateFormatter extends Transformer<Date> {

        @Override
        public Date transform(String s) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            try {
                return simpleDateFormat.parse(s);
            } catch (ParseException e) {
                return null;
            }
        }
    }

}
