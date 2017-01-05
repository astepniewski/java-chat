package pl.stepniewski.java_chat;

import java.util.LinkedList;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;

import com.caucho.burlap.server.BurlapServlet;

import pl.stepniewski.java_chat_contracts.CommunicationService;

public class BurlapServer extends BurlapServlet implements CommunicationService {

	public Integer SignIn(String userName) {
		return MainServer.SignIn(userName);
	}

	public void SignOut(Integer userId) {
		MainServer.SignOut(userId);
	}

	public void SendMessage(Integer senderId, String message) {
		MainServer.SendMessage(senderId, message);
	}

	public LinkedList<String> GetMessages(Integer clientId) {
		return MainServer.GetMessages(clientId);
	}

	public static void start() throws Exception {
		Server server = new Server(8087);
		Context context = new Context(server, "/", Context.SESSIONS);
		context.addServlet(BurlapServer.class, "/communication-serviceburlap");
		server.start();
		System.out.println("Burlap start");
	}
}
