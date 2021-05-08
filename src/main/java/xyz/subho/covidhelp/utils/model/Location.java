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

  /*
   * Default setter for timestmap
   *
   * @param Date
   */
  public void setTimestamp(Date date) {
    this.timestamp = date;
  }

  /*
   * This method overloads so that we can automatically generate the timestamp from System Time
   */
  public void setTimestamp() {
    this.timestamp = new Date(System.currentTimeMillis());
  }

  /*
   * This method is overloaded so that an long literal can be taken for setting timestamp
   *
   * @param Long
   */
  public void setTimestamp(Long currentTimeMillis) {
    this.timestamp = new Date(currentTimeMillis);
  }
}
