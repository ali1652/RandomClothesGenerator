package multi.screen.workoutplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomClothesTest extends AppCompatActivity {
    EditText shirts;
    FirebaseDatabase database;
    DatabaseReference reference;
    List<String> userShirts;
    TextView randomItemDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_clothes_test);

        shirts = findViewById(R.id.shirtsInput);
        randomItemDisplay = findViewById(R.id.randomItemShirt);



        String getShirtsInput = shirts.getText().toString();

        //referencing the database and child node specified
        database = FirebaseDatabase.getInstance("https://workoutplanner-49f96-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Shirts");

        //creating arrayList for shirts
        userShirts = new ArrayList<>();



        ListView listView = findViewById(R.id.listview);

        //setting arrayadapter with the userShirts list and custom card view
        ArrayAdapter<String> arrayAdapter = new customCard(this, userShirts);
        listView.setAdapter(arrayAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clears the list one there has been a change
                userShirts.clear();
                //then goes through database and adds values back to list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    userShirts.add(snapshot.getValue().toString());
                }
                //tells array adapter to update
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    // adds data to the firebase database once user has inputted a shirt
    public void buttonClick(View view) {
        String getShirtsInput = shirts.getText().toString();
        shirts.setText("");
        if (getShirtsInput.isEmpty()) {
            Toast.makeText(RandomClothesTest.this, "Enter a shirt", Toast.LENGTH_SHORT).show();
        } else {
            //adding to the database
            reference.push().setValue(getShirtsInput);
            Toast.makeText(RandomClothesTest.this, "Shirt added", Toast.LENGTH_SHORT).show();
        }

    }

    //generating the random shirt and setting to the the random shirt display box
    public void generateRandomShirt(View view) {
        Random r = new Random();
        if (!userShirts.isEmpty()) {
            int randomListNumber = r.nextInt(userShirts.size());
            String randomShirt = userShirts.get(randomListNumber);
            randomItemDisplay.setText(randomShirt);
        } else {
            randomItemDisplay.setText("Add a shirt to you list");
        }

    }

    //home button function
    public void toHomePage(View view) {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
}








