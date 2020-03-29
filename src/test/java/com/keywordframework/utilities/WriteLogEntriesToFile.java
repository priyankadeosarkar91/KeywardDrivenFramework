package com.keywordframework.utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class WriteLogEntriesToFile {
	public static void main(String[] args) {
		FileHandler handler;
		Logger logger=Logger.getLogger("Logger.MyLog");
		
		boolean append=true;
		try {
			handler=new FileHandler("MylogFile.log",append);
			logger=Logger.getLogger("WriteLogEntriesToFile.class");
			logger.addHandler(handler);
			SimpleFormatter formatter=new SimpleFormatter();
			handler.setFormatter(formatter);
//			logger.info("My first log");
//			logger.log(Level.INFO, "Info");
			
			if(logger.isLoggable(Level.INFO)) {
				logger.info("General info msg");
			}
			if(logger.isLoggable(Level.CONFIG)) {
				logger.config("Configuration msg");
			}
			if(logger.isLoggable(Level.FINE)) {
				logger.fine("Fine msg : general developer info");
			}
			if(logger.isLoggable(Level.FINER)) {
				logger.finer("Finer msg: detailed developer info");
			}
			if(logger.isLoggable(Level.FINEST)) {
				logger.finest("Finer msg: specialized developer info");
			}
			if(logger.isLoggable(Level.WARNING)) {
				logger.warning("Wanrning msg: potential problem");
			}
			if(logger.isLoggable(Level.SEVERE)) {
				logger.severe("Severe msg: serious failure");
			}
				
		} catch (SecurityException |IOException e) {
			e.printStackTrace();
		}
	}
}
