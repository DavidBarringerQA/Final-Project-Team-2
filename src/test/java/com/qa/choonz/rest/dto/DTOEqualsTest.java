package com.qa.choonz.rest.dto;

import org.junit.jupiter.api.Test;
import nl.jqno.equalsverifier.EqualsVerifier;

class DTOEqualsTest{

	@Test
	void DTOEquals(){
		EqualsVerifier.simple().forPackage("com.qa.choonz.rest.dto").verify();
	}
}
