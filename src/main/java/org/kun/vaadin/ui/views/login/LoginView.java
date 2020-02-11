package org.kun.vaadin.ui.views.login;

import org.kun.vaadin.app.security.SecurityUtils;
import org.kun.vaadin.ui.utils.Constants;
import org.kun.vaadin.ui.views.SearchView;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route
@PageTitle("Login")
@JsModule("./styles/shared-styles.js")
@Viewport(Constants.VIEWPORT)
public class LoginView extends LoginOverlay
	implements AfterNavigationObserver, BeforeEnterObserver {

	Logger LOG = LoggerFactory.getLogger(LoginView.class);

	public LoginView() {
		LoginI18n i18n = LoginI18n.createDefault();
		i18n.setHeader(new LoginI18n.Header());
		i18n.getHeader().setTitle("Sample");
		i18n.getHeader().setDescription("Login");
		i18n.setAdditionalInformation(null);
		i18n.setForm(new LoginI18n.Form());
		i18n.getForm().setSubmit("ログインする");
		i18n.getForm().setTitle("ログイン");
		i18n.getForm().setUsername("ログインメール");
		i18n.getForm().setPassword("パスワード");
		setI18n(i18n);
		setForgotPasswordButtonVisible(false);
		setAction("login");
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (SecurityUtils.isUserLoggedIn()) {
			event.forwardTo(SearchView.class);
		} else {
			setOpened(true);
		}
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		setError(
			event.getLocation().getQueryParameters().getParameters().containsKey(
				"error"));
	}

}
