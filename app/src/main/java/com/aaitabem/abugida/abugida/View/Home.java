package com.aaitabem.abugida.abugida.View;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aaitabem.abugida.abugida.R;
import com.aaitabem.abugida.abugida.View.Tabs.HomeFragmenet;
import com.aaitabem.abugida.abugida.View.Tabs.NotPayedTab;
import com.aaitabem.abugida.abugida.View.Tabs.PayedHIstory;
import com.aaitabem.abugida.abugida.View.Tabs.ProfileTab;
import com.aaitabem.abugida.abugida.View.Tabs.SettingsTab;

public class Home extends AppCompatActivity implements View.OnClickListener {

    ImageButton home;
    ImageButton payedHistory;
    ImageButton pendingPayment;
    ImageButton profile;
    ImageButton settings;

    //TextView of the top textView

    TextView textView;
    //Calling the fragments
    HomeFragmenet homeFragmenet;
    NotPayedTab notPayedTab;
    PayedHIstory payedHistoryTab;
    ProfileTab profileTab;
    SettingsTab settingsTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Find the image buttons
        home = findViewById(R.id.homeButotn);
        payedHistory = findViewById(R.id.payedHistoryButton);
        pendingPayment = findViewById(R.id.pendingPaymentButton);
        profile = findViewById(R.id.profileButotn);
        settings = findViewById(R.id.settingsButton);

        //Assigning on click listeners on the following ImageButtons
        home.setOnClickListener(this);
        payedHistory.setOnClickListener(this);
        pendingPayment.setOnClickListener(this);
        profile.setOnClickListener(this);
        settings.setOnClickListener(this);

        //Initializing the fragments
        homeFragmenet = new HomeFragmenet();
        notPayedTab = new NotPayedTab();
        payedHistoryTab = new PayedHIstory();
        profileTab = new ProfileTab();
        settingsTab = new SettingsTab();

        //Finding the title
        textView = findViewById(R.id.titelHome);

        //Adding the fragment when the home is created
        fragmentChanger(1);


    }

    @Override
    public void onClick(View v){

        if(v == home){
            fragmentChanger(1);
        }else if(v ==  payedHistory){
            fragmentChanger(2);
        }else if(v == pendingPayment){
            fragmentChanger(3);
        }else if(v == profile){
            fragmentChanger(4);
        }else if(v == settings){
            fragmentChanger(5);
        }
    }

    //Creating the fragments
    public void fragmentChanger(int id){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if( id ==1){
            fragmentTransaction.replace(R.id.homeFrameLayout, homeFragmenet);
            textView.setText(R.string.homeFragementTitle);
        }else if( id == 2){
            fragmentTransaction.replace(R.id.homeFrameLayout, payedHistoryTab);
            textView.setText(R.string.bookedTravelResult);
        }else if(id ==3){
            fragmentTransaction.replace(R.id.homeFrameLayout, notPayedTab);
            textView.setText(R.string.pendingTicketsTititle);
        }else if(id == 4){
            fragmentTransaction.replace(R.id.homeFrameLayout, profileTab);
            textView.setText(R.string.profileTitle);
        }else if(id == 5){
            fragmentTransaction.replace(R.id.homeFrameLayout, settingsTab);
            textView.setText(R.string.settingTitle);
        }

        fragmentTransaction.commit();
    }
}
