package com.recnav.app.routes;

import com.recnav.app.ResponseModels.Response;
import com.recnav.app.models.Articles;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public interface ApiController {

    @RequestMapping(method = RequestMethod.POST,
            value = "/newApp",  produces = MediaType.APPLICATION_JSON_VALUE )
    public Response addNewApp();


    @RequestMapping(method = RequestMethod.POST,
            value = "/addArticlesGroup" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addArticles(List<Articles> items);



}
