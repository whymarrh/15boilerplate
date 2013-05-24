package com.whymarrh.boilerplate.controller;

import com.whymarrh.boilerplate.Utils;
import com.whymarrh.boilerplate.ViewRenderer;

import java.io.IOException;
import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class ExampleController extends HttpServlet {

	// the version of this object
	public static final long serialVersionUID = 0L;

	/**
	 * Process GET requests.
	 */
	@Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ask for this response to not be cached
		Utils.pleaseDontCache(response);
		// set some values
		final HashMap<String, Object> values = new HashMap<String, Object>();
		values.put("title", "15boilerplate");
		values.put("heading", "15boilerplate");
		values.put("message", "This page was rendered dynamically.");
		// render the view
		response.setContentType("text/html");
		ViewRenderer.render(response, "example", values);
	}

}
