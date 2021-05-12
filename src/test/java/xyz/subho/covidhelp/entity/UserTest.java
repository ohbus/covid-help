package xyz.subho.covidhelp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import xyz.subho.covidhelp.security.Provider;
import xyz.subho.covidhelp.security.Role;
import xyz.subho.covidhelp.security.UserRole;

public class UserTest {
  @Test
  public void testConstructor() {
    User actualUser = new User("Name", "Contact No", "42");
    assertEquals("42", actualUser.getUsername());
    assertTrue(actualUser.isAccountNonExpired());
    assertTrue(actualUser.isAccountNonLocked());
    assertTrue(actualUser.isCredentialsNonExpired());
    assertFalse(actualUser.isEnabled());
  }

  @Test
  public void testConstructor2() {
    User actualUser = new User();
    assertEquals(
        "User(userId=0, name=, contactNo=, emailId=, password=, provider=UNAVAILABLE, userRoles=[], enabled=false,"
            + " currentIp=, currentLogin=Thu Jan 01 05:30:00 IST 1970, currentLocation=null, lastIp=, lastLogin=Thu"
            + " Jan 01 05:30:00 IST 1970, lastLocation=null)",
        actualUser.toString());
    assertFalse(actualUser.isEnabled());
    assertEquals("", actualUser.getUsername());
    assertEquals(0L, actualUser.getUserId().longValue());
    assertEquals(Provider.UNAVAILABLE, actualUser.getProvider());
    assertEquals("", actualUser.getPassword());
    assertEquals("", actualUser.getName());
    assertEquals("", actualUser.getContactNo());
    assertEquals("", actualUser.getCurrentIp());
    assertEquals("", actualUser.getLastIp());
  }

  @Test
  public void testConstructor3() {
    User actualUser = new User("Name", "Contact No", "42", "Current Ip", "Current Location");
    assertEquals("Contact No", actualUser.getContactNo());
    assertEquals("42", actualUser.getUsername());
    assertEquals("iloveyou", actualUser.getPassword());
    assertEquals("Name", actualUser.getName());
    assertNull(actualUser.getLastLogin());
    assertNull(actualUser.getLastLocation());
    assertNull(actualUser.getLastIp());
    assertEquals("Current Location", actualUser.getCurrentLocation());
    assertEquals("Current Ip", actualUser.getCurrentIp());
  }

  @Test
  public void testUpdateLogin() {
    User user = new User();
    user.updateLogin("Current Ip", "Current Location");
    assertNull(user.getLastLocation());
    assertEquals("Current Location", user.getCurrentLocation());
    assertEquals("Current Ip", user.getCurrentIp());
    assertEquals("", user.getLastIp());
  }

  @Test
  public void testGetAuthorities() {
    assertTrue((new User()).getAuthorities().isEmpty());
  }

  @Test
  public void testGetAuthorities2() {
    User user = new User("Name", "Contact No", "42");
    user.setUserRoles(new HashSet<UserRole>());
    assertTrue(user.getAuthorities().isEmpty());
  }

  @Test
  public void testGetAuthorities3() {
    User user = new User();
    user.setPassword("iloveyou");
    user.setLastIp("Last Ip");
    user.setName("Name");
    user.setLastLocation("Last Location");
    user.setEnabled(true);
    user.setUserId(123L);
    user.setProvider(Provider.UNAVAILABLE);
    user.setCurrentIp("Current Ip");
    user.setUserRoles(new HashSet<UserRole>());
    user.setEmailId("42");
    user.setContactNo("Contact No");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    user.setLastLogin(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
    user.setCurrentLocation("Current Location");
    LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
    user.setCurrentLogin(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));

    Role role = new Role();
    role.setName("Name");
    role.setUserRoles(new HashSet<UserRole>());
    role.setRoleId(123);

    UserRole userRole = new UserRole();
    userRole.setUser(user);
    userRole.setUserRoleId(123L);
    userRole.setRole(role);

    HashSet<UserRole> userRoleSet = new HashSet<UserRole>();
    userRoleSet.add(userRole);

    User user1 = new User("Name", "Contact No", "42");
    user1.setUserRoles(userRoleSet);
    assertEquals(1, user1.getAuthorities().size());
  }
}
