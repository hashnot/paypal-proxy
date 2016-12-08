package com.hashnot.paypal;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rafał Krupiński
 */
public class Flags {
    private static final String OPT_USER = "u";
    private static final String OPT_PASSWD = "p";
    private static final String OPT_SIG = "s";
    private static final String OPT_APPID = "a";
    private static final String OPT_LIVE = "l";

    public static Map<String, String> parseFlags(String[] args) throws ParseException {
        Options opts = new Options();

        opts.addOption(mkOption(OPT_USER, true, "user name", true));
        opts.addOption(mkOption(OPT_PASSWD, true, "password", true));
        opts.addOption(mkOption(OPT_SIG, true, "signature", true));
        opts.addOption(mkOption(OPT_APPID, true, "appId", true));
        opts.addOption(mkOption(OPT_LIVE, false, "live", false));

        CommandLine parse = new DefaultParser().parse(opts, args);

        Map<String, String> result = new HashMap<>();
        result.put("acct1.UserName", parse.getOptionValue(OPT_USER));
        result.put("acct1.Password", parse.getOptionValue(OPT_PASSWD));
        result.put("acct1.Signature", parse.getOptionValue(OPT_SIG));
        result.put("acct1.AppId", parse.getOptionValue(OPT_APPID));

        if (parse.hasOption(OPT_LIVE))
            result.put("mode", "live");
        else
            result.put("mode", "sandbox");

        return result;
    }

    private static Option mkOption(String opt, boolean hasArg, String description, boolean required) {
        Option result = new Option(opt, hasArg, description);
        result.setRequired(required);
        return result;
    }
}
