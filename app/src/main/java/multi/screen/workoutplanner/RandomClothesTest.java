package multi.screen.workoutplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.List;

public class RandomClothesTest extends AppCompatActivity {
    EditText shirts;
    FirebaseDatabase database;

    List<String> userShirts;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_clothes_test);

         shirts = findViewById(R.id.shirtsInput);

         String getShirtsInput = shirts.getText().toString();

        String Tshirts[]
                = { "Nike Blue", "Primark red",
                "Adidas white"};

        ListView listView = findViewById(R.id.listview);
        //sets the view of list
        // ArrayAdapter<String> arrayList = getList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Tshirts);
        //l.setChoiceMode(textview.Choice);
        listView.setAdapter(arrayAdapter);
        //listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        userShirts = new ArrayList<>();
        database = FirebaseDatabase.getInstance("https://workoutplanner-49f96-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Shirts");


    }

    // adds data to the firebase database once user has inputted a shirt
    public void buttonClick(View view) {
        String getShirtsInput = shirts.getText().toString();
        reference.push().setValue(getShirtsInput)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RandomClothesTest.this, "Shirt added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RandomClothesTest.this, "Failed to add shirt to database", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}


