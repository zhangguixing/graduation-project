package com.jefflee.util;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtil {

	public static void copyProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target);
		return;
	}

	public static void copyPropertiesSelective(Object source, Object target) {
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper sourceBean = new BeanWrapperImpl(source);
		PropertyDescriptor[] propertyDescriptors = sourceBean.getPropertyDescriptors();

		Set<String> nullPropertyNameSet = new HashSet<String>();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String propertyName = propertyDescriptor.getName();
			if (null == sourceBean.getPropertyValue(propertyName))
				nullPropertyNameSet.add(propertyName);
		}
		String[] nullPropertyNames = new String[nullPropertyNameSet.size()];
		return nullPropertyNameSet.toArray(nullPropertyNames);
	}

}
