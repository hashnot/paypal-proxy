package com.hashnot.paypal;

import org.springframework.boot.ApplicationArguments;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rafał Krupiński
 */
public class Flags {
    public static Map<String, String> parseFlags(ApplicationArguments args) {
        Map<String, String> result = new HashMap<>();
        result.put("acct1.UserName", args.getOptionValues("username").get(0));
        result.put("acct1.Password", args.getOptionValues("password").get(0));
        result.put("acct1.Signature", args.getOptionValues("signature").get(0));
        result.put("acct1.AppId", args.getOptionValues("appid").get(0));

        if (args.containsOption("live"))
            result.put("mode", "live");
        else
            result.put("mode", "sandbox");

        return result;
    }
}
