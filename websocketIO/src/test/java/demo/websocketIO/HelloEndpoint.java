package demo.websocketIO;

import java.io.IOException;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

public class HelloEndpoint extends Endpoint {

	private Session session;

	@Override
	public void onOpen(Session session, EndpointConfig config) {
		this.session = session;
		this.session.addMessageHandler(new MessageHandler.Whole<String>() {

			@Override
			public void onMessage(String message) {
				System.out.println("retrived message!!!!!!!!!!!" + message);
			}
		});
	}

	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

}
