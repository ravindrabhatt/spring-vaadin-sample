package org.kun.vaadin.app.security;

import org.kun.vaadin.backend.data.entity.User;

@FunctionalInterface
public interface CurrentUser {

	User getUser();
}
