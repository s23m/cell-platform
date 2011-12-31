package org.s23m.cell.repository;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigValues {
	private static final String BUNDLE_NAME = "org.s23m.cell.repository.configvalues"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private ConfigValues() {
	}

	public static String getValue(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
