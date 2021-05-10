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

package xyz.subho.covidhelp.utils.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.subho.covidhelp.entity.OxygenLead;

@Data
@AllArgsConstructor
public class Location {

  private BigDecimal latitude;
  private BigDecimal longitude;
  private BigDecimal accuracy;
  private Date timestamp;

  /** default */
  public Location() {
    this.latitude = BigDecimal.valueOf(0D);
    this.longitude = BigDecimal.valueOf(0D);
    this.accuracy = BigDecimal.valueOf(0D);
    this.timestamp = new Date(0);
  }
  /**
   * Takes a lead and fetches the location
   *
   * @param OxygenLead
   */
  public Location(OxygenLead oxygenLead) {
    this.latitude = oxygenLead.getLocationLat();
    this.longitude = oxygenLead.getLocationLon();
    this.accuracy = oxygenLead.getLocationAcu();
    this.timestamp = oxygenLead.getLocationTimestamp();
  }

  /**
   * @param Sting(locationString) in DB format
   * @return Location()
   */
  public Location covertUserLocationFromDB(String locationString) {
    String[] splitLocationString = locationString.trim().split(",");
    var location = new Location();
    location.setLatitude(BigDecimal.valueOf(Double.valueOf(splitLocationString[0])));
    location.setLongitude(BigDecimal.valueOf(Double.valueOf(splitLocationString[1])));
    location.setAccuracy(BigDecimal.valueOf(Double.valueOf(splitLocationString[2])));
    location.setTimestamp(new Date(Long.valueOf(splitLocationString[3])));
    return location;
  }

  /**
   * @param location
   * @return String(locationString) in DB format
   */
  public String covertUserLocationToDB(Location location) {
    return ""
        + location.getLatitude().toString()
        + location.getLongitude().toString()
        + location.getAccuracy().toString()
        + convertDateToMilSecForDB(location.getTimestamp());
  }

  /**
   * Default setter for timestmap
   *
   * @param Date
   */
  public void setTimestamp(Date date) {
    this.timestamp = date;
  }

  /**
   * @param date
   * @return String(locationString) in DB format
   */
  public String convertDateToMilSecForDB(Date date) {
    return Long.toString(date.getTime());
  }

  /** This method overloads so that we can automatically generate the timestamp from System Time */
  public void setTimestamp() {
    this.timestamp = new Date(System.currentTimeMillis());
  }

  /**
   * This method is overloaded so that an long literal can be taken for setting timestamp
   *
   * @param Long
   */
  public void setTimestamp(Long currentTimeMillis) {
    this.timestamp = new Date(currentTimeMillis);
  }
}
