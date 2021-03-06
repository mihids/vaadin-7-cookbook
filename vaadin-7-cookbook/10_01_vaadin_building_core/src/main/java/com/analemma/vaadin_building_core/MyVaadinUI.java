package com.analemma.vaadin_building_core;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

  @WebServlet(value = "/*", asyncSupported = true)
  @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class,
      widgetset = "com.analemma.vaadin_building_core.AppWidgetSet")
  public static class Servlet extends VaadinServlet {
  }

  private PageLayout pageLayout;

  @Override
  protected void init(final VaadinRequest request) {
    pageLayout = new PageLayout();
    setContent(pageLayout);
  }

  public PageLayout getPageLayout() {
    return pageLayout;
  }

  public static MyVaadinUI getCurrent() {
    return (MyVaadinUI) UI.getCurrent();
  }
}
