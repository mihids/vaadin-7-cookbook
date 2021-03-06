package com.analemma.vaadin_spring_login;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedHttpSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.UI;

@Theme("mytheme")
@SuppressWarnings("serial")
@PreserveOnRefresh
public class MyVaadinUI extends UI {

  @WebServlet(value = "/*", asyncSupported = true, initParams = {@WebInitParam(
      name = "org.atmosphere.cpr.broadcasterCacheClass",
      value = "org.atmosphere.cache.UUIDBroadcasterCache")})
  @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class,
      widgetset = "com.analemma.vaadin_spring_login.AppWidgetSet")
  public static class Servlet extends VaadinServlet {
  }

  private ApplicationContext applicationContext;

  private static final Logger logger = LoggerFactory.getLogger(MyVaadinUI.class);

  @Override
  protected void init(VaadinRequest request) {
    logger.info("Init started");

    WrappedSession session = request.getWrappedSession();
    HttpSession httpSession = ((WrappedHttpSession) session).getHttpSession();
    ServletContext servletContext = httpSession.getServletContext();
    applicationContext =
        WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

    Navigator navigator = new Navigator(this, this);

    navigator.addView("login", LoginView.class);

    navigator.addView("user", UserView.class);

    navigator.navigateTo("login");
    setNavigator(navigator);
    
    logger.info("Init finished");
  }

  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }
}
