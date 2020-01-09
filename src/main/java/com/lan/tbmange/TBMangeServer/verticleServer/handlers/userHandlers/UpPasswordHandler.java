package com.lan.tbmange.TBMangeServer.verticleServer.handlers.userHandlers;

import com.lan.tbmange.TBMangeServer.entity.keFu;
import com.lan.tbmange.TBMangeServer.threadUtil.FixedThreadPool;
import com.lan.tbmange.TBMangeServer.verticleServer.httpUtil.HttpUtil;
import com.lan.tbmange.TBMangeServer.verticleServer.service.userService.IUserService;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.RoutingContext;

/*
 * 	修改密码
 */
public class UpPasswordHandler implements Handler<RoutingContext>{

private IUserService service;
	
	public UpPasswordHandler(IUserService userService) {
		this.service = userService;
	}
	
	@Override
	public void handle(RoutingContext ctx) {
		
		
		JsonObject bodyAsJson = ctx.getBodyAsJson();
		FixedThreadPool.threadPool.submit(new Runnable() {

			@Override
			public void run() {

				String username = bodyAsJson.getString("username");
				String oldpassword = bodyAsJson.getString("oldpassword");
				String newpassword = bodyAsJson.getString("newpassword");
				
				
				keFu keFu = new keFu(username,oldpassword);
				String rString = service.updatePassword(keFu, newpassword);
				
				JsonObject jsonObject = new JsonObject();
				jsonObject.put("message",rString );
				
				
				HttpUtil.resp(ctx.request(), new JsonObject().put("message", "ok"));
				
			
			}
		});
		
		
	}
	
}
