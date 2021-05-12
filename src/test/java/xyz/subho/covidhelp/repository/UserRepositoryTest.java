package xyz.subho.covidhelp.repository;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import xyz.subho.covidhelp.entity.User;
import xyz.subho.covidhelp.security.Provider;
import xyz.subho.covidhelp.security.UserRole;

@ContextConfiguration(classes = {UserRepository.class})
@EnableAutoConfiguration
@DataJpaTest
@Slf4j
public class UserRepositoryTest {
  @Autowired private UserRepository userRepository;

  @Test
  public void testFindByEmailId() {
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
    log.info("USER 1: " + user.toString());

    User user1 = new User();
    user1.setPassword("iloveyou");
    user1.setLastIp("Last Ip");
    user1.setName("Name");
    user1.setLastLocation("Last Location");
    user1.setEnabled(true);
    user1.setUserId(124L);
    user1.setProvider(Provider.UNAVAILABLE);
    user1.setCurrentIp("Current Ip");
    user1.setUserRoles(new HashSet<UserRole>());
    user1.setEmailId("42");
    user1.setContactNo("Contact No");
    LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
    user1.setLastLogin(Date.from(atStartOfDayResult2.atZone(ZoneId.systemDefault()).toInstant()));
    user1.setCurrentLocation("Current Location");
    LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
    user1.setCurrentLogin(
        Date.from(atStartOfDayResult3.atZone(ZoneId.systemDefault()).toInstant()));
    log.info("USER 2: " + user1.toString());

    log.info("SAVING USER 1 into DB");
    this.userRepository.save(user);
    log.info("SAVING USER 2 into DB");
    this.userRepository.save(user1);
    log.info("Done Saving");
    assertNull(this.userRepository.findByEmailId("42"));
  }
}
