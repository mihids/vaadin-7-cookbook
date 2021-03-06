package com.analemma.cookies;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

  @WebServlet(value = "/*", asyncSupported = true)
  @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class,
      widgetset = "com.analemma.cookies.AppWidgetSet")
  public static class Servlet extends VaadinServlet {
  }

  @Override
  public void init(final VaadinRequest request) {
    final TabsURL tabsURL = new TabsURL();
    setContent(tabsURL);
    tabsURL.selectTab();

    getPage().addUriFragmentChangedListener(new UriFragmentChangedListener() {
      @Override
      public void uriFragmentChanged(final UriFragmentChangedEvent event) {
        tabsURL.selectTab();
      }
    });
  }
}
