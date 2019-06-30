package com.trl.users.temporary;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestGregorianCalendar {
    public static void main(String[] args) {

        System.out.println(new SimpleDateFormat("dd-MM-yyy").format(new GregorianCalendar(2019, Calendar.JANUARY, 20).getTime()));

    }

}
