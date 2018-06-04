package io.milo.demo;

import java.util.Map;

public interface Demo {
    void setClient(UserClient client);
    void runDemo();
    void insertUsers(Map<String, User[]> users);
    void insertVotes(Map<String, User[]> users);
    void insertConstrainedVotes(Map<String, User[]> users);
}
