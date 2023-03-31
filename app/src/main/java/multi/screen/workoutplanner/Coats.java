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

public class Coats extends AppCompatActivity {
    EditText Coats;
    FirebaseDatabase database;
    DatabaseReference reference;
    List<String> userCoats;
    TextView randomItemDisplayCoats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coats);


        Coats = findViewById(R.id.coatsInput);
        randomItemDisplayCoats = findViewById(R.id.randomItemCoats);

        String geCoatsInput = Coats.getText().toString();

        //referencing the database and child node specified
        database = FirebaseDatabase.getInstance("https://workoutplanner-49f96-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Coats");

        //creating arrayList for coats
        userCoats = new ArrayList<>();


        ListView listViewC = findViewById(R.id.listviewC);

        //setting arrayadapter with the userCoats list and custom card view
        ArrayAdapter<String> arrayAdapter = new customCardCoats(this, userCoats);
        listViewC.setAdapter(arrayAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clears the list one there has been a change
                userCoats.clear();
                //then goes through database and adds values back to list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    userCoats.add(snapshot.getValue().toString());

                }
                //updates arrayadapter to update
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    // adds data to the firebase database once user has inputted a trouser
    public void buttonClick(View view) {

        String getCoatsInput = Coats.getText().toString();
        Coats.setText("");

        if (getCoatsInput.isEmpty()) {
            Toast.makeText(Coats.this, "Enter a coat", Toast.LENGTH_SHORT).show();
        } else {
            //adding to the database
            reference.push().setValue(getCoatsInput);
            Toast.makeText(Coats.this, "Coat added", Toast.LENGTH_SHORT).show();
        }

    }
    //generating the random coat and setting to the the random coat display box
    public void generateRandomCoats(View view) {
        Random r = new Random();
        if (!userCoats.isEmpty()) {
            int randomListNumberCoats = r.nextInt(userCoats.size());
            String randomShoe = userCoats.get(randomListNumberCoats);
            randomItemDisplayCoats.setText(randomShoe);


        } else {
            randomItemDisplayCoats.setText("Add a coat to you list");
        }
    }
}
    
