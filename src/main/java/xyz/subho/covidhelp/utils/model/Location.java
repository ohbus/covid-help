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

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Location {

  private Double latitude;
  private Double longitude;
  private Double accuracy;
  private Date timestamp;

  /**
   * Default setter for timestmap
   *
   * @param Date
   */
  public void setTimestamp(Date date) {
    this.timestamp = date;
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
