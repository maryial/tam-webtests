package util;

import com.google.common.base.CaseFormat;

public enum Browsers {

	CHROME,
	FIREFOX;
	
    public static Browsers forValue(String value)
    {
        return valueOf(value.toUpperCase());
    }

    public String toValue()
    {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name());
    }
}
