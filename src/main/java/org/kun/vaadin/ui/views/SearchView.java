package org.kun.vaadin.ui.views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.kun.vaadin.ui.MainView;
import org.kun.vaadin.ui.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route(value = Constants.PAGE_SEARCH, layout = MainView.class)
@PageTitle(Constants.TITLE_SEARCH)
@RouteAlias(value = Constants.PAGE_ROOT, layout = MainView.class)
@JsModule("./styles/shared-styles.js")
@CssImport(value = "./styles/form-item.css", themeFor = "vaadin-form-item")
public class SearchView extends VerticalLayout {
  Logger LOG = LoggerFactory.getLogger(SearchView.class);

  private HorizontalLayout inputFormWrapper = new HorizontalLayout();
  private VerticalLayout outputWrapper = new VerticalLayout();

  public SearchView() {
    TextField trackingNumberField = new TextField();
    trackingNumberField.setPlaceholder("tracking number");


    FormLayout inputLayout = inputFieldLayout(trackingNumberField);
    Button submitButton = setUpFormSubmission(trackingNumberField);

    updateLayout(inputLayout, submitButton);
  }

  private void updateLayout(FormLayout input, Button button) {
    inputFormWrapper = inputFormLayout(input, button);
    add(inputFormWrapper);
  }

  private HorizontalLayout inputFormLayout(FormLayout input, Button button) {
    inputFormWrapper.setWidth("90%");
    HorizontalLayout left = new HorizontalLayout();
    left.setWidth("30%");
    VerticalLayout centre = new VerticalLayout();
    centre.setWidth("50%");
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    horizontalLayout.getStyle().set("margin-left", "20%");
    horizontalLayout.getStyle().set("margin-top", "0");
    HorizontalLayout horizontalLayout2 = new HorizontalLayout();
    horizontalLayout2.getStyle().set("margin-left", "20%");
    horizontalLayout2.add(button);

    HorizontalLayout right = new HorizontalLayout();
    right.setWidth("20%");

    centre.add(input, horizontalLayout, horizontalLayout2);

    inputFormWrapper.add(left, centre, right);
    return inputFormWrapper;
  }

  private FormLayout inputFieldLayout(TextField trackingNumberField) {
    FormLayout layoutWithFormItems = new FormLayout();

    layoutWithFormItems.addFormItem(trackingNumberField, "tracking number");
    layoutWithFormItems.setResponsiveSteps(new FormLayout.ResponsiveStep("15em",
        1,
        FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
    return layoutWithFormItems;
  }

  private Button setUpFormSubmission(TextField input) {
    ComponentEventListener<ClickEvent<Button>> onSubmitListener = e -> {
      String trackingNumber = input.getValue();
      try {
        clearExisting(input);
        outputWrapper.setWidth("70%");
        outputWrapper.getStyle().set("margin-left", "2%");

      } catch (Exception ex) {
        LOG.debug("", ex);
        showNotification(trackingNumber);
      }
    };

    return new Button("Search", onSubmitListener);
  }


  private void clearExisting(TextField input) {
    input.clear();
    outputWrapper.removeAll();
    remove(outputWrapper);
  }

  private void showNotification(String trackingNumber) {
    Notification.show("error" + ", " + trackingNumber,
        3000, Notification.Position.MIDDLE);
  }
}
