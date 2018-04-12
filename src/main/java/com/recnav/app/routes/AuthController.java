package com.recnav.app.routes;

import com.recnav.app.ResponseModels.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthController {

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public Response login(@RequestParam("appName") String appName, @RequestParam("secretKey") String secretKey);

}
