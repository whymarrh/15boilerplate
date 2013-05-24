package com.whymarrh.boilerplate;

import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.System;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServer extends Object {

	/**
	 * Run the server.
	 */
	public static void main(String[] args) throws Exception {
		final int argc = 3;
		if (args.length != argc) {
			// not enough arguments
			System.out.println("Moar arguments!");
			System.exit(argc);
		}

		SelectChannelConnector conn = new SelectChannelConnector();
		int port = Integer.parseInt(args[0]);
		conn.setPort(port);
		Connector[] conns = { conn };

		Server server = new Server();
		server.setConnectors(conns);
		server.setHandler(new WebAppContext(args[1], args[2]));
		int timeoutMS = 2000;
		server.setGracefulShutdown(timeoutMS);
		server.start(); // throws java.lang.Exception
		server.join();  // throws java.lang.InterruptedException
	}

}
