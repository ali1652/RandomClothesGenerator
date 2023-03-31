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

        //referencing the database
        database = FirebaseDatabase.getInstance("https://workoutplanner-49f96-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("HoodiesJumpers");


        userhoodies = new ArrayList<>();


        ListView listViewH = findViewById(R.id.listviewH);

        ArrayAdapter<String> arrayAdapter = new customCardHoodiesJumpers(this, userhoodies);
        listViewH.setAdapter(arrayAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userhoodies.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    userhoodies.add(snapshot.getValue().toString());
                    //String getID = snapshot.getKey();
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    // adds data to the firebase database once user has inputted a trouser

    public void buttonClick(View view) {

        String gethoodiesInput = hoodies.getText().toString();
        hoodies.setText("");
        if (gethoodiesInput.isEmpty()) {
            Toast.makeText(HoodiesJumpers.this, "Enter a hoodie/jumper", Toast.LENGTH_SHORT).show();
        } else {
            reference.push().setValue(gethoodiesInput);
            Toast.makeText(HoodiesJumpers.this, "Hoodie/Jumper added", Toast.LENGTH_SHORT).show();
        }

        /*
        reference2 = database.getReference("hoodies");
        String trouserName = gethoodiesInput;
        String id = reference2.push().getKey();

        trouser trouser = new trouser(trouserName, id);
        assert id != null;
        reference2.child(id).setValue(trouser);
        // reference.push().setValue(trouser);

    }

         */






    }

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