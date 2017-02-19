package com.epam.tam.model;

public class Doctor {
	private String name;
	private String lastName;
	private String email;
	private String site;
	private boolean orgAdmin;
	private boolean siteAdmin;
	
	public Doctor(String name, String lastName, String email, String site, boolean orgAdmin, boolean siteAdmin) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.site = site;
		this.orgAdmin = orgAdmin;
		this.siteAdmin = siteAdmin;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getSite() {
		return site;
	}
	
	public boolean isOrgAdmin() {
		return orgAdmin;
	}
	
	public boolean isSiteAdmin() {
		return siteAdmin;
	}
}
