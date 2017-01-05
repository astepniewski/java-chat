package pl.stepniewski.java_chat;

import com.caucho.hessian.server.HessianServlet;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;

import pl.stepniewski.java_chat_contracts.CommunicationService;

public class HessianServer extends HessianServlet implements CommunicationService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String communicate(String str) {
		return "Hello World! " + str;
	}

	public static void start() throws Exception {
		Server server = new Server(8080);
		Context context = new Context(server, "/", Context.SESSIONS);
		context.addServlet(HessianServer.class, "/communication-service");
		server.start();
	}
}
