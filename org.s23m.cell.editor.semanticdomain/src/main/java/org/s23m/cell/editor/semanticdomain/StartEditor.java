/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Gmodel.
 *
 * The Initial Developer of the Original Code is
 * Sofismo AG (Sofismo).
 * Portions created by the Initial Developer are
 * Copyright (C) 2009-2011 Sofismo AG.
 * All Rights Reserved.
 *
 * Contributor(s):
 * Andrew Shewring
 *
 * ***** END LICENSE BLOCK ***** */
package org.s23m.cell.editor.semanticdomain;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Used during development to start the web application in an embedded Jetty instance.
 *
 * @see http://vaadin.com/forum/-/message_boards/view_message/119526
 */
public class StartEditor {

	private static final int PORT = 8888;

	private static final String CONTEXT_PATH = "/development";

	private static final String SERVLET_PATH = "/app";

    public static void main(final String[] args) throws Exception {
    	final String inMemoryInstances = System.getProperty("gmodel.development.inmemory.artefacts");
    	if (inMemoryInstances == null) {
    		throw new IllegalStateException("In-memory flag missing");
    	}
    	System.out.println("inMemoryInstances: " + inMemoryInstances);
    	final String localDatabase = System.getProperty("gmodel.development.local.database");
    	if (localDatabase == null) {
    		throw new IllegalStateException("Local database flag missing");
    	}
    	System.out.println("localDatabase: " + localDatabase);

    	startServer();

    	System.out.println("Server started.");
    	final String url = "http://localhost:" + PORT + CONTEXT_PATH + SERVLET_PATH;
    	System.out.println("The web application is available at:\n\n" + url);
    }

    private static void startServer() throws Exception {

    	/*
    	 * Original code commented out as we don't want a hard dependency on Jetty.
    	 * Vaadin dependencies from Vaadin plugin include embedded Jetty (org.mortbay.jetty)
    	 */
    	/*
    	final Server server = new Server(PORT);

    	final WebAppContext webAppContext = new WebAppContext();
    	webAppContext.setContextPath(CONTEXT_PATH);
    	webAppContext.setResourceBase("./src/");

    	final ServletHolder vaadinLoader = new ServletHolder(new ApplicationServlet());
    	vaadinLoader.setInitParameter("application", Editor.class.getName());
    	webAppContext.addServlet(vaadinLoader, SERVLET_PATH + "/*");

        // See Chapter 4.8.3. of Book of Vaadin ("Deployment Descriptor")
    	webAppContext.addServlet(vaadinLoader, "/VAADIN/*");

    	server.setHandler(webAppContext);
    	server.start();
    	*/

    	final Class<?> serverClass = Class.forName("org.mortbay.jetty.Server");
    	final Constructor<?> serverConstructor = serverClass.getConstructor(int.class);
    	final Object server = serverConstructor.newInstance(PORT);

    	final Class<?> webAppContextClass = Class.forName("org.mortbay.jetty.webapp.WebAppContext");
    	final Object webAppContext = webAppContextClass.newInstance();
    	webAppContextClass.getMethod("setContextPath", String.class).invoke(webAppContext, CONTEXT_PATH);
    	webAppContextClass.getMethod("setResourceBase", String.class).invoke(webAppContext, "./src/");


    	final Class<?> applicationServletClass = Class.forName("com.vaadin.terminal.gwt.server.ApplicationServlet");
    	final Object applicationServlet = applicationServletClass.newInstance();
    	final Class<?> servletHolderClass = Class.forName("org.mortbay.jetty.servlet.ServletHolder");
    	final Class<?> servletClass = Class.forName("javax.servlet.Servlet");
    	final Constructor<?> servletHolderConstructor = servletHolderClass.getConstructor(servletClass);
    	final Object vaadinLoader = servletHolderConstructor.newInstance(applicationServlet);
    	final Method setInitParameterMethod = servletHolderClass.getMethod("setInitParameter", String.class, String.class);
    	setInitParameterMethod.invoke(vaadinLoader, "application", Editor.class.getName());

    	final Method addServletMethod = webAppContextClass.getMethod("addServlet", servletHolderClass, String.class);
    	addServletMethod.invoke(webAppContext, vaadinLoader, SERVLET_PATH + "/*");

        // See Chapter 4.8.3. of Book of Vaadin ("Deployment Descriptor")
    	addServletMethod.invoke(webAppContext, vaadinLoader, "/VAADIN/*");
    	//addServletMethod.invoke(webAppContext, vaadinLoader, "/VAADIN/themes/org.s23m.cell.editor.semanticdomaintheme/*");

    	final Class<?> handlerClass = Class.forName("org.mortbay.jetty.Handler");
    	serverClass.getMethod("setHandler", handlerClass).invoke(server, webAppContext);
    	serverClass.getMethod("start").invoke(server);
    }
}