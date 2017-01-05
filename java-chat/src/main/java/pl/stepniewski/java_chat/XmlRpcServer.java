package pl.stepniewski.java_chat;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.server.XmlRpcStreamServer;
import org.apache.xmlrpc.webserver.WebServer;

import pl.stepniewski.java_chat_contracts.SerializeService;

public class XmlRpcServer {

	private SerializeService serializeService = new SerializeService();
	
	public static void start() throws Exception {
		try {
			WebServer webServer = new WebServer(8086);
	        
	          XmlRpcStreamServer xmlRpcServer = webServer.getXmlRpcServer();
	        
	          PropertyHandlerMapping phm = new PropertyHandlerMapping();

	          phm.addHandler("xmlrpcserver", XmlRpcServer.class);
	          xmlRpcServer.setHandlerMapping(phm);
	        
	          XmlRpcServerConfigImpl serverConfig =
	              (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
	          serverConfig.setEnabledForExtensions(true);
	          serverConfig.setContentLengthOptional(false);

	          webServer.start();
	          System.out.println("XMLRPC Server started.");

		} catch (Exception exception) {
			System.err.println("JavaServer: " + exception);
		}
	}
	
	public Integer SignIn(String userName) {
		return MainServer.SignIn(userName);
	}

	public Integer SendMessage(String senderId, String message) {
		MainServer.SendMessage(Integer.parseInt(senderId), message);
		return 0;
	}

	public String GetMessages(Integer clientId) {
		return serializeService.ObjectToString(MainServer.GetMessages(clientId).toArray(new String[0]));
	}

	public Integer SignOut(Integer userId) {
		MainServer.SignOut(userId);
		return 0;
	}
}
