package multi.screen.workoutplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    ////////////// setting the navigation buttons functions


    public void ToShirt(View view) {
        Intent intent = new Intent(this,RandomClothesTest.class);
        startActivity(intent);

    }

    public void ToHoodie(View view) {
        Intent intent = new Intent(this,HoodiesJumpers.class);
        startActivity(intent);
    }

    public void ToShoes(View view) {
        Intent intent = new Intent(this,Shoes.class);
        startActivity(intent);
    }

    public void ToTrouser(View view) {
        Intent intent = new Intent(this,Trousers.class);
        startActivity(intent);
    }

    public void ToCoats(View view) {
        Intent intent = new Intent(this,Coats.class);
        startActivity(intent);
    }


    public void ToOufits(View view) {
        Intent intent = new Intent(this,Outfits.class);
        startActivity(intent);
    }
}