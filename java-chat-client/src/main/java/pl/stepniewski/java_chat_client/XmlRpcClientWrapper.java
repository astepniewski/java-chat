package pl.stepniewski.java_chat_client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import pl.stepniewski.java_chat_contracts.CommunicationService;
import pl.stepniewski.java_chat_contracts.SerializeService;

public class XmlRpcClientWrapper implements CommunicationService {

	private XmlRpcClient client;
	private SerializeService serializeService = new SerializeService();

	public XmlRpcClientWrapper(String url) throws MalformedURLException {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(url));
		client = new XmlRpcClient();
		client.setConfig(config);
	}

	public Integer SignIn(String userName) {
		Object[] params = new Object[] { userName };
		try {
			return (Integer) client.execute("xmlrpcserver.SignIn", params);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void SignOut(Integer userId) {
		Object[] params = new Object[] { userId };
		try {
			client.execute("xmlrpcserver.SignOut", params);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
	}

	public void SendMessage(Integer senderId, String message) {
		Vector<String> params = new Vector<String>();
		params.addElement(Integer.toString(senderId));
		params.addElement(message);
		try {
			client.execute("xmlrpcserver.SendMessage", params);
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
	}

	public LinkedList<String> GetMessages(Integer clientId) {
		Object[] params = new Object[] { clientId };
		try {
			Object result = client.execute("xmlrpcserver.GetMessages", params);
			String msg = result.toString();
			if (!msg.isEmpty() && msg != null) {
				return new LinkedList(Arrays.asList(serializeService.StringToObject(msg)));
			}
		} catch (XmlRpcException e) {
			e.printStackTrace();
		}
		return new LinkedList<String>();
	}

}
