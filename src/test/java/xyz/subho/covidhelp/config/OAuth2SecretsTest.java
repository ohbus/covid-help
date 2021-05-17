package xyz.subho.covidhelp.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OAuth2SecretsTest {
  @Test
  public void testConstructor() {
    OAuth2Secrets actualOAuth2Secrets = new OAuth2Secrets();
    actualOAuth2Secrets.setGoogleCleintId("42");
    actualOAuth2Secrets.setGoogleClientSecret("Google Client Secret");
    assertEquals("42", actualOAuth2Secrets.getGoogleCleintId());
    assertEquals("Google Client Secret", actualOAuth2Secrets.getGoogleClientSecret());
  }
}
