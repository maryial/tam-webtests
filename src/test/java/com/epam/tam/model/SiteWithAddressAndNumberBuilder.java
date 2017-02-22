package com.epam.tam.model;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class SiteWithAddressAndNumberBuilder extends SiteBuilder {

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
		site.setNumber(ThreadLocalRandom.current().nextInt(100, 999 + 1) +
				"-" + ThreadLocalRandom.current().nextInt(0, 99 + 1) +
				"-" + ThreadLocalRandom.current().nextInt(0, 99 + 1));		
	}
}
