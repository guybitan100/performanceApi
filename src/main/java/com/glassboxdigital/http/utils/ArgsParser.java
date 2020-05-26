/*
 User: Ophir
 Date: 30/01/12
 Time: 12:53
 */
package com.glassboxdigital.http.utils;

import com.beust.jcommander.JCommander;
import com.google.common.base.Joiner;
import org.slf4j.LoggerFactory;


public class ArgsParser {

	private ArgsParser() {}

	public static void parseArguments(Object params, String[] args) {
		JCommander jCommander = new JCommander(params);
		try {
			jCommander.parse(args);
		} catch (Exception e) {
			jCommander.usage();
			String message = "Failed parsing arguments for type " + params.getClass() + " with args: " + Joiner.on(", ").join(args);
			System.out.println(message + ": \n" + e.getMessage());
			LoggerFactory.getLogger(ArgsParser.class).error(message, e);
			System.exit(1);
		}
	}
}
