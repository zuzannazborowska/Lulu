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


        Switch swing= (Switch) findViewById(R.id.swing_switch);
        Switch notif= (Switch) findViewById((R.id.notification_switch));
        Switch music= (Switch) findViewById(R.id.switch_music);


        swing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(isChecked){
                   // myrqst.zuza("http://192.168.1.114/?led4=1")
                    // Add a request (in this example, called stringRequest) to your RequestQueue.
                    Toast.makeText(getApplicationContext(),"is checked", Toast.LENGTH_SHORT).show();
                    //MySingleton.getInstance(getApplicationContext()).addToRequestQueue( myrqst.zuza("http://192.168.1.114/?led4=1"));
                }
                else{
                    Toast.makeText(getApplicationContext(), "is not checked", Toast.LENGTH_SHORT).show();
                   // MySingleton.getInstance(getApplicationContext()).addToRequestQueue( myrqst.zuza("http://192.168.1.114/?led4=0"));
                }
            }
        });


    }
}
