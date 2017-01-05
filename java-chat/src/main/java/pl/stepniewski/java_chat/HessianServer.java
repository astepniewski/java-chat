package pl.stepniewski.java_chat;

import com.caucho.hessian.server.HessianServlet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;

import pl.stepniewski.java_chat_contracts.CommunicationService;

public class HessianServer extends HessianServlet implements CommunicationService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void start() throws Exception {
		Server server = new Server(8080);
		Context context = new Context(server, "/", Context.SESSIONS);
		context.addServlet(HessianServer.class, "/communication-service");
		server.start();
	}

	public Integer SignIn(String userName) {
		return MainServer.SignIn(userName);
	}

	public void SendMessage(Integer senderId, String message) {
		MainServer.SendMessage(senderId, message);
	}

	public LinkedList<String> GetMessages(Integer clientId) {
		return MainServer.GetMessages(clientId);
	}

	public void SignOut(Integer userId) {
		MainServer.SignOut(userId);
	}
}
