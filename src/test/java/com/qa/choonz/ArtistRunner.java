package com.qa.choonz;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		 features = { "classpath:src/test/resources/Features/Artists/artistCreate.feature" },
		 glue = { "classpath:com.qa.choonz.ArtistStepDefs" }
		 )

public class ArtistRunner {

}