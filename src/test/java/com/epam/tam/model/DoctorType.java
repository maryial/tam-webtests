package com.epam.tam.model;

import com.google.common.base.CaseFormat;

public enum DoctorType {
	
	HCP,
	SITE_ADMIN,
	ORG_ADMIN;
	
    public static DoctorType forValue(String value)
    {
        return valueOf(value.toUpperCase());
    }

    public String toValue()
    {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name());
    }

}
