package com.recnav.app.routes;

import com.recnav.app.ResponseModels.Response;
import com.recnav.app.models.Articles;
import com.recnav.app.models.RequestModels.UserClickRequest;
import com.recnav.app.models.Users;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface ApiController {
    @RequestMapping(method = RequestMethod.POST,
            value = "/api/newApp",  produces = MediaType.APPLICATION_JSON_VALUE )
    public Response addNewApp();

    @RequestMapping(method = RequestMethod.POST,
            value = "/api/addArticlesGroup" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addArticles(List<Articles> items);


    @RequestMapping(method = RequestMethod.POST,
            value = "/api/registerUser" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Response registerUser(List <Users> items);


    @RequestMapping(method = RequestMethod.POST,
            value = "/api/recordClick" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Response recordClick(UserClickRequest users);



}
