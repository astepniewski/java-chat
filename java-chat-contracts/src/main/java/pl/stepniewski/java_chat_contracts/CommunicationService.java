package pl.stepniewski.java_chat_contracts;

import java.util.LinkedList;

public interface CommunicationService {

    Integer SignIn(String userName);
    void SignOut(Integer userId);
    void SendMessage(Integer senderId, String message);
    LinkedList<String> GetMessages(Integer clientId);
}
