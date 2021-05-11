package xyz.subho.covidhelp.utils.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    // TODO: This test is incomplete.
    //   Reason: No meaningful assertions found.
    //   To help Diffblue Cover to find assertions, please add getters to the
    //   class under test that return fields written by the method under test.
    //   See https://diff.blue/R004

    (new Location()).covertUserLocationFromDB("Location String");
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
