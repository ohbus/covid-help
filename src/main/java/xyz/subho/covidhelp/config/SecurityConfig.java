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

package xyz.subho.covidhelp.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import xyz.subho.covidhelp.security.ApplicationOAuth2User;
import xyz.subho.covidhelp.security.ApplicationOAuth2UserService;
import xyz.subho.covidhelp.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private ApplicationOAuth2UserService oauthUserService;

  @Autowired private UserService userService;

  private static final String[] PUBLIC_MATCHERS = {
    "/webjars/**",
    "/css/**",
    "/js/**",
    "/images/**",
    "/oauth/**",
    "/",
    "/login/**",
    "/about/**",
    "/contact/**",
    "/tos",
    "/pp",
    "/error/**",
    "/console/**",
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.antMatcher("/**")
        .authorizeRequests()
        .antMatchers(PUBLIC_MATCHERS)
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .oauth2Login();
  }

  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {

    ApplicationOAuth2User oauthUser = (ApplicationOAuth2User) authentication.getPrincipal();
    userService.processOAuthPostLogin(oauthUser);

    response.sendRedirect("/dashboard");
  }
}