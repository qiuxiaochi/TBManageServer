package com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers;

import com.lan.tbmange.TBMangeServer.entity.Jishu;
import com.lan.tbmange.TBMangeServer.threadUtil.FixedThreadPool;
import com.lan.tbmange.TBMangeServer.verticleServer.httpUtil.HttpUtil;
import com.lan.tbmange.TBMangeServer.verticleServer.service.jsMgService.IJsMgService;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.RoutingContext;

/*
 * 查询qq获得技术信息
 */
public class GetJishuByQqHandler implements Handler<RoutingContext>{

private IJsMgService service;
	
	public GetJishuByQqHandler(IJsMgService userService) {
		this.service = userService;
	}
	
	@Override
	public void handle(RoutingContext ctx) {
		
		
		JsonObject bodyAsJson = ctx.getBodyAsJson();
		FixedThreadPool.threadPool.submit(new Runnable() {

			@Override
			public void run() {
				
				String qq = bodyAsJson.getString("qq");
				
				
				Jishu jishu = service.getJishuByQq(qq);
				
				JsonObject jsonObject= new JsonObject();
				
				if(jishu==null) {
					jsonObject.put("status", "error");
					jsonObject.put("message", "该技术不存在!");
					HttpUtil.resp(ctx.request(),jsonObject);
					
				}else {
				
					jsonObject.put("status", "ok");
					jsonObject.put("jishu", jishu);
					HttpUtil.resp(ctx.request(), jsonObject);
				
				}
			
			}
		});
		
		
	}
	
	
}
