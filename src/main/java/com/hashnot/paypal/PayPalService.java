package com.hashnot.paypal;

import urn.ebay.api.PayPalAPI.*;
import urn.ebay.apis.eBLBaseComponents.PaymentTransactionStatusCodeType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * @author Rafał Krupiński
 */
public class PayPalService {
    private PayPalAPIInterfaceServiceService service;

    private DateTimeFormatter dateTimeFormatter;

    {
        dateTimeFormatter = new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE_TIME).appendLiteral('Z').toFormatter();
    }

    public PayPalService(PayPalAPIInterfaceServiceService service) {
        this.service = service;
    }

    public TransactionSearchResponseType transactionSearch(String userName, LocalDateTime startDate, LocalDateTime endDate, PaymentTransactionStatusCodeType status) {
        TransactionSearchRequestType requestType = new TransactionSearchRequestType();

        requestType.setStartDate(dateTimeFormatter.format(startDate));

        if (endDate != null)
            requestType.setEndDate(dateTimeFormatter.format(endDate));

        if (status != null)
            requestType.setStatus(status);

        return transactionSearch(userName, requestType);
    }

    public TransactionSearchResponseType transactionSearch(String userName, TransactionSearchRequestType criteria) {
        TransactionSearchReq txnreq = new TransactionSearchReq();
        txnreq.setTransactionSearchRequest(criteria);
        try {
            return service.transactionSearch(txnreq, userName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public GetTransactionDetailsResponseType getTransactionDetails(String transactionID) {
        GetTransactionDetailsReq req = new GetTransactionDetailsReq();
        GetTransactionDetailsRequestType req1 = new GetTransactionDetailsRequestType();
        req1.setTransactionID(transactionID);

        req.setGetTransactionDetailsRequest(req1);
        try {
            return service.getTransactionDetails(req);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
