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

package xyz.subho.covidhelp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.subho.covidhelp.security.Authority;
import xyz.subho.covidhelp.security.Provider;
import xyz.subho.covidhelp.security.UserRole;

@Entity
@Data
@AllArgsConstructor
public class User implements UserDetails {

  private static final long serialVersionUID = 1366143551256180947L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "userId", nullable = false, updatable = false)
  private Long userId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String contactNo;

  @Column(nullable = false, unique = true)
  private String emailId;

  private String password;
  private String pictureUrl;
  private String givenName;
  private String familyName;
  private String locale;
  private String oAuthUserId;

  @Enumerated(EnumType.STRING)
  private Provider provider;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
  private Set<UserRole> userRoles;

  private boolean enabled;

  private String currentIp;
  private Date currentLogin;
  private String currentLocation;
  private String lastIp;
  private Date lastLogin;
  private String lastLocation;

  // Default Constructor
  public User() {
    this.userId = 0L;
    this.name = "";
    this.contactNo = "";
    this.emailId = "";
    this.password = "";
    this.pictureUrl = "https://nwsid.net/wp-content/uploads/2015/05/dummy-profile-pic.png";
    this.givenName = "";
    this.familyName = "";
    this.locale = "";
    this.oAuthUserId = "";
    this.provider = Provider.UNAVAILABLE;
    this.userRoles = new HashSet<>();
    this.enabled = false;
    this.currentIp = "";
    this.currentLogin = new Date(0);
    this.currentLocation = "";
    this.lastIp = "";
    this.lastLogin = new Date(0);
    this.lastLocation = "";
  }

  /**
   * @param name
   * @param contactNo
   * @param emailId
   * @param password
   * @param currentIp
   * @param currentLocation
   */
  public User(
      String name, String contactNo, String emailId, String currentIp, String currentLocation) {
    this(name, contactNo, emailId);
    updateLogin(currentIp, currentLocation);
  }

  /**
   * @param name
   * @param contactNo
   * @param emailId
   * @param password
   */
  public User(String name, String contactNo, String emailId) {
    this.name = name;
    this.contactNo = contactNo;
    this.emailId = emailId;
  }

  /**
   * @param currentIp
   * @param currentLocation
   */
  public void updateLogin(String currentIp, String currentLocation) {
    this.lastLogin = this.currentLogin;
    this.lastIp = this.currentIp;
    this.lastLocation = this.currentLocation;
    this.currentLogin = new Date(System.currentTimeMillis());
    this.currentIp = currentIp;
    this.currentLocation = currentLocation;
  }

  public void enableUser() {
    this.enabled = true;
  }

  public void disableUser() {
    this.enabled = false;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> authorities = new HashSet<>();
    userRoles.forEach(userRole -> authorities.add(new Authority(userRole.getRole().getName())));
    return authorities;
  }

  @Override
  public String getUsername() {
    // Auto-generated method stub
    return this.emailId;
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
  public boolean isEnabled() {
    return this.enabled;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // Not implemented
    return true;
  }
}
