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
	private static Map<Integer, String> users = new HashMap<Integer, String>();
	private static Map<Integer, LinkedList<String>> unreadedMessages = new HashMap<Integer, LinkedList<String>>();
	private static Integer newUserId = 0;

	public String communicate(String str) {
		return "Hello World! " + str;
	}

	public static void start() throws Exception {
		Server server = new Server(8080);
		Context context = new Context(server, "/", Context.SESSIONS);
		context.addServlet(HessianServer.class, "/communication-service");
		server.start();
	}

	public Integer Login(String userName) {
		if (users.containsValue(userName)) {
			return -1;
		}
		users.put(++newUserId, userName);
		unreadedMessages.put(newUserId, new LinkedList<String>());
		return newUserId;
	}

	public void SendMessage(Integer senderId, String message) {
		for(Entry<Integer, LinkedList<String>> entry : unreadedMessages.entrySet()) {
		    Integer userId = entry.getKey();
		    if(userId != senderId){
		    	LinkedList<String> messages = entry.getValue();
		    	messages.add(users.get(senderId) + ": " + message + "\n");
		    }
		}
	}

	public LinkedList<String> GetMessages(Integer clientId) {
		if(unreadedMessages.get(clientId) != null){
			LinkedList<String> messages = (LinkedList<String>) unreadedMessages.get(clientId).clone();
			unreadedMessages.get(clientId).removeAll(messages);
			return messages;
		}
		return new LinkedList<String>();
	}
}
