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

public class SignUp extends AppCompatActivity {

    EditText inputUserName, inputEmail, inputPassword, inputPassword2;
    Button signUp;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    //DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputUserName = findViewById(R.id.userName);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        inputPassword2 = findViewById(R.id.confirmPassword);
        signUp = findViewById(R.id.signUpButton);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

       // DB = new DBHelper(this);
    }
    public void signUpFunction(View view) {
        PerforAuth();
    }


    public void toLogIn2(View view) {
        Intent toLogInPage = new Intent(this, LogIn.class);
        startActivity(toLogInPage);
    }

    private void PerforAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String password2 = inputPassword2.getText().toString();

        if(!email.matches(emailPattern))
        {
            inputEmail.setError("Enter correct email");
        }else if(password.isEmpty()|| password.length()<6)
        {
            inputPassword.setError("Please enter a password that is at leat six characters");
        }else if (!password.equals(password2))
        {
            inputPassword2.setError("Passwords not not match");
        }else
        {
            progressDialog.setMessage("Please wait while we register you account");
            progressDialog.setTitle("Registering....");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(SignUp.this, "Registration Sucessful", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }



    }

    private void sendUserToNextActivity(){
        Intent intent = new Intent(SignUp.this,Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


/*
    public void signUpFunction(View view) {
        String user = userName.getText().toString();
        String Email = email.getText().toString();
        String pass = password.getText().toString();
        String repass = password2.getText().toString();

        if(user.equals("")||Email.equals("")|| pass.equals("") || repass.equals(""))
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        else{
            if(pass.equals(repass)) {
                Boolean checkUser = DB.checkUserName(user);
                if (checkUser == false) {
                    Boolean insert = DB.insertData(user, Email, pass);
                    if (insert == true) {
                        Toast.makeText(this, "Registered Sucessfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LogIn.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Passwords not matching", Toast.LENGTH_SHORT).show();
            }
        }


    }

 */
}