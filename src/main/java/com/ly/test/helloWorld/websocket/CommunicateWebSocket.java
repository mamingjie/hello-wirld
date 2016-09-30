package com.ly.test.helloWorld.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint("/communicateWebSocket")
@Component
public class CommunicateWebSocket {

	private static Logger log = LoggerFactory.getLogger(CommunicateWebSocket.class);
	private static int onlineCount = 0;
	private static CopyOnWriteArraySet<CommunicateWebSocket> webSocketSet = new CopyOnWriteArraySet<>();
	private Session session;
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);
		addOnlineCount();
		log.info("有新的链接加入！当前在线人数为 " + getOnlineCount());
	}
	
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
		subOnlineCount();
		log.info("有一个链接关闭！当前在线人数为 " + getOnlineCount());
	}
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		log.info("来自客户端的消息：" + message);
		for (CommunicateWebSocket item: webSocketSet) {
			item.sendMessage(message);
		}
	}

	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}
	
	public static synchronized int getOnlineCount() {
		return CommunicateWebSocket.onlineCount;
	}
	
	public static synchronized void addOnlineCount() {
		CommunicateWebSocket.onlineCount++;
	}
	
	public static synchronized void subOnlineCount() {
		CommunicateWebSocket.onlineCount--;
	}
	
}
