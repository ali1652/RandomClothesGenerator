package multi.screen.workoutplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashScreen extends AppCompatActivity {
    Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);





    }

    public void toSignUp(View view) {
        Intent toSignUpPage = new Intent(this, SignUp.class);
        startActivity(toSignUpPage);

    }

    public void toLogIn(View view){
        Intent toLogInPage = new Intent(this, LogIn.class);
        startActivity(toLogInPage);
    }

}