package com.recnav.app.filters;

import com.recnav.app.ResponseModels.Response;

import com.recnav.app.config.ApplicationProperties;
import com.recnav.app.models.Auth;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthorizationFilter implements HandlerInterceptor {

    private Response response;

    @Override
    public boolean preHandle(HttpServletRequest req,
                          HttpServletResponse res, Object handler) throws IOException, ServletException {


        try {
            String compactJws = req.getHeader("token");

            int userId = Integer.parseInt(
                    Jwts.parser().setSigningKey(ApplicationProperties.key).parseClaimsJws(compactJws).getBody().getSubject()
            );

            Auth.getInstance().setUserId(userId);
            return true;

            //OK, we can trust this JWT

        } catch (SignatureException | ExpiredJwtException | IllegalArgumentException e) {
            response = new Response();
            e.printStackTrace();
            res.reset();
            res.setHeader("Content-Type", "application/json;charset=UTF-8");

            this.response.setType(Response.ERROR);
            this.response.setCode(Response.TOKEN_NOT_VALID);
            this.response.setMessageKey("message");
            this.response.setMessageText(e.getMessage());
            this.response.buildMessage();
            res.getOutputStream().println(response.responseJSON());
            //don't trust the JWT!
        }
        return false;
    }
}
