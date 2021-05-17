package xyz.subho.covidhelp.utils.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.junit.jupiter.api.Test;
import xyz.subho.covidhelp.entity.OxygenLead;

public class LocationTest {
  @Test
  public void testConstructor() {
    Location actualLocation = new Location();
    assertEquals(
        "Location(latitude=0.0, longitude=0.0, accuracy=0.0, timestamp=Thu Jan 01 05:30:00 IST 1970)",
        actualLocation.toString());
    assertEquals("0.0", actualLocation.getLongitude().toString());
    assertEquals("0.0", actualLocation.getAccuracy().toString());
    assertEquals("0.0", actualLocation.getLatitude().toString());
  }

  @Test
  public void testConstructor2() {
    Location actualLocation = new Location(new OxygenLead());
    assertEquals(
        "Location(latitude=0.0, longitude=0.0, accuracy=0.0, timestamp=Thu Jan 01 05:30:00 IST 1970)",
        actualLocation.toString());
    assertEquals("0.0", actualLocation.getLongitude().toString());
    assertEquals("0.0", actualLocation.getAccuracy().toString());
    assertEquals("0.0", actualLocation.getLatitude().toString());
  }

  @Test
  public void testConstructor3() {
    OxygenLead oxygenLead = new OxygenLead();
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    oxygenLead.setLastVerifiedAt(
        Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
    Location actualLocation = new Location(oxygenLead);
    assertEquals(
        "Location(latitude=0.0, longitude=0.0, accuracy=0.0, timestamp=Thu Jan 01 05:30:00 IST 1970)",
        actualLocation.toString());
    assertEquals("0.0", actualLocation.getLongitude().toString());
    assertEquals("0.0", actualLocation.getAccuracy().toString());
    assertEquals("0.0", actualLocation.getLatitude().toString());
  }

  @Test
  public void testCovertUserLocationFromDB() {

    var location = new Location();
    location.setLatitude(BigDecimal.valueOf(Double.valueOf("22.6105")));
    location.setLongitude(BigDecimal.valueOf(Double.valueOf("88.4325")));
    location.setAccuracy(BigDecimal.valueOf(Double.valueOf("20054")));
    location.setTimestamp(new Date(Long.valueOf("1620752468293")));
    assertEquals(
        (new Location()).covertUserLocationFromDB("22.6105,88.4325,20054,1620752468293"), location);
  }

  @Test
  public void testCovertUserLocationFromDB2() {
    assertThrows(
        ArrayIndexOutOfBoundsException.class, () -> (new Location()).covertUserLocationFromDB(","));
  }

  @Test
  public void testCovertUserLocationFromDB3() {
    assertThrows(
        ArrayIndexOutOfBoundsException.class,
        () -> (new Location()).covertUserLocationFromDB("42"));
  }

  @Test
  public void testCovertUserLocationToDB() {
    Location location = new Location();
    assertEquals("0.00.00.00", location.covertUserLocationToDB(new Location()));
  }

  @Test
  public void testSetTimestamp() {
    Location location = new Location();
    location.setTimestamp(new Date(1L));
    assertEquals(
        "Location(latitude=0.0, longitude=0.0, accuracy=0.0, timestamp=Thu Jan 01 05:30:00 IST 1970)",
        location.toString());
  }

  @Test
  public void testSetTimestamp2() {
    (new Location()).setTimestamp();
  }

  @Test
  public void testSetTimestamp3() {
    Location location = new Location();
    location.setTimestamp(1L);
    assertEquals(
        "Location(latitude=0.0, longitude=0.0, accuracy=0.0, timestamp=Thu Jan 01 05:30:00 IST 1970)",
        location.toString());
  }

  @Test
  public void testConvertDateToMilSecForDB() {
    Location location = new Location();
    assertEquals("1", location.convertDateToMilSecForDB(new Date(1L)));
  }
}
