package io.milo.demo;

import feign.FeignException;

import java.util.ArrayList;
import java.util.Map;

public class DemoImpl1 extends AbstractDemo implements Demo {

    public void insertUsers(Map<String, User[]> users) {
        users.put("serbia", new User[]{
                this.client.insertUser("Marko", "Markovic","male", "serbia"),
                this.client.insertUser("Jovan", "Jovanovic","male", "serbia"),
                this.client.insertUser("Dejana", "Dejanovic","female", "serbia")
        });

        users.put("england", new User[]{
                this.client.insertUser("John", "Lenon","male", "england"),
                this.client.insertUser("Steven", "Stevenson","male", "england"),
                this.client.insertUser("Ryan", "Peterson","male", "england")
        });

        users.put("france", new User[]{
                this.client.insertUser("Vincent", "Lacron","male", "france"),
                this.client.insertUser("Lorena", "Lebrie","female", "france")
        });

        users.put("spain", new User[]{
                this.client.insertUser("Fernando", "Rodriguez","male", "spain"),
                this.client.insertUser("Jaime", "Sanchez","male", "spain")
        });

        users.put("italy", new User[]{
                this.client.insertUser("Francisco", "Petrarka","male", "italy"),
                this.client.insertUser("Valeria", "Puti","female", "italy")
        });

        users.put("italy", new User[]{
                this.client.insertUser("Francisco", "Petrarka","male", "italy"),
                this.client.insertUser("Valeria", "Puti","female", "italy")
        });

        users.put("portugal", new User[]{
                this.client.insertUser("Nuno", "Gomes","male", "portugal")
        });

        users.put("germany", new User[]{
                this.client.insertUser("Fritz", "Klanker","male", "germany")
        });

        users.put("russia", new User[]{
                this.client.insertUser("Jurij", "Belova","male", "russia")
        });

        users.put("sweden", new User[]{
                this.client.insertUser("Frido", "Glonen","male", "sweden")
        });

        users.put("croatia", new User[]{
                this.client.insertUser("Stipe", "Stipic","male", "croatia")
        });
    }

    public void insertVotes(Map<String, User[]> users) {
        this.client.vote(users.get("serbia")[0].getId(), new ArrayList<String>(){{
            add(users.get("serbia")[1].getId());
            add(users.get("england")[0].getId());
            add(users.get("france")[1].getId());
            add(users.get("spain")[0].getId());
            add(users.get("italy")[0].getId());
            add(users.get("portugal")[0].getId());
            add(users.get("germany")[0].getId());
            add(users.get("russia")[0].getId());
            add(users.get("sweden")[0].getId());
            add(users.get("croatia")[0].getId());
        }});

        this.client.vote(users.get("serbia")[1].getId(), new ArrayList<String>(){{
            add(users.get("serbia")[0].getId());
            add(users.get("england")[1].getId());
            add(users.get("france")[0].getId());
            add(users.get("spain")[1].getId());
            add(users.get("italy")[0].getId());
            add(users.get("portugal")[0].getId());
            add(users.get("germany")[0].getId());
            add(users.get("russia")[0].getId());
            add(users.get("sweden")[0].getId());
        }});

        this.client.vote(users.get("england")[0].getId(), new ArrayList<String>(){{
            add(users.get("serbia")[0].getId());
            add(users.get("russia")[0].getId());
            add(users.get("sweden")[0].getId());
        }});

        this.client.vote(users.get("france")[0].getId(), new ArrayList<String>(){{
            add(users.get("serbia")[0].getId());
            add(users.get("spain")[1].getId());
        }});

        this.client.vote(users.get("croatia")[0].getId(), new ArrayList<String>(){{
            add(users.get("serbia")[0].getId());
        }});
    }

    @Override
    public void insertConstrainedVotes(Map<String, User[]> users) {
        // violates vote for self
        try {
            this.client.vote(users.get("serbia")[1].getId(), new ArrayList<String>() {{
                add(users.get("serbia")[1].getId());
            }});
        } catch (FeignException e) {
            System.out.println(e.getMessage());
        }

        // violates max 10 votes per user
        try {
            this.client.vote(users.get("serbia")[0].getId(), new ArrayList<String>() {{
                add(users.get("serbia")[2].getId());
            }});
        } catch (FeignException e) {
            System.out.println(e.getMessage());
        }

        // violates can not vote for same user multiple times
        try {
            this.client.vote(users.get("england")[0].getId(), new ArrayList<String>() {{
                add(users.get("serbia")[0].getId());
            }});
        } catch (FeignException e) {
            System.out.println(e.getMessage());
        }

        // violates same gender constraint
        try {
            this.client.vote(users.get("serbia")[1].getId(), new ArrayList<String>() {{
                add(users.get("croatia")[0].getId());
            }});
        } catch (FeignException e) {
            System.out.println(e.getMessage());
        }

        // violates vote for same region in less than 5 minutes
        try {
            this.client.vote(users.get("france")[0].getId(), new ArrayList<String>() {{
                add(users.get("spain")[0].getId());
            }});
        } catch (FeignException e) {
            System.out.println(e.getMessage());
        }

    }
}
