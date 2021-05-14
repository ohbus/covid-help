/*
 * The MIT License
 * Copyright © 2021 Subhrodip Mohanta
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package xyz.subho.covidhelp.service.impl;

import java.math.BigInteger;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.subho.covidhelp.entity.User;
import xyz.subho.covidhelp.repository.UserRepository;
import xyz.subho.covidhelp.security.Provider;
import xyz.subho.covidhelp.service.UserService;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Override
  public User getUserByEmailId(String emailId) {
    return userRepository.findByEmailId(emailId);
  }

  @Override
  public boolean isUserEnabled(Long id) {
    var findUser = userRepository.findById(id);
    var status = false;
    if (findUser.isPresent()) status = findUser.get().isEnabled();
    return status;
  }

  @Override
  public boolean isUserEnabled(User user) {
    return isUserEnabled(user.getUserId());
  }

  @Override
  public void processOAuthPostLogin(Map<String, String> oauthUserAttributes) {
    var user = new User();
    var currentUserEmail = oauthUserAttributes.get("email");
    try {
      user = getUserByEmailId(currentUserEmail);
      if (user.getEmailId().equals(currentUserEmail)) {
        log.info("User Already exists");
      } else {
        user.enableUser();
        user.setProvider(
            oauthUserAttributes.get("provider").equalsIgnoreCase(Provider.GOOGLE.toString())
                ? Provider.GOOGLE
                : Provider.UNAVAILABLE);
        user.setName(oauthUserAttributes.get("name"));
        user.setEmailId(currentUserEmail);
        user.setPictureUrl(oauthUserAttributes.get("picture"));
        user.setGivenName(oauthUserAttributes.get("given_name"));
        user.setFamilyName(oauthUserAttributes.get("family_name"));
        user.setOAuthUserId(new BigInteger(oauthUserAttributes.get("oAuthUserId")));
        user.setLocale(oauthUserAttributes.get("locale"));
      }
    } catch (Exception e) {
      log.warn("Exception occured while at OAuth Post Processing: " + e.toString());
    } finally {
      log.info("saving OAuth User: " + user.toString());
      userRepository.save(user);
    }
  }
}
