package com.lan.tbmange.TBMangeServer.verticleServer.httpServerBoot;

import com.lan.tbmange.TBMangeServer.verticleServer.verticles.TBManageVerticle;

public class VertXBootServer {

	
	public static void verticleServerStart() {
		Runner.runExample(TBManageVerticle.class);
	}
	
	
	
}
