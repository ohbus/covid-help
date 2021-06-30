package xyz.subho.covidhelp.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthorityTest {
  @Test
  public void testConstructor() {
    assertEquals("", (new Authority()).getAuthority());
    assertEquals("JaneDoe", (new Authority("JaneDoe")).getAuthority());
  }
}
