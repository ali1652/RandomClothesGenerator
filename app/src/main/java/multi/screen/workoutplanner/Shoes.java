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

public class Shoes extends AppCompatActivity {
    EditText shoes;
    FirebaseDatabase database;
    DatabaseReference reference;
    List<String> usershoes;
    TextView randomItemDisplayShoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes);


        shoes = findViewById(R.id.shoesInput);
        randomItemDisplayShoes = findViewById(R.id.randomItemShoes);

        String geshoesInput = shoes.getText().toString();

        //referencing the database and child node specified
        database = FirebaseDatabase.getInstance("https://workoutplanner-49f96-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Shoes");

        //creating arrayList for coats
        usershoes = new ArrayList<>();


        ListView listViewT = findViewById(R.id.listviewS);

        //setting arrayadapter with the userCoats list and custom card view
        ArrayAdapter<String> arrayAdapter = new customCardShoes(this, usershoes);
        listViewT.setAdapter(arrayAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clears the list one there has been a change
                usershoes.clear();
                //then goes through database and adds values back to list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    usershoes.add(snapshot.getValue().toString());
                    //String getID = snapshot.getKey();
                }
                //tells array adapter to update
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    // adds data to the firebase database once user has inputted shoes
    public void buttonClick(View view) {

        String getshoesInput = shoes.getText().toString();
        shoes.setText("");
        if (getshoesInput.isEmpty()) {
            Toast.makeText(Shoes.this, "Enter shoes", Toast.LENGTH_SHORT).show();
        } else {
            //adding to the database
            reference.push().setValue(getshoesInput);
            Toast.makeText(Shoes.this, "Shoes added", Toast.LENGTH_SHORT).show();
        }


    }

    //generating the random shoes and setting to the the random shoes display box
    public void generateRandomShoes(View view) {
        Random r = new Random();
        if (!usershoes.isEmpty()) {
            int randomListNumberShoes = r.nextInt(usershoes.size());
            String randomShoe = usershoes.get(randomListNumberShoes);
            randomItemDisplayShoes.setText(randomShoe);


        } else {
            randomItemDisplayShoes.setText("Add a shoe to you list");
        }
    }
}