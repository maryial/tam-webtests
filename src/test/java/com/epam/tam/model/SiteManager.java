package com.epam.tam.model;

public class SiteManager {
	private SiteBuilder builder;
	
	public void setSiteBuilder(SiteBuilder builder) {
		this.builder = builder;
	}
	
	public Site getSite() {
		constructSite();
		return builder.getSite();
	}
	
	private void constructSite() {
		builder.createNewSite();
		builder.addName();
		builder.addAddress();
		builder.addNumber();
	}
	
}
