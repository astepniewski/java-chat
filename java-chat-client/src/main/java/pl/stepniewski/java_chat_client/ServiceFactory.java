package pl.stepniewski.java_chat_client;

import java.net.MalformedURLException;

import com.caucho.burlap.client.BurlapProxyFactory;
import com.caucho.hessian.client.HessianProxyFactory;

import pl.stepniewski.java_chat_contracts.CommunicationService;

public class ServiceFactory {
	public static CommunicationService create(ClientType clientType) throws MalformedURLException {
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
			String url = "http://localhost:8087/communication-serviceburlap";
			BurlapProxyFactory factory = new BurlapProxyFactory();
			basic = (CommunicationService) factory.create(CommunicationService.class, url);
		}
		return basic;
	}
}
