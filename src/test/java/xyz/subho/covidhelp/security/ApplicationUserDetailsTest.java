package xyz.subho.covidhelp.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import xyz.subho.covidhelp.entity.User;

public class ApplicationUserDetailsTest {
    @Test
    public void testGetAuthorities() {
        assertTrue((new ApplicationUserDetails(new User())).getAuthorities().isEmpty());
    }

    @Test
    public void testGetAuthorities2() {
        User user = new User("Name", "Contact No", "42", "iloveyou");
        user.setUserRoles(new HashSet<UserRole>());
        assertTrue((new ApplicationUserDetails(user)).getAuthorities().isEmpty());
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

        ApplicationUserDetails applicationUserDetails = new ApplicationUserDetails(null);
        applicationUserDetails.setUser(user);
        assertTrue(applicationUserDetails.getAuthorities().isEmpty());
    }

    @Test
    public void testGetAuthorities4() {
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

        User user1 = new User("Name", "Contact No", "42", "iloveyou");
        user1.setUserRoles(userRoleSet);
        assertEquals(1, (new ApplicationUserDetails(user1)).getAuthorities().size());
    }

    @Test
    public void testGetPassword() {
        assertEquals("", (new ApplicationUserDetails(new User())).getPassword());
    }

    @Test
    public void testGetPassword2() {
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

        ApplicationUserDetails applicationUserDetails = new ApplicationUserDetails(null);
        applicationUserDetails.setUser(user);
        assertEquals("iloveyou", applicationUserDetails.getPassword());
    }

    @Test
    public void testGetUsername() {
        assertEquals("", (new ApplicationUserDetails(new User())).getUsername());
    }

    @Test
    public void testGetUsername2() {
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

        ApplicationUserDetails applicationUserDetails = new ApplicationUserDetails(null);
        applicationUserDetails.setUser(user);
        assertEquals("42", applicationUserDetails.getUsername());
    }

    @Test
    public void testIsEnabled() {
        assertFalse((new ApplicationUserDetails(new User())).isEnabled());
    }

    @Test
    public void testIsEnabled2() {
        HashSet<UserRole> userRoles = new HashSet<UserRole>();
        Date currentLogin = new Date(1L);
        assertTrue((new ApplicationUserDetails(new User(123L, "Name", "Contact No", "42", "iloveyou", Provider.UNAVAILABLE,
                userRoles, true, "Current Ip", currentLogin, "Current Location", "Last Ip", new Date(1L), "Last Location")))
                .isEnabled());
    }

    @Test
    public void testIsEnabled3() {
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

        ApplicationUserDetails applicationUserDetails = new ApplicationUserDetails(null);
        applicationUserDetails.setUser(user);
        assertTrue(applicationUserDetails.isEnabled());
    }

    @Test
    public void testConstructor() {
        ApplicationUserDetails actualApplicationUserDetails = new ApplicationUserDetails(new User());
        assertTrue(actualApplicationUserDetails.isAccountNonExpired());
        assertTrue(actualApplicationUserDetails.isAccountNonLocked());
        assertTrue(actualApplicationUserDetails.isCredentialsNonExpired());
    }
}

