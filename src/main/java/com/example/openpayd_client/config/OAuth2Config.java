package com.example.openpayd_client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    private final String clientid = "openpayd-client";
    //test1234$
    private final String clientSecret = "$2a$12$JZF9sSodNEPswYYWfdhTye0Lnpl0sgSNc0dJnQxrqFgy77pZGasKG";
    //test1234
//    private final String clientSecret = "$2a$12$XAE9IdRf9LOJl8YfYccbFePBSzQvv3F/UrD7/h992P/ygiDw6U082";
    private final String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIICXQIBAAKBgQCI/2ZTTuIWlCSIL9EW1LFCesFqUX/UGLY7C4VnRlMFzs8CxdA+\n" +
            "kzEzfcK0RXXdEhMbzNiHRO/9tO2KnobxeCbrmspb865ld1Zdxa/GqyWGWg0rSfcq\n" +
            "9L7CuaBI8GmRyS5muwAQyp8DnVHcqF0s93vQmoioZwTwKSTvwhP81ZCxPwIDAQAB\n" +
            "AoGAaTpbu5O0v4kMwnqQDQxUF284z1RIbn6IwrOnxVrT/HsMsOGGgfoHabiobFWK\n" +
            "IcN3X/wpOUWvXV2ys63ukt0qbt61TK3KgR1t50td/ymDd4M9obJ2gPPHsGZ3KAOr\n" +
            "pwwSsTfUaPkNeTdq3OE8d9RFxdKmQTSOgafGLG2vBhLoZOkCQQDQs25N4OgEUm3R\n" +
            "uVpILNMFqT4HGR3tVCfnEMh7SAH5ZozUSYJShjkt2DXsiMmSrmKsJznFRTFvwsfU\n" +
            "TS1oC4ttAkEAqAvVtvm+pV9CaIGHrt6jF9N+RjpXPuA3eJMUXZ4KnkENisI1XQY7\n" +
            "ehkz8V5EPHDSVRuStu00EjPxXKYYqiw32wJBAMvkcMA/GQ2X5lh9yDk5KZSfSs0u\n" +
            "ebFyhWVWXTcsG1moGbAQfPTY0yX51HzZCm12V0ovLF6RAgJY/ldoaxL6LGUCQBoE\n" +
            "CwgSzgMIJl3Lf9gAu7sakPgKXCttxuixzHtZ1kb9xzzuKBjIuDiE4lfBwkqhJM7s\n" +
            "P+B2BJO02u7+/96IjfUCQQC7Y+95D2bjRdFkem/oDzS6bW3eW/G72o2gsUJH+iJ9\n" +
            "KvxEllzbqcNqx49HJk0IpbkSKuNMtsSa5ofj+J0mqNtk\n" +
            "-----END RSA PRIVATE KEY-----";

    private final AuthenticationManager authenticationManager;

    @Autowired
    public OAuth2Config(@Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(privateKey);
        return converter;
    }
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientid).secret(clientSecret).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }
}