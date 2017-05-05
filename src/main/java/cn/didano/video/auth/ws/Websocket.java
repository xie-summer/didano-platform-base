package cn.didano.video.auth.ws;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.didano.base.exception.ServiceException;
import cn.didano.video.auth.AuthVideoInfo;
import cn.didano.video.exception.VideoExceptionEnums;
import cn.didano.video.service.WebsocketService;

/**
 * 一个websocket链接，该链接由http协议建立，然后由tcp协议保持通信
 * 
 * @author stephen Created on 2016年12月23日 下午6:30:26
 */
@Component
@ServerEndpoint(value = "/video/ws/video_auth/{channelId}/{studentId}/{openId}", configurator = GetHttpSessionConfigurator.class)
public class Websocket {
	static Logger logger = Logger.getLogger(Websocket.class);

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	private HttpSession httpSession;

	private int studentId;

	private int channelId;

	private String openId;

	/**
	 * 连接建立成功调用的方法，应该放到session里面，每个通道不论时间都可由管理员或者园长关闭
	 * 
	 * @Todo
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(@PathParam("channelId") int channelId, @PathParam("studentId") int studentId,
			@PathParam("openId") String openId, Session session, EndpointConfig config) throws Exception {
		this.channelId = channelId;
		this.studentId = studentId;
		this.openId = openId;
		// this.key = studentId + "-" + channelId + "-" + openId;
		this.session = session;
		logger.debug("Websocket open one websocket,channelId=" + channelId+":studentId="+studentId+":openId="+openId);
		this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		// 先看频道socket集合存在不，不存在添加
		if (!ifChannelexitBychannelId(channelId)) {
			WebsocketService.addChannel(channelId);
		}
		// 如果正在看视频已经存在，那么将被替换
		AuthVideoInfo info = new AuthVideoInfo();
		info.setChannelId(channelId);
		info.setStudentId(studentId);
		info.setOpenId(openId);
		info.setWebsocket(this);
		info.setSessionId( httpSession.getId());
		// if not has add channel
		if (WebsocketService.getWebsocketChannelMap().get(channelId) == null) {
			WebsocketService.addChannel(channelId);
		}
		// 不能用sessionid,因为一个用户可以多次链接，而sessionid是一样的
		WebsocketService.getWebsocketChannelMap().get(channelId).getWayMap().put(httpSession.getId(), info);
		addOnlineCount(); // 在线数加1
		logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
		
	}

	/**
	 * 某条视频频道是否存在 频道 一个摄像头一个频道 通道 每个看的人能建立一个websocket通道
	 * 
	 * @param channelId
	 * @return
	 */
	private boolean ifChannelexitBychannelId(int channelId) {
		boolean ifexit = WebsocketService.getWebsocketChannelMap().get(channelId) != null ? true : false;
		return ifexit;
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		subOnlineCount(); // 在线数减1
		//从管理器中移除此websocket
		WebsocketService.getWebsocketChannelMap().get(channelId).getWayMap().remove(httpSession.getId());
		logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		// 客户端不发信息
		// 同时也不向客户端发送信息
	}

	/**
	 * 发生错误时调用
	 * 此方法被自动调用，同时之后会自动调用onClose
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		//管理器里面如何删除呢
		logger.debug("websocket 发生错误"+error.getMessage());
		subOnlineCount(); // 在线数减1
		//从管理器中移除此websocket
		WebsocketService.getWebsocketChannelMap().get(channelId).getWayMap().remove(httpSession.getId());
		logger.info("有一连接出现异常关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) {
		try {
			logger.debug("websocket:sendMessage");
			this.session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			logger.debug(VideoExceptionEnums.FAIL_WEBSOCKET_SEND + ":websocket已经关闭sessionid[" + session.getId() + "]"+ ":"
					+ e.getMessage());
		} catch (Exception ex) {
			logger.debug(VideoExceptionEnums.FAIL_WEBSOCKET_SEND + ":websocket已经关闭sessionid[" + session.getId() + "]" + ":"
					+ ex.getMessage());
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		Websocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		Websocket.onlineCount--;
	}
}