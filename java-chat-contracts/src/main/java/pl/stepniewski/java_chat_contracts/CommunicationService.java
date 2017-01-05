package pl.stepniewski.java_chat_contracts;

import java.util.LinkedList;

public interface CommunicationService {

    String communicate(String str);
    Integer Login(String userName);
    void SendMessage(Integer senderId, String message);
    LinkedList<String> GetMessages(Integer clientId);
}
