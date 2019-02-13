package com.jefflee.util;

import java.text.DecimalFormat;

public class NumberUtil {

	public static Double reserveTwo(Double number) {
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.valueOf(df.format(number));
	}
}
