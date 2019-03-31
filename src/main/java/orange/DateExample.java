/*
 * Copyright 2006-2018 (c) Care.com, Inc.
 * 1400 Main Street, Waltham, MA, 02451, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Care.com, Inc. ("Confidential Information").  You shall not disclose
 * such Confidential Information and shall use it only in accordance with
 * the terms of an agreement between you and CZen.
 */
package orange;

import java.time.*;
import java.util.TimeZone;
import java.util.stream.Stream;

/**
 * Created 3/21/2019
 *
 * @author sjkumar
 */
public class DateExample {

    static void simple(){
        ZonedDateTime l = LocalDateTime
                .now()
                .atZone(ZoneId.of("America/New_York"));
        System.out.println(l);
//        Stream.of(TimeZone.getAvailableIDs())
//                .filter(s -> s.contains("America") || s.startsWith("E"))
//                .forEach(System.out::println);
        TimeZone t =TimeZone.getTimeZone("PST");
        System.out.println(t.getDisplayName());
        System.out.println(t.getID());
        System.out.println(t.getRawOffset()/1000);
        int hour = 3600*1000;
        System.out.println(ZoneOffset.ofTotalSeconds((t.getRawOffset() - hour*2)/1000));
    }

    static void same(){
        ZonedDateTime d = LocalDateTime
                .now()
                .atZone(ZoneId.of("CET+1"));
        System.out.println(d);
    }
}
