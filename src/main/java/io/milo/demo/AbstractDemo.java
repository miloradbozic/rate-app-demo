package io.milo.demo;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDemo implements Demo {

    protected UserClient client;
    protected Map<String, User[]> users;

    public AbstractDemo() {
        this.users = new HashMap<>();
    }

    public AbstractDemo(UserClient client) {
        this.client = client;
        this.users = new HashMap<>();
    }

    public void setClient(UserClient client) {
        this.client = client;
    }

    public void runDemo() {
        try {
            this.client.recreateIndex();
        } catch (Exception e) {
            ;
        }
        Map<String, User[]> users = new HashMap<>();
        this.insertUsers(users);
        this.insertVotes(users);
        this.insertConstrainedVotes(users);
        this.printReports();
    }

    @Override
    public abstract void insertUsers(Map<String, User[]> users);

    @Override
    public abstract void insertVotes(Map<String, User[]> users);

    public void printReports() {
        User lead  = this.client.getUserLead();
        String regionMostVotes = this.client.regionMostVotes();
        String regionVotedMost = this.client.regionVotedMost();
        Map<String, User> regionLeads = this.client.regionLeads();

        System.out.println("---------------------");
        System.out.println("\nLead user: " + lead);
        System.out.println("\nRegion with most votes: " + regionMostVotes);
        System.out.println("\nRegion which voted most: " + regionVotedMost);

        System.out.println("\nRegional leads: " );
        for (Map.Entry<String, User> entry : regionLeads.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName() + " " + entry.getValue().getSurname());
        }
    }
}
