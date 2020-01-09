package com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers;

import com.lan.tbmange.TBMangeServer.entity.Vote;
import com.lan.tbmange.TBMangeServer.threadUtil.FixedThreadPool;
import com.lan.tbmange.TBMangeServer.verticleServer.httpUtil.HttpUtil;
import com.lan.tbmange.TBMangeServer.verticleServer.service.jsMgService.IJsMgService;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.RoutingContext;

/*
 * 某客服给技术投票和贴标签
 */
public class VoteJishuHandler implements Handler<RoutingContext>{

private IJsMgService service;
	
	public VoteJishuHandler(IJsMgService userService) {
		this.service = userService;
	}
	
	@Override
	public void handle(RoutingContext ctx) {
		
		
		JsonObject bodyAsJson = ctx.getBodyAsJson();
		FixedThreadPool.threadPool.submit(new Runnable() {

			@Override
			public void run() {

				String kefuname = bodyAsJson.getString("kefuname");
				
				String jishuqq = bodyAsJson.getString("jishuqq");
				
				int yesorno = bodyAsJson.getInteger("yesorno") ;     
				
				String info = bodyAsJson.getString("info");         //其他信息
				String label = bodyAsJson.getString("label");        //标签
				
				
				
				Vote vote = new Vote(kefuname, jishuqq, yesorno, label, info);
				
				String rString = service.voteJishu(vote);
				
				
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
