package com.ipo.utils;

import org.apache.log4j.Logger;

import com.ipo.utils.Lg;

public class Lg {
	static Logger log = Logger.getLogger(Lg.class.getName());
    public static void storeLog(String msg){
        log.info(msg);
    }

    public void storeErrorLog(Exception msg){
        log.debug("Error",msg);
    }

}
