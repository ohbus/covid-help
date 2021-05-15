package xyz.subho.covidhelp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.subho.covidhelp.entity.User;
import xyz.subho.covidhelp.service.UserService;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  @Autowired private UserService userService;

  @GetMapping("/get/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    try {
      log.info("At GET User");
      return ResponseEntity.ok(userService.getUserById(userId));
    } catch (Exception e) {
      log.info("At GET User: User Not Presnet");
      return ResponseEntity.notFound().build();
    }
  }
}
