package com.aaitabem.abugida.abugida.View.Tabs;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.aaitabem.abugida.abugida.Controller.Notification_reciever;
import com.aaitabem.abugida.abugida.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsTab extends Fragment implements View.OnClickListener {

    Switch aSwitch;
    String seleceted ="Every day";
    TextView frequency,color,languge;
    public Date date;
    String[] colorList,langugeList,frequencyList;
    public static boolean booked = false;
    public SettingsTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_settings_tab, container, false);
        aSwitch = view.findViewById(R.id.switch1);

        frequency = view.findViewById(R.id.frequency);
        color = view.findViewById(R.id.color);
        languge = view.findViewById(R.id.languge);
        aSwitch.setClickable(false);
        if (booked){
            aSwitch.setClickable(true);
            aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked ) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY,7);
                        calendar.set(Calendar.MINUTE,0);
                        calendar.set(Calendar.SECOND,0);
                        Toast.makeText(getActivity(),seleceted + booked,Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(getActivity(), Notification_reciever.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                        if (seleceted == ("Once")){
                            long time = System.currentTimeMillis();
                            //calendar.set(Calendar.SECOND,date.getSeconds());
                            alarmManager.setRepeating(alarmManager.RTC_WAKEUP,time + 10*1000,alarmManager.INTERVAL_DAY,pendingIntent);

                        }
                        else {
                            alarmManager.setRepeating(alarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY,pendingIntent);
                        }


                        Toast.makeText(getActivity(),"Notifiction",Toast.LENGTH_SHORT).show();

                    }
                }

            });
            frequency.setOnClickListener(this);
            color.setOnClickListener(this);
            languge.setOnClickListener(this);
        }


        return view;
    }

    @Override
    public void onClick(View view) {



        if (view == frequency){
            frequencyList = new String[] {"Once","Every day"};
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Choose Notification Frequency");
            builder.setIcon(R.drawable.ic_list_black_24dp);
            builder.setSingleChoiceItems(frequencyList, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    frequency.setText(frequencyList[i]);
                    dialogInterface.dismiss();
                    seleceted = frequencyList[i].toString();
                }
            });
            builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create();
            builder.show();
        }
        else if (view == languge){
            langugeList = new String[] {"English","Amharic"};
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Choose Languages");
            builder.setIcon(R.drawable.ic_list_black_24dp);
            builder.setSingleChoiceItems(langugeList, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    languge.setText(langugeList[i]);
                    dialogInterface.dismiss();
                }
            });
            builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create();
            builder.show();
        }
        else if (view == color){
            colorList = new String[] {"LightBlue","Red","DarkBlue","LightGreen","pink"};
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Choose Colors");
            builder.setIcon(R.drawable.ic_list_black_24dp);
            builder.setSingleChoiceItems(colorList, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    color.setText(colorList[i]);
                    dialogInterface.dismiss();
                }
            });
            builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create();
            builder.show();
        }

    }
}