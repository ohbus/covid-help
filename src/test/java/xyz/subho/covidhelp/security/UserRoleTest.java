package xyz.subho.covidhelp.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import xyz.subho.covidhelp.entity.User;

public class UserRoleTest {
  @Test
  public void testConstructor() {
    User user = new User();
    assertEquals(
        "UserRole(userRoleId=null, user=User(userId=0, name=, contactNo=, emailId=, password=, provider=UNAVAILABLE,"
            + " userRoles=[], enabled=false, currentIp=, currentLogin=Thu Jan 01 05:30:00 IST 1970, currentLocation=null,"
            + " lastIp=, lastLogin=Thu Jan 01 05:30:00 IST 1970, lastLocation=null), role=Role(roleId=0, name=,"
            + " userRoles=[]))",
        (new UserRole(user, new Role())).toString());
  }

  @Test
  public void testConstructor2() {
    UserRole actualUserRole = new UserRole();
    assertEquals(
        "UserRole(userRoleId=0, user=User(userId=0, name=, contactNo=, emailId=, password=, provider=UNAVAILABLE,"
            + " userRoles=[], enabled=false, currentIp=, currentLogin=Thu Jan 01 05:30:00 IST 1970, currentLocation=null,"
            + " lastIp=, lastLogin=Thu Jan 01 05:30:00 IST 1970, lastLocation=null), role=Role(roleId=0, name=,"
            + " userRoles=[]))",
        actualUserRole.toString());
    assertEquals(0L, actualUserRole.getUserRoleId().longValue());
    User user = actualUserRole.getUser();
    assertEquals(
        "User(userId=0, name=, contactNo=, emailId=, password=, provider=UNAVAILABLE, userRoles=[], enabled=false,"
            + " currentIp=, currentLogin=Thu Jan 01 05:30:00 IST 1970, currentLocation=null, lastIp=, lastLogin=Thu"
            + " Jan 01 05:30:00 IST 1970, lastLocation=null)",
        user.toString());
    assertFalse(user.isEnabled());
    assertEquals("", user.getUsername());
    assertEquals(0L, user.getUserId().longValue());
    assertEquals(Provider.UNAVAILABLE, user.getProvider());
    assertEquals("", user.getPassword());
    assertEquals("", user.getName());
    Role role = actualUserRole.getRole();
    assertEquals("Role(roleId=0, name=, userRoles=[])", role.toString());
    assertEquals("", role.getName());
    assertEquals("", user.getCurrentIp());
    assertEquals(0, role.getRoleId().intValue());
    assertEquals("", user.getLastIp());
    assertEquals("", user.getContactNo());
  }
}
