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
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import xyz.subho.covidhelp.security.ApplicationOAuth2User;
import xyz.subho.covidhelp.security.ApplicationOAuth2UserService;
import xyz.subho.covidhelp.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
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
        .oauth2Login()
        .userInfoEndpoint()
        .userService(oauthUserService)
        .and()
        .successHandler(
            new AuthenticationSuccessHandler() {
            	
              @Override
              public void onAuthenticationSuccess(
                  HttpServletRequest request,
                  HttpServletResponse response,
                  Authentication authentication)
                  throws IOException, ServletException {
            	  
            	log.info("AuthenticationSuccessHandler invoked");
            	
            	Map<String, String> oAuth2Attributes = new HashMap<>();
            	  
            	if (authentication instanceof OAuth2AuthenticationToken)	{
            		var oAuth2Authentication = (OAuth2AuthenticationToken)authentication;
            		oAuth2Attributes.put("registrationId", oAuth2Authentication.getAuthorizedClientRegistrationId());
            	} else	log.warn("Could NOT get RegistrationId");
            	
            	log.info("Authentication NAME \t : " + authentication.getName());
                log.info("Authentication PRINCIPAL \t : " + authentication.getPrincipal().toString());
            	
            	if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof OAuth2User)	{
        			OAuth2User auth2User = (OAuth2User) authentication.getPrincipal();
        			oAuth2Attributes.put("username", auth2User.getName());
        			oAuth2Attributes.put("name", auth2User.getAttribute("name"));
        			oAuth2Attributes.put("email", auth2User.getAttribute("email"));
        			oAuth2Attributes.put("email_verified", auth2User.getAttribute("email_verified"));
        			oAuth2Attributes.put("given_name", auth2User.getAttribute("given_name"));
        			oAuth2Attributes.put("family_name", auth2User.getAttribute("family_name"));
        			oAuth2Attributes.put("picture", auth2User.getAttribute("picture"));
        			oAuth2Attributes.put("locale", auth2User.getAttribute("locale"));
        		}
            	  
                
                log.info("Authentication NAME \t : " + authentication.getName());
                log.info("Authentication PRINCIPAL \t : " + authentication.getPrincipal().toString());
                
                ApplicationOAuth2User oauthUser = (ApplicationOAuth2User) authentication.getPrincipal();
                
                
                log.info("OAuth User got from principal" + oauthUser.toString());
                log.info("session {}" + request.getSession());
                
                userService.processOAuthPostLogin(oauthUser);
                
                log.info("Done SAVING into Database");
                
                response.sendRedirect("/dashboard");
              }
            })
        // .defaultSuccessUrl("/dashboard")
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .permitAll()
        .and()
        .exceptionHandling()
        .accessDeniedPage("/403");
  }
}
