package com.hashnot.paypal;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import urn.ebay.apis.eBLBaseComponents.PaymentTransactionSearchResultType;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Rafał Krupiński
 */
public class JsonTest {
    @Test
    public void testUnmarshal() throws IOException {
        InputStream input = getClass().getResourceAsStream("testUnmarshal.json");
        assertNotNull(input);

        ObjectMapper mapper = new ObjectMapper();
        MappingIterator<PaymentTransactionSearchResultType> iterator = mapper.readerFor(PaymentTransactionSearchResultType.class).readValues(input);

        assertTrue(iterator.hasNext());
        PaymentTransactionSearchResultType next = iterator.next();
        assertNotNull(next);
        assertEquals(next.getStatus(), "Completed");
    }
}
