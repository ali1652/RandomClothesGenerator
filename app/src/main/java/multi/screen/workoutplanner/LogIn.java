package multi.screen.workoutplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    EditText inputEmail, inputPassword;
    Button buttonLogIn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        inputEmail = findViewById(R.id.userNameLogIn);
        inputPassword = findViewById(R.id.passwordLogIn);

        buttonLogIn = findViewById(R.id.loginButton);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerfromAuth();
            }
        });
    }

    //authorization for log in feature
    private void PerfromAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        // making sure email is entered in a correct email format
        if (!email.matches(emailPattern)) {
            inputEmail.setError("Enter correct email");
            //password authentication
        } else if (password.isEmpty() || password.length() < 6) {
            inputPassword.setError("Please enter the correct password");
        } else {
            //displays dialog before loading in to home screen
            progressDialog.setMessage("Please wait while we Log In");
            progressDialog.setTitle("Loading....");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        //shows this if sucessful and takes user to home page
                        Toast.makeText(LogIn.this, "Log in Sucessful", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(LogIn.this, "Log in Failed"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void sendUserToNextActivity(){
        Intent intent = new Intent(LogIn.this,Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void ToSplash(View view) {
        Intent intent = new Intent(LogIn.this,SplashScreen.class);
        startActivity(intent);
    }
}