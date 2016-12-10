package com.hashnot.paypal;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Rafał Krupiński
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public PayPalAPIInterfaceServiceService createPayPalSdk(ApplicationArguments args) {
        return new PayPalAPIInterfaceServiceService(Flags.parseFlags(args));
    }

    @Bean
    public Executor createExecutor() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public PayPalService createPayPalService(PayPalAPIInterfaceServiceService sdk) {
        return new PayPalService(sdk);
    }
}
