package pl.stepniewski.java_chat;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class MainServer {
	private static Map<Integer, String> users = new HashMap<Integer, String>();
	private static Map<Integer, LinkedList<String>> unreadedMessages = new HashMap<Integer, LinkedList<String>>();
	private static Integer newUserId = 0;

	public static Integer SignIn(String userName) {
		if (users.containsValue(userName)) {
			return -1;
		}
		users.put(++newUserId, userName);
		unreadedMessages.put(newUserId, new LinkedList<String>());
		return newUserId;
	}

	public static void SignOut(Integer userId) {
		users.remove(userId);
		unreadedMessages.remove(userId);
	}

	public static void SendMessage(Integer senderId, String message) {
		for(Entry<Integer, LinkedList<String>> entry : unreadedMessages.entrySet()) {
		    Integer userId = entry.getKey();
		    if(userId != senderId){
		    	LinkedList<String> messages = entry.getValue();
		    	messages.add(users.get(senderId) + ": " + message + "\n");
		    }
		}
	}

	public static LinkedList<String> GetMessages(Integer clientId) {
		if(unreadedMessages.get(clientId) != null){
			LinkedList<String> messages = (LinkedList<String>) unreadedMessages.get(clientId).clone();
			unreadedMessages.get(clientId).removeAll(messages);
			return messages;
		}
		return new LinkedList<String>();
	}
}
