package multi.screen.workoutplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class Outfits extends AppCompatActivity {
    CheckBox checkBoxCoat;
    CheckBox checkBoxHoodie;
    CheckBox checkBoxShirt;
    CheckBox checkBoxTrousers;
    CheckBox checkBoxShoes;

    TextView randomCoat;
    TextView randomHoodie;
    TextView randomShirt;
    TextView randomTrouser;
    TextView randomShoes;
////////////// commit -


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfits);
        checkBoxCoat = findViewById(R.id.checkBoxCoat);
        checkBoxHoodie = findViewById(R.id.checkBoxHoodie);
        checkBoxShirt = findViewById(R.id.checkBoxShirt);
        checkBoxTrousers = findViewById(R.id.checkBoxTrousers);
        checkBoxShoes = findViewById(R.id.checkBoxShoes);

        randomCoat = findViewById(R.id.displayCoat);
        randomHoodie = findViewById(R.id.displayHoodie);
        randomShirt = findViewById(R.id.displayShirt);
        randomTrouser = findViewById(R.id.displayTrouser);
        randomShoes = findViewById(R.id.displayShoes);


        checkBoxCoat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                randomCoat.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });
    }
}