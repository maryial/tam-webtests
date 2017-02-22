package com.epam.tam.model;

import org.apache.commons.lang3.RandomStringUtils;

public class SiteWithAddressBuilder extends SiteBuilder {

	@Override
	public void addName() {
		site.setName(RandomStringUtils.random(10, "abcdefghijklmnopqrstuxyz"));
	}

	@Override
	public void addAddress() {
		site.setAddress("Berlin, Unter den Linden 4");
	}

	@Override
	public void addNumber() {
		site.setNumber("");		
	}
}
