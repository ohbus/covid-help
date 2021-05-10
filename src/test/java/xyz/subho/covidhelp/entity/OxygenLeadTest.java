package xyz.subho.covidhelp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class OxygenLeadTest {
    @Test
    public void testConstructor() {
        BigDecimal locationLat = BigDecimal.valueOf(42L);
        BigDecimal locationLon = BigDecimal.valueOf(42L);
        BigDecimal locationAcu = BigDecimal.valueOf(42L);
        Date locationTimestamp = new Date(1L);
        Date lastVerifiedAt = new Date(1L);
        OxygenLead actualOxygenLead = new OxygenLead("Oxy Prop Name", "Oxy Prop Contact Primary",
                "Oxy Prop Contact Secondary", locationLat, locationLon, locationAcu, locationTimestamp,
                "https://example.org/example", lastVerifiedAt, 3, new Date(1L), 3);
        assertEquals("OxygenLead(oxyLeadId=null, oxyPropName=Oxy Prop Name, oxyPropContactPrimary=Oxy Prop Contact Primary,"
                + " oxyPropContactSecondary=Oxy Prop Contact Secondary, locationLat=42, locationLon=42, locationAcu=42,"
                + " locationTimestamp=Thu Jan 01 05:30:00 IST 1970, locationUrl=https://example.org/example, lastVerifiedAt=Thu"
                + " Jan 01 05:30:00 IST 1970, verifiedCount=3, lastUnavailableAt=Thu Jan 01 05:30:00 IST 1970,"
                + " unavailableCount=3)", actualOxygenLead.toString());
        assertEquals("Oxy Prop Contact Primary", actualOxygenLead.getOxyPropContactPrimary());
        assertEquals("Oxy Prop Contact Secondary", actualOxygenLead.getOxyPropContactSecondary());
        assertEquals("Oxy Prop Name", actualOxygenLead.getOxyPropName());
        assertEquals(3, actualOxygenLead.getUnavailableCount().intValue());
        assertEquals(3, actualOxygenLead.getVerifiedCount().intValue());
        assertEquals("https://example.org/example", actualOxygenLead.getLocationUrl());
        assertEquals("42", actualOxygenLead.getLocationLat().toString());
        assertEquals("42", actualOxygenLead.getLocationAcu().toString());
        assertEquals("42", actualOxygenLead.getLocationLon().toString());
    }

    @Test
    public void testConstructor2() {
        OxygenLead actualOxygenLead = new OxygenLead();
        assertEquals(
                "OxygenLead(oxyLeadId=0, oxyPropName=, oxyPropContactPrimary=, oxyPropContactSecondary=, locationLat=0.0,"
                        + " locationLon=0.0, locationAcu=0.0, locationTimestamp=Thu Jan 01 05:30:00 IST 1970, locationUrl=,"
                        + " lastVerifiedAt=Thu Jan 01 05:30:00 IST 1970, verifiedCount=0, lastUnavailableAt=Thu Jan 01 05:30:00"
                        + " IST 1970, unavailableCount=0)",
                actualOxygenLead.toString());
        assertEquals(0L, actualOxygenLead.getOxyLeadId().longValue());
        assertEquals("", actualOxygenLead.getOxyPropContactPrimary());
        assertEquals("", actualOxygenLead.getOxyPropContactSecondary());
        assertEquals("", actualOxygenLead.getOxyPropName());
        assertEquals(0, actualOxygenLead.getUnavailableCount().intValue());
        assertEquals(0, actualOxygenLead.getVerifiedCount().intValue());
        assertEquals("", actualOxygenLead.getLocationUrl());
        assertEquals("0.0", actualOxygenLead.getLocationLat().toString());
        assertEquals("0.0", actualOxygenLead.getLocationAcu().toString());
        assertEquals("0.0", actualOxygenLead.getLocationLon().toString());
    }

    @Test
    public void testUpdateLocation() {
        OxygenLead oxygenLead = new OxygenLead();
        BigDecimal locationLat = BigDecimal.valueOf(42L);
        BigDecimal locationLon = BigDecimal.valueOf(42L);
        BigDecimal locationAcu = BigDecimal.valueOf(42L);
        oxygenLead.updateLocation(locationLat, locationLon, locationAcu, new Date(1L));
        assertEquals(
                "OxygenLead(oxyLeadId=0, oxyPropName=, oxyPropContactPrimary=, oxyPropContactSecondary=, locationLat=42,"
                        + " locationLon=42, locationAcu=42, locationTimestamp=Thu Jan 01 05:30:00 IST 1970, locationUrl=,"
                        + " lastVerifiedAt=Thu Jan 01 05:30:00 IST 1970, verifiedCount=0, lastUnavailableAt=Thu Jan 01 05:30:00"
                        + " IST 1970, unavailableCount=0)",
                oxygenLead.toString());
    }

    @Test
    public void testUpdateLocation2() {
        OxygenLead oxygenLead = new OxygenLead();
        BigDecimal locationLat = BigDecimal.valueOf(42L);
        BigDecimal locationLon = BigDecimal.valueOf(42L);
        BigDecimal locationAcu = BigDecimal.valueOf(42L);
        oxygenLead.updateLocation(locationLat, locationLon, locationAcu, new Date(1L), "https://example.org/example");
        assertEquals(
                "OxygenLead(oxyLeadId=0, oxyPropName=, oxyPropContactPrimary=, oxyPropContactSecondary=, locationLat=42,"
                        + " locationLon=42, locationAcu=42, locationTimestamp=Thu Jan 01 05:30:00 IST 1970, locationUrl=https:/"
                        + "/example.org/example, lastVerifiedAt=Thu Jan 01 05:30:00 IST 1970, verifiedCount=0, lastUnavailableAt=Thu"
                        + " Jan 01 05:30:00 IST 1970, unavailableCount=0)",
                oxygenLead.toString());
        assertEquals("https://example.org/example", oxygenLead.getLocationUrl());
    }

    @Test
    public void testSetLastVerifiedAt() {
        (new OxygenLead()).setLastVerifiedAt();
    }

    @Test
    public void testSetLastUnavailableAt() {
        (new OxygenLead()).setLastUnavailableAt();
    }

    @Test
    public void testUpdateVerify() {
        OxygenLead oxygenLead = new OxygenLead();
        oxygenLead.updateVerify();
        assertEquals(1, oxygenLead.getVerifiedCount().intValue());
    }

    @Test
    public void testUpdateUnavailable() {
        OxygenLead oxygenLead = new OxygenLead();
        oxygenLead.updateUnavailable();
        assertEquals(1, oxygenLead.getUnavailableCount().intValue());
    }
}

