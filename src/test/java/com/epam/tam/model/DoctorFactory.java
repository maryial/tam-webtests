package com.epam.tam.model;

import com.epam.tam.exception.UnsupportedDoctorTypeException;
//factory example
public class DoctorFactory {
	
	public static Doctor getDoctor(String email, String name, String lastName, String siteName, DoctorType type) throws UnsupportedDoctorTypeException {
		switch (type) {
		case HCP:
			return new Doctor(name, lastName, email, siteName, false, false);
		case ORG_ADMIN:
			return new Doctor(name, lastName, email, siteName, true, false);
		case SITE_ADMIN:
			return new Doctor(name, lastName, email, siteName, false, true);
		default:
			throw new UnsupportedDoctorTypeException(type);
		}
	}
}
