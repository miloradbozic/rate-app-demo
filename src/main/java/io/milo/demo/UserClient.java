package io.milo.demo;

import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

public interface UserClient {

    @RequestLine("GET /api/index/recreate")
    String recreateIndex();

    @RequestLine("POST /api/index/insert?name={name}&surname={surname}&gender={gender}&region={region}")
    User insertUser(@Param(value = "name") String name, @Param(value = "surname") String surname,
                      @Param(value = "gender") String gender, @Param(value = "region") String region);

    @RequestLine("POST /api/vote/add?voterId={voterId}&votedIds={votedIds}")
    List<User> vote(@Param(value = "voterId") String voterId, @Param(value = "votedIds") List<String> votedIds);

    @RequestLine("GET /api/report/user/lead")
    User getUserLead();

    @RequestLine("GET /api/report/region/most-votes")
    String regionMostVotes();

    @RequestLine("GET /api/report/region/voted-most")
    String regionVotedMost();

    @RequestLine("GET /api/report/region/leads")
    Map<String, User> regionLeads();

    //@RequestLine("POST")
    //@Headers("Content-Type: application/json")
    //void create(Book book);
}