package com.epam.tam.exception;

import com.epam.tam.model.DoctorType;

public class UnsupportedDoctorTypeException extends Exception {
	public UnsupportedDoctorTypeException(DoctorType type) {
		super(type.toValue() + " is not supported");
	}
}
