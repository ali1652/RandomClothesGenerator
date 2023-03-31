package multi.screen.workoutplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import multi.screen.workoutplanner.R;
import multi.screen.workoutplanner.HoodiesJumpers;
import multi.screen.workoutplanner.customCardHoodiesJumpers;

public class HoodiesJumpers extends AppCompatActivity {
    EditText hoodies;
    FirebaseDatabase database;
    DatabaseReference reference;
    List<String> userhoodies;
    TextView randomItemDisplayHoodie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoodies_jumpers);

        hoodies = findViewById(R.id.hoodieInput);
        randomItemDisplayHoodie = findViewById(R.id.randomItemHoodie);

        String gehoodiesInput = hoodies.getText().toString();

        //referencing the database and child node specified
        database = FirebaseDatabase.getInstance("https://workoutplanner-49f96-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("HoodiesJumpers");

        //creatign arrayList for hoodies/jmpers
        userhoodies = new ArrayList<>();


        ListView listViewH = findViewById(R.id.listviewH);

        //setting arrayadapter with the userCoats list and custom card view
        ArrayAdapter<String> arrayAdapter = new customCardHoodiesJumpers(this, userhoodies);
        listViewH.setAdapter(arrayAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clears the list one there has been a change
                userhoodies.clear();
                //then goes through database and adds values back to list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    userhoodies.add(snapshot.getValue().toString());
                }
                //updates array adapter to update
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    // adds data to the firebase database once user has inputted hoodie/jumper
    public void buttonClick(View view) {
        String gethoodiesInput = hoodies.getText().toString();
        hoodies.setText("");
        if (gethoodiesInput.isEmpty()) {
            Toast.makeText(HoodiesJumpers.this, "Enter a hoodie/jumper", Toast.LENGTH_SHORT).show();
        } else {
            //adding to the database
            reference.push().setValue(gethoodiesInput);
            Toast.makeText(HoodiesJumpers.this, "Hoodie/Jumper added", Toast.LENGTH_SHORT).show();
        }



    }
    //generating the random hoodie/jumper and setting to the the random hoodie display box
    public void generateRandomHoodie(View view) {
        Random r = new Random();
        if (!userhoodies.isEmpty()) {
            int randomListNumber = r.nextInt(userhoodies.size());
            String randomShirt = userhoodies.get(randomListNumber);
            randomItemDisplayHoodie.setText(randomShirt);


        } else {
            randomItemDisplayHoodie.setText("Add a hoodie/jumper to you list");
        }
    }
}