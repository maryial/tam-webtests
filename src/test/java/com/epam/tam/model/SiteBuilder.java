package com.epam.tam.model;

//Builder example
public abstract class SiteBuilder {
	protected Site site;
	
	public Site getSite() {
		return site;
	}
	
	public void createNewSite() {
		site = new Site();
	}
	
	public abstract void addName();
	public abstract void addAddress();
	public abstract void addNumber();

}
