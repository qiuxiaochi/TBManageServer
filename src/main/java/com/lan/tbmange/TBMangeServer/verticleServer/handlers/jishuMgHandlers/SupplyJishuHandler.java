package com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers;

import com.lan.tbmange.TBMangeServer.entity.Jishu;
import com.lan.tbmange.TBMangeServer.entity.keFu;
import com.lan.tbmange.TBMangeServer.threadUtil.FixedThreadPool;
import com.lan.tbmange.TBMangeServer.verticleServer.httpUtil.HttpUtil;
import com.lan.tbmange.TBMangeServer.verticleServer.service.jsMgService.IJsMgService;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.RoutingContext;

/*
 * 补充技术信息
 */
public class SupplyJishuHandler implements Handler<RoutingContext>{

private IJsMgService service;
	
	public SupplyJishuHandler(IJsMgService userService) {
		this.service = userService;
	}
	
	@Override
	public void handle(RoutingContext ctx) {
		
		
		JsonObject bodyAsJson = ctx.getBodyAsJson();
		FixedThreadPool.threadPool.submit(new Runnable() {

			@Override
			public void run() {

				String kefuusername = bodyAsJson.getString("kefuusername");
				
				String qq = bodyAsJson.getString("qq");
				String wx = bodyAsJson.getString("wx");
				int yes = bodyAsJson.getInteger("yes") ;           //支持数量
				int no = bodyAsJson.getInteger("no");             //否定数量
				String info = bodyAsJson.getString("info");         //其他信息
				String label = bodyAsJson.getString("label");        //标签
				String skill = bodyAsJson.getString("skill");        //会的技术语言
				
				keFu keFu = new keFu(kefuusername);
				Jishu jishu = new Jishu(qq, wx, yes, no, info, label, skill);
				
				String rString = service.supplyJishu(keFu, jishu);
				
				
				if(rString.equals("ok")) {
					HttpUtil.resp(ctx.request(), new JsonObject().put("message", "ok"));
					
				}else {
					if(rString.equals("usernameerror")) {
						HttpUtil.resp(ctx.request(), new JsonObject().put("message", "该账号不存在!"));
					}else if(rString.equals("passworderror")) {
						HttpUtil.resp(ctx.request(), new JsonObject().put("message", "账号或密码错误!"));
					}else {
						HttpUtil.resp(ctx.request(), new JsonObject().put("message", "服务器繁忙,请稍后再试!"));
					}
					
				}
			
			}
		});
		
		
	}
	
	
}
