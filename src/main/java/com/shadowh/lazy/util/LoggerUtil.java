package com.shadowh.lazy.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggerUtil {
	private static Log infoLog = LogFactory.getLog("info");
	private static Log debugLog = LogFactory.getLog("debug");
	private static Log errorLog = LogFactory.getLog("error");

	public static void info(String msg) {
		infoLog.info(msg);
	}
	public static void debug(String msg) {
		debugLog.debug(msg);
	}

	public static void error(String msg, Exception e) {
		if (msg != null) {
			errorLog.error(msg);
		}
		if (e != null) {
			errorLog.error(e.getMessage());
		}
	}
	public static void error(String msg) {
		error(msg, null);
	}

}
