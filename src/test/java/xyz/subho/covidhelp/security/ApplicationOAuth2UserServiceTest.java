package xyz.subho.covidhelp.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ApplicationOAuth2UserService.class})
@ExtendWith(SpringExtension.class)
public class ApplicationOAuth2UserServiceTest {
    @Autowired
    private ApplicationOAuth2UserService applicationOAuth2UserService;

    @Test
    public void testLoadUser() throws OAuth2AuthenticationException {
        // TODO: This test is incomplete.
        //  No meaningful assertions found.

        this.applicationOAuth2UserService.loadUser(null);
    }
}

