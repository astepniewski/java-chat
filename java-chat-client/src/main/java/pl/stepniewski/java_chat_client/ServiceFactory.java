package pl.stepniewski.java_chat_client;

import java.net.MalformedURLException;

import org.apache.xmlrpc.XmlRpcException;

import com.caucho.burlap.client.BurlapProxyFactory;
import com.caucho.hessian.client.HessianProxyFactory;

import pl.stepniewski.java_chat_contracts.CommunicationService;

public class ServiceFactory {
	public static CommunicationService create(ClientType clientType) throws MalformedURLException, XmlRpcException {
		CommunicationService basic;
		if (clientType == ClientType.Hessian) {
			String url = "http://localhost:8080/communication-service";
			HessianProxyFactory factory = new HessianProxyFactory();
			basic = (CommunicationService) factory.create(CommunicationService.class, url);
		} else if (clientType == ClientType.Burlap) {
			String url = "http://localhost:8087/communication-serviceburlap";
			BurlapProxyFactory factory = new BurlapProxyFactory();
			basic = (CommunicationService) factory.create(CommunicationService.class, url);
		} else {
			return new XmlRpcClientWrapper("http://localhost:8086");
		}
		return basic;
	}
}
