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

        //referencing the database
        database = FirebaseDatabase.getInstance("https://workoutplanner-49f96-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Trousers");


        usertrousers = new ArrayList<>();


        ListView listViewT = findViewById(R.id.listviewT);

        ArrayAdapter<String> arrayAdapter = new customCardTrousers(this, usertrousers);
        listViewT.setAdapter(arrayAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usertrousers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    usertrousers.add(snapshot.getValue().toString());
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

        String gettrousersInput = trousers.getText().toString();

        if (gettrousersInput.isEmpty()) {
            Toast.makeText(Trousers.this, "Enter value", Toast.LENGTH_SHORT).show();
        } else {
            reference.push().setValue(gettrousersInput);
            Toast.makeText(Trousers.this, "trouser added", Toast.LENGTH_SHORT).show();
        }

        /*
        reference2 = database.getReference("trousers");
        String trouserName = gettrousersInput;
        String id = reference2.push().getKey();

        trouser trouser = new trouser(trouserName, id);
        assert id != null;
        reference2.child(id).setValue(trouser);
        // reference.push().setValue(trouser);

    }

         */

    }

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