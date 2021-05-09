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

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long userId;

  private String name;
  private String contactNo;
  private String emailId;

  private String currentIp;
  private Date currentLogin;
  private String lastIp;
  private Date lastLogin;

  /** Default Constructor */
  public User() {
    this.userId = 0L;
    this.name = "";
    this.contactNo = "";
    this.emailId = "";
    this.currentIp = "";
    this.currentLogin = new Date(0);
    this.lastIp = "";
    this.lastLogin = new Date(0);
  }

  /**
   * @param name
   * @param contactNo
   * @param emailId
   * @param lastIp
   * @param lastLogin
   * @param oxygenLeadsList
   */
  public User(String name, String contactNo, String emailId, String lastIp, Date lastLogin) {
    this.name = name;
    this.contactNo = contactNo;
    this.emailId = emailId;
    this.lastIp = lastIp;
    this.lastLogin = lastLogin;
  }

  public void updateLastLogin(String currentIp) {
    this.lastLogin = this.currentLogin;
    this.lastIp = this.currentIp;
    this.currentLogin = new Date(System.currentTimeMillis());
    this.currentIp = currentIp;
  }
}
