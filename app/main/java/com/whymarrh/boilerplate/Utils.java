package com.whymarrh.boilerplate;

import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Utils extends Object {

	// the newline character for all systems
	public static final String NEWLINE = "\r\n";
	// the root of the webapp
	public static final String APP_ROOT = "/";
	// a simple date formatter
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

	/**
	 * Reads a whole {@code InputStream} into a string.
	 * http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner.html
	 */
	public static String readStream(InputStream stream) throws IOException {
		Scanner s = new Scanner(stream, "UTF-8").useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
	/**
	 * Returns the given date in ISO format.
	 */
	public static String formatDate(Date date) {
		return formatter.format(date);
	}
	/**
	 * Ask for the response to not be cached by the client.
	 */
	public static void pleaseDontCache(HttpServletResponse res) {
		// set some headers
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		res.setDateHeader("Expires", 0);
		res.setHeader("Pragma", "no-cache");
	}
	/**
	 * Dumps the request to the user.
	 */
	public static void dumpRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/plain");
		PrintWriter out = res.getWriter();
		out.printf("You sent a %s request.%n", req.getMethod());
		out.println(dumpRequest(req));
	}
	/**
	 * Dump the request in plain text to the response.
	 */
	public static String dumpRequest(HttpServletRequest req) {
		StringBuilder s = new StringBuilder();
		s.append(NEWLINE);
		// the keys for the header fields
		Enumeration names = req.getHeaderNames();
		Enumeration values = null;
		// add all the headers
		while (names.hasMoreElements()) {
			String header = (String) names.nextElement();
			values = req.getHeaders(header);
			while (values.hasMoreElements()) {
				s.append(header + ": " + values.nextElement() + NEWLINE);
			}
		}
		// add all the request parameters
		Map<String, String[]> params = req.getParameterMap();
		if (params.size() > 0) {
			// space
			s.append(NEWLINE);
		}
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String[] vals = entry.getValue();
			String key = entry.getKey();
			for (String val : vals) s.append("" + key + ": " + val + NEWLINE);
		}
		return s.toString();
	}

}
