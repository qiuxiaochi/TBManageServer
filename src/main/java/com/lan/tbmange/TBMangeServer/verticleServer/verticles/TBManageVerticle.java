package com.lan.tbmange.TBMangeServer.verticleServer.verticles;

import java.util.HashSet;
import java.util.Set;

import com.lan.tbmange.TBMangeServer.verticleServer.config.Config;
import com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers.AddJishuHandler;
import com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers.GetJishuByQqHandler;
import com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers.GetJishuByWxHandler;
import com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers.GetJishuListBySkillHandler;
import com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers.GetNoJishusHandler;
import com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers.GetOnlyNoJishusHandler;
import com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers.GetOnlyYesJishusHandler;
import com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers.GetYesJishusHandler;
import com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers.SupplyJishuHandler;
import com.lan.tbmange.TBMangeServer.verticleServer.handlers.jishuMgHandlers.VoteJishuHandler;
import com.lan.tbmange.TBMangeServer.verticleServer.handlers.userHandlers.LoginHandler;
import com.lan.tbmange.TBMangeServer.verticleServer.handlers.userHandlers.UpPasswordHandler;
import com.lan.tbmange.TBMangeServer.verticleServer.service.jsMgService.IJsMgService;
import com.lan.tbmange.TBMangeServer.verticleServer.service.jsMgService.JsMgServiceImpl;
import com.lan.tbmange.TBMangeServer.verticleServer.service.userService.IUserService;
import com.lan.tbmange.TBMangeServer.verticleServer.service.userService.UserServiceImpl;
import com.lan.tbmange.TBMangeServer.verticleServer.verticles.routePath.UserRoutePath;

import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import io.vertx.reactivex.ext.web.handler.CorsHandler;

public class TBManageVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> future) throws Exception {

		Router router = Router.router(vertx);
		// Enable HTTP Body parse.
		router.route().handler(BodyHandler.create());
		// Enable CORS.
		enableCorsSupport(router);

		// 设置路由
		initRoute(router);

		vertx.createHttpServer().requestHandler(router).listen(Config.verticlePort, res -> {
			if (res.succeeded()) {
				future.complete();
				System.out.println("Vertx 端口[" + Config.verticlePort + "]绑定成功!");
			} else {
				future.fail(res.cause());
			}
		});

	}

	protected void enableCorsSupport(Router router) {
		Set<String> allowHeaders = new HashSet<>();
		allowHeaders.add("x-requested-with");
		allowHeaders.add("Access-Control-Allow-Origin");
		allowHeaders.add("origin");
		allowHeaders.add("Content-Type");
		allowHeaders.add("accept");
		// CORS support
		router.route()
				.handler(CorsHandler.create("*").allowedHeaders(allowHeaders).allowedMethod(HttpMethod.GET)
						.allowedMethod(HttpMethod.POST).allowedMethod(HttpMethod.DELETE).allowedMethod(HttpMethod.PATCH)
						.allowedMethod(HttpMethod.PUT));

	}

	private void initRoute(Router router) {

		// 添加用户路由处理器
		IUserService userService = new UserServiceImpl();

		router.route().path(UserRoutePath.API_LOGIN).handler(new LoginHandler(userService));
		router.route().path(UserRoutePath.API_UPDATE_PASS).handler(new UpPasswordHandler(userService));

		// 添加技术路由处理器
		IJsMgService jsMgService = new JsMgServiceImpl();

		router.route().path(UserRoutePath.API_ADD_JISHU).handler(new AddJishuHandler(jsMgService));
		router.route().path(UserRoutePath.API_SUPPLY_JISHU).handler(new SupplyJishuHandler(jsMgService));
		router.route().path(UserRoutePath.API_VOTE_JISHU).handler(new VoteJishuHandler(jsMgService));

		router.route().path(UserRoutePath.API_GET_JISHU_QQ).handler(new GetJishuByQqHandler(jsMgService));
		router.route().path(UserRoutePath.API_GET_JISHU_WX).handler(new GetJishuByWxHandler(jsMgService));
		router.route().path(UserRoutePath.API_GET_JISHU_SKILL).handler(new GetJishuListBySkillHandler(jsMgService));

		router.route().path(UserRoutePath.API_GET_JISHU_ONLY_YES).handler(new GetOnlyYesJishusHandler(jsMgService));
		router.route().path(UserRoutePath.API_GET_JISHU_ONLY_NO).handler(new GetOnlyNoJishusHandler(jsMgService));
		router.route().path(UserRoutePath.API_GET_JISHU_YES).handler(new GetYesJishusHandler(jsMgService));
		router.route().path(UserRoutePath.API_GET_JISHU_NO).handler(new GetNoJishusHandler(jsMgService));

	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		stopFuture.complete();
	}

}
