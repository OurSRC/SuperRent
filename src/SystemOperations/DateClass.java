/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SystemOperations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Vyas
 */
public class DateClass {
    
    public static Date getDateObject(LocalDate Date,String Time) throws ParseException 
    {
        Date date = ConvertLocalDatetoDate(Date);
        Date time = ConvertStringToTime(Time);
        Date FinalDate = FinalDate(date,time);
        String DATE_FORMAT = "yyyy/MM/dd HH:MM:SS";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return FinalDate;
    }
    
    public static Date ConvertLocalDatetoDate(LocalDate localdate)
    {
        Instant instant = localdate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date res = Date.from(instant);    
        return res;
    }
    
    public static Date ConvertStringToTime(String Time) throws ParseException
    {
        String str = Time + ":00";
        DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        Date date = (Date)formatter.parse(str);
        return date;
    }
    
    public static Date FinalDate(Date date,Date time)
    {
	Calendar calendarA = Calendar.getInstance();
        calendarA.setTime(date);
        Calendar calendarB = Calendar.getInstance();
        calendarB.setTime(time);
        calendarA.set(Calendar.HOUR_OF_DAY, calendarB.get(Calendar.HOUR_OF_DAY));
        calendarA.set(Calendar.MINUTE, calendarB.get(Calendar.MINUTE));
        calendarA.set(Calendar.SECOND, calendarB.get(Calendar.SECOND));
        calendarA.set(Calendar.MILLISECOND, calendarB.get(Calendar.MILLISECOND));
        Date result = calendarA.getTime();
        return result;   
    }
    
}
    

