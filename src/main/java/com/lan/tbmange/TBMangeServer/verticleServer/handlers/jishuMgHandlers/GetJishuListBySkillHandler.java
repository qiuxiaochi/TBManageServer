package com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers;

import java.util.Vector;

import com.lan.tbmange.TBMangeServer.entity.Jishu;
import com.lan.tbmange.TBMangeServer.threadUtil.FixedThreadPool;
import com.lan.tbmange.TBMangeServer.verticleServer.httpUtil.HttpUtil;
import com.lan.tbmange.TBMangeServer.verticleServer.service.jsMgService.IJsMgService;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.RoutingContext;

/*
 * 获得会某技术语言的所有技术
 */
public class GetJishuListBySkillHandler implements Handler<RoutingContext>{

private IJsMgService service;
	
	public GetJishuListBySkillHandler(IJsMgService userService) {
		this.service = userService;
	}
	
	@Override
	public void handle(RoutingContext ctx) {
		
		
		JsonObject bodyAsJson = ctx.getBodyAsJson();
		FixedThreadPool.threadPool.submit(new Runnable() {

			@Override
			public void run() {
				
				String skill = bodyAsJson.getString("skill");
				
				
				Vector<Jishu> jishus = service.getJishuListBySkill(skill);
				
				
				
				JsonObject jsonObject= new JsonObject();
				
				if(jishus==null||jishus.size()==0) {
					jsonObject.put("status", "error");
					jsonObject.put("message", "该技术不存在!");
					HttpUtil.resp(ctx.request(),jsonObject);
					
				}else {
				
					jsonObject.put("status", "ok");
					jsonObject.put("jishus", jishus);
					HttpUtil.resp(ctx.request(), jsonObject);
				
				}
			
			}
		});
		
		
	}
	
	
}
