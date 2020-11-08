import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.lang.Object.*;
//import java.lang.Object.org.apache.commons.lang3.time.DateUtils;
//import org.apache.commons.lang.time.DateUtils;
//import java.org.apache.commons.lang3.time.*;
import org.apache.commons.lang3.time.DateUtils;
//import org.apache.commons.lang.time.*;
import org.apache.commons.lang3.*;


public class Time
{    
    Date d;
    Time(){
            d = new Date(System.currentTimeMillis());
    }
    Time(String time){

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");  
        try {  
            this.d = formatter.parse(time);  
            
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    double diff(Time entry)//this method returns the difference in time between the 2 date objects as a double data type, useful for calculating bill amounts
    {   
        double timeDiff =Math.abs(((entry.d.getTime() - this.d.getTime())/3600000.0)) ;
        return timeDiff;
    }
    String getTime()
    {
        return d.toString();
    }
    void setTime(String time)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");  
        try {  
            this.d = formatter.parse(time);  
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    boolean isGreaterThan(Time t)
    {
        double d = ((t.d.getTime() - this.d.getTime())/60000.0);
        if(d>0.0)
        return false;
        return true;
    }
    Time add(int x)
    {
        Time t = new Time();
        t.d = DateUtils.addMinutes(t.d, x); //add minute
        //Date targetTime = new Date(); //now
        //targetTime = DateUtils.addMinutes(targetTime, x); //add minute
        //Time t = new Time();
        return t;
    }
}

