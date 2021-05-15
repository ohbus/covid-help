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

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "oxygen")
public class OxygenLead {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "oxy_id")
  private Long oxyLeadId;

  private String oxyPropName;
  private String oxyPropContactPrimary;
  private String oxyPropContactSecondary;

  /*
  @ManyToMany(mappedBy = "userId", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<User> volenteerIds;*/

  private BigDecimal locationLat;
  private BigDecimal locationLon;
  private BigDecimal locationAcu;
  private Date locationTimestamp;
  private String locationUrl;

  private Date lastVerifiedAt;
  private Long verifiedCount;
  private Date lastUnavailableAt;
  private Long unavailableCount;

  /** Default Constructor */
  public OxygenLead() {
    this.oxyLeadId = 0L;
    this.oxyPropName = "";
    this.oxyPropContactPrimary = "";
    this.oxyPropContactSecondary = "";
    this.locationLat = BigDecimal.valueOf(0D);
    this.locationLon = BigDecimal.valueOf(0D);
    this.locationAcu = BigDecimal.valueOf(0D);
    this.locationTimestamp = new Date(0);
    this.locationUrl = "";
    this.lastVerifiedAt = new Date(0);
    this.verifiedCount = 0L;
    this.lastUnavailableAt = new Date(0);
    this.unavailableCount = 0L;
  }

  /**
   * @param oxyPropName
   * @param oxyPropContactPrimary
   * @param oxyPropContactSecondary
   * @param locationLat
   * @param locationLon
   * @param locationAcu
   * @param locationTimestamp
   * @param locationUrl
   * @param lastVerifiedAt
   * @param verifiedCount
   * @param lastUnavailableAt
   * @param unavailableCount
   */
  public OxygenLead( // NOSONAR
      String oxyPropName,
      String oxyPropContactPrimary,
      String oxyPropContactSecondary,
      BigDecimal locationLat,
      BigDecimal locationLon,
      BigDecimal locationAcu,
      Date locationTimestamp,
      String locationUrl,
      Date lastVerifiedAt,
      Long verifiedCount,
      Date lastUnavailableAt,
      Long unavailableCount) {
    this.oxyPropName = oxyPropName;
    this.oxyPropContactPrimary = oxyPropContactPrimary;
    this.oxyPropContactSecondary = oxyPropContactSecondary;
    this.locationLat = locationLat;
    this.locationLon = locationLon;
    this.locationAcu = locationAcu;
    this.locationTimestamp = locationTimestamp;
    this.locationUrl = locationUrl;
    this.lastVerifiedAt = lastVerifiedAt;
    this.verifiedCount = verifiedCount;
    this.lastUnavailableAt = lastUnavailableAt;
    this.unavailableCount = unavailableCount;
  }

  /**
   * @param locationLat
   * @param locationLon
   * @param locationAcu
   * @param locationTimestamp
   * @param locationUrl
   */
  public void updateLocation(
      BigDecimal locationLat,
      BigDecimal locationLon,
      BigDecimal locationAcu,
      Date locationTimestamp,
      String locationUrl) {
    this.locationLat = locationLat;
    this.locationLon = locationLon;
    this.locationAcu = locationAcu;
    this.locationTimestamp = locationTimestamp;
    this.locationUrl = locationUrl;
  }

  /**
   * @param locationLat
   * @param locationLon
   * @param locationAcu
   * @param locationTimestamp
   */
  public void updateLocation(
      BigDecimal locationLat,
      BigDecimal locationLon,
      BigDecimal locationAcu,
      Date locationTimestamp) {
    this.locationLat = locationLat;
    this.locationLon = locationLon;
    this.locationAcu = locationAcu;
    this.locationTimestamp = locationTimestamp;
  }

  public void setLastVerifiedAt() {
    this.lastUnavailableAt = new Date(System.currentTimeMillis());
  }

  public void setLastUnavailableAt() {
    this.lastUnavailableAt = new Date(System.currentTimeMillis());
  }

  public void updateVerify() {
    setLastVerifiedAt();
    ++this.verifiedCount;
  }

  public void updateUnavailable() {
    setLastUnavailableAt();
    ++this.unavailableCount;
  }
}
