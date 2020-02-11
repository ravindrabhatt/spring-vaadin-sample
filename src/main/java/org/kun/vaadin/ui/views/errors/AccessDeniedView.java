package org.kun.vaadin.ui.views.errors;

import org.kun.vaadin.ui.MainView;
import org.kun.vaadin.ui.exceptions.AccessDeniedException;
import org.kun.vaadin.ui.utils.Constants;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.*;
import com.vaadin.flow.templatemodel.TemplateModel;

import javax.servlet.http.HttpServletResponse;

@Tag("access-denied-view")
@JsModule("./src/views/errors/access-denied-view.js")
@ParentLayout(MainView.class)
@PageTitle(Constants.TITLE_ACCESS_DENIED)
@Route
public class AccessDeniedView extends PolymerTemplate<TemplateModel> implements HasErrorParameter<AccessDeniedException> {

	@Override
	public int setErrorParameter(BeforeEnterEvent beforeEnterEvent, ErrorParameter<AccessDeniedException> errorParameter) {
		return HttpServletResponse.SC_FORBIDDEN;
	}
}
