package zuza.lulu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private  MyStringRequest myrqst;
    final String address= "http://172.16.73.161/?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button button_home = (Button) findViewById(R.id.button_home);
        button_home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO : on click home

                Intent i = new Intent(getApplicationContext(), Menu.class);
                startActivity(i);
            }
        });
        myrqst = new MyStringRequest();


        Switch swing_switch= (Switch) findViewById(R.id.switch_swigning);
        Switch notif= (Switch) findViewById((R.id.notification_switch));
        Switch music= (Switch) findViewById(R.id.switch_music);

        notif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)  MySingleton.getInstance(getApplicationContext()).addToRequestQueue(myrqst.zuza(address +"led2=1"));
               else    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(myrqst.zuza(address +"led2=0"));
            }
        });

        swing_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue( myrqst.zuza(address +"led4=1"));
                else MySingleton.getInstance(getApplicationContext()).addToRequestQueue( myrqst.zuza(address +"led4=0"));
            }
        });


    }
}
