package receivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.example.alink.huerto.ListaTareasActivity;
import com.example.alink.huerto.Planta;
import com.example.alink.huerto.R;

/**
 * Created by Alink on 09-07-2016.
 */
public class AlarmReceiver extends BroadcastReceiver{

    private Planta planta;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle b = intent.getExtras();
        planta = (Planta)b.get("planta");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(context, ListaTareasActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(planta.getNombre())
                .setContentText("¡Acuérdate de regar y cuidar tu " + planta.getNombre() + "!")
                .setSound(alarmSound)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        notificationManager.notify(0, notifyBuilder.build());
    }
}
