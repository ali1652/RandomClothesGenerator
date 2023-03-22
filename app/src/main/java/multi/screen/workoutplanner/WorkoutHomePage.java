package multi.screen.workoutplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.Collections;

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
    TextView textView;

    ListView l;
    String tutorials[]
            = { "Algorithms", "Data Structures",
            "Languages", "Interview Corner",
            "GATE", "ISRO CS",
            "UGC NET CS", "CS Subjects",
            "Web Technologies" };

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

        //textView = findViewById(R.id.textView);


        TextView textView;
        boolean[] selectedLanguage;
        ArrayList<Integer> langList = new ArrayList<>();
        String[] langArray = {"Java", "C++", "Kotlin", "C", "Python", "Javascript"};

        // assign variable
        textView = findViewById(R.id.textView);

        // initialize selected language array
        selectedLanguage = new boolean[langArray.length];

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutHomePage.this);

                // set title
                builder.setTitle("Select Excercises");

                // set dialog non cancelable
                builder.setCancelable(false);

                ////// Creating workout
                builder.setMultiChoiceItems(langArray, selectedLanguage, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            langList.add(i);
                            // Sort array list
                            Collections.sort(langList);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            langList.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < langList.size(); j++) {
                            // concat array value
                            stringBuilder.append(langArray[langList.get(j)]);
                            // check condition
                            if (j != langList.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        textView.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // use for loop
                        for (int j = 0; j < selectedLanguage.length; j++) {
                            // remove all selection
                            selectedLanguage[j] = false;
                            // clear language list
                            langList.clear();
                            // clear text view value
                            textView.setText("");
                        }
                    }
                });
                // show dialog
                builder.show();
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////











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