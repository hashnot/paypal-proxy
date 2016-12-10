package com.hashnot.paypal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import urn.ebay.apis.eBLBaseComponents.PaymentTransactionSearchResultType;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Rafał Krupiński
 */
public class JsonTest {
    @Test
    public void testUnmarshal() throws IOException {
        InputStream input = getClass().getResourceAsStream("testUnmarshal.json");
        assertNotNull(input);

        ObjectMapper mapper = new ObjectMapper();
        PaymentTransactionSearchResultType value = mapper.readerFor(PaymentTransactionSearchResultType.class).readValue(input);

        assertNotNull(value);
        assertEquals(value.getStatus(), "Completed");
    }
}
