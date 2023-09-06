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

public class Trousers extends AppCompatActivity {
    EditText trousers;
    FirebaseDatabase database;
    DatabaseReference reference;
    List<String> usertrousers;
    TextView randomItemDisplayTrouser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trousers);

        trousers = findViewById(R.id.trousersInput);
        randomItemDisplayTrouser = findViewById(R.id.randomItemTrouser);

        String getrousersInput = trousers.getText().toString();

        //referencing the database and child node specified
        database = FirebaseDatabase.getInstance("https://workoutplanner-49f96-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Trousers");

        //creating arrayList for trousers
        usertrousers = new ArrayList<>();


        ListView listViewT = findViewById(R.id.listviewT);

        //setting arrayadapter with the userShirts list and custom card view
        ArrayAdapter<String> arrayAdapter = new customCardTrousers(this, usertrousers);
        listViewT.setAdapter(arrayAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clears the list one there has been a change
                usertrousers.clear();
                //then goes through database and adds values back to list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    usertrousers.add(snapshot.getValue().toString());
                }
                //tells array adapter to update
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    // adds data to the firebase database once user has inputted a trouser
    public void buttonClick(View view) {

        String gettrousersInput = trousers.getText().toString();
        trousers.setText("");
        if (gettrousersInput.isEmpty()) {
            Toast.makeText(Trousers.this, "Enter value", Toast.LENGTH_SHORT).show();
        } else {
            //adding to the database
            reference.push().setValue(gettrousersInput);
            Toast.makeText(Trousers.this, "Trouser added", Toast.LENGTH_SHORT).show();
        }

    }
    //generating the random shirt and setting to the the random shirt display box
    public void generateRandomTrouser(View view) {
        Random r = new Random();
        if (!usertrousers.isEmpty()) {
            int randomListNumber = r.nextInt(usertrousers.size());
            String randomTrouser = usertrousers.get(randomListNumber);
            randomItemDisplayTrouser.setText(randomTrouser);


        } else {
            randomItemDisplayTrouser.setText("Add a trouser to you list");
        }
    }
}