package multi.screen.workoutplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class RandomClothesTest extends AppCompatActivity {
    EditText shirts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_clothes_test);

         shirts = findViewById(R.id.shirtsInput);

         String getShirtsInput = shirtsInput.getText.toString;


    }
}