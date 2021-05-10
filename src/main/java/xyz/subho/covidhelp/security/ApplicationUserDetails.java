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

package xyz.subho.covidhelp.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.subho.covidhelp.entity.User;

@Data
@AllArgsConstructor
public class ApplicationUserDetails implements UserDetails {

  private static final long serialVersionUID = 1487410223802799615L;

  private User user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    var userRoles = user.getUserRoles();
    Set<GrantedAuthority> authorities = new HashSet<>();
    userRoles.forEach(userRole -> authorities.add(new Authority(userRole.getRole().getName())));
    return authorities;
  }

  @Override
  public String getPassword() {
    // Not using password based authentication
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    // Auto-generated method stub
    return user.getEmailId();
  }

  @Override
  public boolean isAccountNonExpired() {
    // Not implemented
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // Not implemented
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // Not implemented
    return true;
  }

  @Override
  public boolean isEnabled() {
    return user.isEnabled();
  }
}
