package com.aaitabem.abugida.abugida.View;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aaitabem.abugida.abugida.MainActivity;
import com.aaitabem.abugida.abugida.R;

public class Signup extends AppCompatActivity{
    //TODO Make sure to chage the icons on the account information display
    public Signup(){

    }
    PersonInfoFragment personInfoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //lets set the Fragment one here;
        personInfoFragment = new PersonInfoFragment(
                "",
                "",
                "",
                "Date of Birth",
                "",
                "",
                "",
                ""
        );

        if(savedInstanceState == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.setCustomAnimations(R.animator.exit_left_toright,R.animator.exit_left_toright,0,0);
            fragmentTransaction.replace(R.id.signupFrameLayout, personInfoFragment, "one");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);

    }

}
