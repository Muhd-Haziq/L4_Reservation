package sg.edu.rp.c346.i.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText fName;
    EditText phone;
    EditText pax;
    DatePicker dp;
    TimePicker tp;
    CheckBox checkSmoke;
    Button btnReset;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fName = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        pax = findViewById(R.id.pax);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        checkSmoke = findViewById(R.id.smokeCheck);
        btnReset = findViewById(R.id.buttonReset);
        btnSubmit = findViewById(R.id.buttonSubmit);
        dp.setMinDate(System.currentTimeMillis());


        resetAll();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fName.getText().toString().trim().length() != 0 && phone.getText().toString().trim().length() != 0 && pax.getText().toString().trim().length() != 0){
                    String name =  fName.getText().toString().trim();
                    String num = phone.getText().toString().trim();
                    String people = pax.getText().toString().trim();
                    int year = dp.getYear();
                    int month = dp.getMonth()+1;
                    int day = dp.getDayOfMonth();
                    int hour = tp.getCurrentHour();
                    int min = tp.getCurrentMinute();

                    String smoke = "";
                    if(checkSmoke.isChecked()){
                        smoke = "Yes";
                    }else{
                        smoke = "No";
                    }


                    String finalSubmit = String.format("Name: %s\nPhone: %s\nNo. of People: %s\nDate: %d/%d/%d\nTime: %d:%d\nSmoking Table? %s",
                            name, num, people, day, month, year, hour, min, smoke);


                    Toast confirm = Toast.makeText(MainActivity.this, finalSubmit, Toast.LENGTH_LONG);
                    confirm.show();
                }else{
                    Toast.makeText(MainActivity.this, "One or more of the fields are empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                if(!(hourOfDay >= 8 && hourOfDay <= 20)){
                    Toast.makeText(MainActivity.this, "Valid timing from 8am to 8:59pm.", Toast.LENGTH_SHORT).show();
                    updateTime(8,0);
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAll();
            }
        });
    }

    private void updateTime(int hour, int min){
        tp.setCurrentHour(hour);
        tp.setCurrentMinute(min);
    }

    private void resetAll(){
        updateTime(19,30);
        dp.updateDate(2020,5,1);
        fName.setText("");
        phone.setText("");
        pax.setText("");
        checkSmoke.setChecked(false);
    }
}
