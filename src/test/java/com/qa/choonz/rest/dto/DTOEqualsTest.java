package com.qa.choonz.rest.dto;

import org.junit.jupiter.api.Test;
import nl.jqno.equalsverifier.EqualsVerifier;

public class DTOEqualsTest{

	@Test
	public void DTOEquals(){
		EqualsVerifier.simple().forPackage("com.qa.choonz.rest.dto").verify();
	}
}
