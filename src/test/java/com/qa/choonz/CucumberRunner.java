package com.qa.choonz;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		 features = { "classpath:Features" },
		 glue = { "classpath:com.qa.choonz" },
		 snippets = SnippetType.CAMELCASE,
		 monochrome = true
		 )

public class CucumberRunner {

}