package multi.screen.workoutplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = (TextView) findViewById(R.id.userName);
        TextView password = (TextView) findViewById(R.id.password);

        Button login = (Button) findViewById(R.id.loginButton);

        //admin and admin

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin")&& password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this,"login Suceess",Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(MainActivity.this,"login failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}