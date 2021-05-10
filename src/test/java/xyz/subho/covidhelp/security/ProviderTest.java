package xyz.subho.covidhelp.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProviderTest {
    @Test
    public void testValueOf() {
        // TODO: This test is incomplete.
        //  No meaningful assertions found.

        Provider.valueOf("Name");
    }

    @Test
    public void testValueOf2() {
        // TODO: This test is incomplete.
        //  No meaningful assertions found.

        Provider.valueOf("foo");
    }

    @Test
    public void testValueOf3() {
        assertEquals(Provider.GITHUB, Provider.valueOf("GITHUB"));
    }

    @Test
    public void testValues() {
        Provider[] actualValuesResult = Provider.values();
        assertEquals(3, actualValuesResult.length);
        assertEquals(Provider.UNAVAILABLE, actualValuesResult[0]);
        assertEquals(Provider.GOOGLE, actualValuesResult[1]);
        assertEquals(Provider.GITHUB, actualValuesResult[2]);
    }
}

