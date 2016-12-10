package com.hashnot.paypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import urn.ebay.apis.eBLBaseComponents.PaymentTransactionSearchResultType;
import urn.ebay.apis.eBLBaseComponents.PaymentTransactionStatusCodeType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author Rafał Krupiński
 */
@RestController
public class PayPalController {

    public static final String LOCAL_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss";
    private PayPalService payPalService;
    private Executor executor;

    @Autowired
    public PayPalController(PayPalService payPalService, Executor executor) {
        this.payPalService = payPalService;
        this.executor = executor;
    }

    @RequestMapping(path = "/{username}/transactionRef", method = RequestMethod.GET)
    public CompletableFuture<List<PaymentTransactionSearchResultType>> transactionSearch(
            @PathVariable("username")
            String userName,

            @RequestParam("startDate")
            @DateTimeFormat(pattern = LOCAL_DATE_TIME)
                    LocalDateTime startDate,

            @RequestParam(name = "endDate", required = false)
            @DateTimeFormat(pattern = LOCAL_DATE_TIME)
                    LocalDateTime endDate,

            @RequestParam(name = "status", required = false)
                    PaymentTransactionStatusCodeType status
    ) {
        CompletableFuture<List<PaymentTransactionSearchResultType>> result = new CompletableFuture<>();
        executor.execute(() -> {
            try {
                result.complete(payPalService.transactionSearch(userName, startDate, endDate, status).getPaymentTransactions());
            } catch (Exception e) {
                result.completeExceptionally(e);
            }
        });
        return result;
    }
}
