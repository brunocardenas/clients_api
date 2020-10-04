package com.clients.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

    public static Date parseDate(String date) throws ParseException {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
}
