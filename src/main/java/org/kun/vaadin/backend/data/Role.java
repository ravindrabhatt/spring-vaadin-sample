package org.kun.vaadin.backend.data;

public class Role {
	public static final String NORMAL_USER = "normal_user";
	// This role implicitly allows access to all views.
	public static final String ADMIN = "admin";

	private Role() {
		// Static methods and fields only
	}

	public static String[] getAllRoles() {
		return new String[] {NORMAL_USER, ADMIN };
	}

}
