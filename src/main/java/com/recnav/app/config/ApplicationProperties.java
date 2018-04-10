package com.recnav.app.config;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.Key;

@ConfigurationProperties
public class ApplicationProperties {
    public static Key key = MacProvider.generateKey();
    public static final String SECRET = "SecretKeyToGenJWTs";

    @Value("${jwt.EXPIRATION_TIME}")
    public static final int EXPIRATION_TIME = 2000;
}
