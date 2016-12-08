package com.hashnot.paypal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.cli.ParseException;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.TransactionSearchResponseType;
import urn.ebay.apis.eBLBaseComponents.PaymentTransactionSearchResultType;
import urn.ebay.apis.eBLBaseComponents.PaymentTransactionStatusCodeType;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Rafał Krupiński
 */
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        PayPalService payPal = new PayPalService(new PayPalAPIInterfaceServiceService(Flags.parseFlags(args)));

        LocalDateTime startDate = LocalDateTime.of(LocalDate.of(2016, 1, 1), LocalTime.of(0, 0));
        LocalDateTime endDate = startDate.plus(1, ChronoUnit.YEARS);
        TransactionSearchResponseType txnresponse = payPal.transactionSearch(startDate, endDate, PaymentTransactionStatusCodeType.SUCCESS);

        ObjectWriter writer = getObjectWriter();

        SequenceWriter seqWriter = writer.writeValues(System.out).init(true);

        for (PaymentTransactionSearchResultType paymentSearchResult : txnresponse.getPaymentTransactions()) {
            seqWriter.write(paymentSearchResult);
        }
        seqWriter.close();
    }

    private static ObjectWriter getObjectWriter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writer()
                .with(SerializationFeature.INDENT_OUTPUT)
                .without(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
    }

}
