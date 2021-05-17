package xyz.subho.covidhelp.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.accept.ContentNegotiationManager;
import xyz.subho.covidhelp.service.impl.UserServiceImpl;

@ContextConfiguration(classes = {SecurityConfig.class, HttpSecurity.class})
@ExtendWith(SpringExtension.class)
public class SecurityConfigTest {

  @MockBean private ContentNegotiationManager contentNegotiationManager;

  @Autowired private HttpSecurity httpSecurity;

  @Autowired private SecurityConfig securityConfig;

  @MockBean private UserServiceImpl userServiceImpl;

  @Test
  public void testConfigure() throws Exception {
    // TODO: This test is incomplete.
    //  No meaningful assertions found.
    assertEquals("Jadoo", "Jadoo");
    this.securityConfig.configure(this.httpSecurity);
  }
}
