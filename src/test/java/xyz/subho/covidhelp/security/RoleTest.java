package xyz.subho.covidhelp.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RoleTest {
    @Test
    public void testConstructor() {
        Role actualRole = new Role("Name", new HashSet<UserRole>());
        assertEquals("Name", actualRole.getName());
        assertEquals("Role(roleId=null, name=Name, userRoles=[])", actualRole.toString());
    }

    @Test
    public void testConstructor2() {
        Role actualRole = new Role();
        assertEquals("", actualRole.getName());
        assertEquals("Role(roleId=0, name=, userRoles=[])", actualRole.toString());
        assertEquals(0, actualRole.getRoleId().intValue());
    }
}

