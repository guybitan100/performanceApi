package com.glassboxdigital.command;

import com.glassboxdigital.utils.DateTimeUtil;

public interface ClickhouseCommandsInt {
    String TOP = "top -bn 1 | grep cloff";
    String CLICK_HOUSE_CLIENT = "clickhouse-client -q";
    String CLICK_HOUSE_SELECT_TOTAL_COUNT_PER_HOUR_SESSIONS = String.format("%s \"select count(*), toYYYYMMDD(toDateTime(session_ts)) day, toHour(toDateTime(session_ts)) hour from beacon_event where day=%s group by day,hour order by day,hour desc\"",
            CLICK_HOUSE_CLIENT, DateTimeUtil.getCurrentTimeDate());
    String CLICK_HOUSE_SELECT_SESSION_COUNT_PER_HOUR = String.format("%s \"select count(*), uniq(session_uuid), toYYYYMMDD(toDateTime(session_ts)) day, toHour(toDateTime(session_ts)) hour from beacon_event where day=%s group by day,hour order by day,hour desc\"",
            CLICK_HOUSE_CLIENT, DateTimeUtil.getCurrentTimeDate());
}
