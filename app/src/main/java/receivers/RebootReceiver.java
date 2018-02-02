package receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Alink on 10-07-2016.
 */
public class RebootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        startAlarmOnReboot(context);
    }

    public static void startAlarmOnReboot(Context context){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, Calendar.MINUTE);
        long timeInterval = c.getTimeInMillis();

        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (AlarmManager.INTERVAL_FIFTEEN_MINUTES/15), AlarmManager.INTERVAL_FIFTEEN_MINUTES/15, pendingIntent);
    }
}
