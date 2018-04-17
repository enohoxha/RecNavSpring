package com.recnav.app.Controllers;

import com.recnav.app.ResponseModels.Response;
import com.recnav.app.config.ApplicationProperties;
import com.recnav.app.database.HibernateUtil;
import com.recnav.app.models.AuthData;
import com.recnav.app.routes.AuthController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
public class AuthControllerImplementation implements AuthController{

    private Response response;
    Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.


    public AuthControllerImplementation(){

        response = new Response();
    }

    @Override
    public Response login(String appName, String secretKey) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String compactJws = "Login Failed";
        calendar.add(Calendar.SECOND, ApplicationProperties.EXPIRATION_TIME);

        try{

            AuthData authData = (AuthData) session.createQuery("from AuthData where name = :n and secretKey = :s")
                    .setParameter("n", appName)
                    .setParameter("s", secretKey)
                    .getSingleResult();


            if(authData.getName() != null){
                compactJws = Jwts.builder()
                        .setExpiration(calendar.getTime())
                        .setSubject(Integer.toString(authData.getId()))
                        .signWith(SignatureAlgorithm.HS512, ApplicationProperties.key)
                        .compact();
            }
            transaction.commit();

            this.response.setType(Response.SUCCESS);
            this.response.setCode(Response.ALL_GOOD);
            this.response.setMessageKey("token");
            this.response.setMessageText(compactJws);


        } catch (Exception e) {
            transaction.rollback();
            this.response.setType(Response.ERROR);
            this.response.setCode(Response.AUTH_FAIL);
            this.response.setMessageKey("message");
            this.response.setMessageText(e.getMessage());
        }

        this.response.buildMessage();
        return this.response;

    }
}
