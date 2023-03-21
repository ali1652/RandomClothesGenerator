package multi.screen.workoutplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WorkoutHomePage extends AppCompatActivity {

    Button b;
    Member member;
    RadioButton male,female,chest,legs,back;
    /*
    Spinner spinner = findViewById(R.id.spinner);
    RadioGroup radioGroup = findViewById(R.id.radioGroup);

     */
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    DatabaseReference reference;
    int i =0;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_home_page);

        b = findViewById(R.id.save_btn);
        member = new Member();
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        name = findViewById(R.id.edit);
        chest = findViewById(R.id.chest);
        legs = findViewById(R.id.legs);
        back = findViewById(R.id.back);


/*
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

 */






        database = FirebaseDatabase.getInstance("https://workoutplanner-49f96-default-rtdb.europe-west1.firebasedatabase.app/");
         /*
         databaseReference = database.getReference("this is the path");
        databaseReference.setValue("here there").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(WorkoutHomePage.this, "Sucess", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(WorkoutHomePage.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

          */

        reference = database.getReference().child("Workouts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m1 = male.getText().toString();
                String m2 = female.getText().toString();
                String getChest = chest.getText().toString();
                String getLegs = legs.getText().toString();
                String getBack = back.getText().toString();

                member.setName(name.getText().toString());
                reference.child(String.valueOf(i+1)).setValue(member);

                if(male.isChecked()){
                    member.setGender(m1);
                    reference.child(String.valueOf(i+1)).setValue(member);
                    Toast.makeText(WorkoutHomePage.this, ":)", Toast.LENGTH_SHORT).show();
                }else if(female.isChecked()){
                    member.setGender(m2);
                    reference.child(String.valueOf(i+1)).setValue(member);
                    Toast.makeText(WorkoutHomePage.this, ":)", Toast.LENGTH_SHORT).show();
                }else if(chest.isChecked()){
                    member.setGender(getChest);
                    reference.child(String.valueOf(i+1)).setValue(member);
                    Toast.makeText(WorkoutHomePage.this, "chest", Toast.LENGTH_SHORT).show();
                }else if(legs.isChecked()){
                    member.setGender(getLegs);
                    reference.child(String.valueOf(i+1)).setValue(member);
                    Toast.makeText(WorkoutHomePage.this, "legs", Toast.LENGTH_SHORT).show();
                }else if(back.isChecked()){
                    member.setGender(getBack);
                    reference.child(String.valueOf(i+1)).setValue(member);
                    Toast.makeText(WorkoutHomePage.this, "back", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(WorkoutHomePage.this, "Select a button", Toast.LENGTH_SHORT).show();

                }
            }
        });
/*
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.radioBtn1);
                        break;
                    case 1:
                        radioGroup.check(R.id.radioBtn2);
                        break;
                    case 2:
                        radioGroup.check(R.id.radioBtn3);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

 */




    }


}