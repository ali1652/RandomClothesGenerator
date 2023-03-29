package multi.screen.workoutplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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

public class Outfits extends AppCompatActivity {
    CheckBox checkBoxCoat;
    CheckBox checkBoxHoodie;
    CheckBox checkBoxShirt;
    CheckBox checkBoxTrousers;
    CheckBox checkBoxShoes;

    TextView coatHeader;
    TextView hoodieHeader;
    TextView shirtHeader;
    TextView trouserHeader;
    TextView shoesHeader;

    EditText randomCoat;
    EditText randomHoodie;
    EditText randomShirt;
    EditText randomTrouser;
    EditText randomShoes;

    Button generateOutfit;
    Button homeButton;

    Button refreshCoat;
    Button refreshHoodie;
    Button refreshShirt;
    Button refreshTrousers;
    Button refreshShoes;
////////////// commit -

    FirebaseDatabase database;
    DatabaseReference referenceCoat;
    DatabaseReference referenceHoodie;
    DatabaseReference referenceShirt;
    DatabaseReference referenceTrouser;
    DatabaseReference referenceShoes;


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

        coatHeader = findViewById(R.id.headingCoat);
        hoodieHeader = findViewById(R.id.headingHoodie);
        shirtHeader = findViewById(R.id.headingShirt);
        trouserHeader = findViewById(R.id.headingTrouser);
        shoesHeader = findViewById(R.id.headingShoes);

        generateOutfit = findViewById(R.id.generateOutfit);

        homeButton = findViewById(R.id.button8);

        refreshCoat = findViewById(R.id.refreshcoats);
        refreshHoodie = findViewById(R.id.refreshHoodie);
        refreshShirt = findViewById(R.id.refreshShirt);
        refreshTrousers = findViewById(R.id.refreshTrouser);
        refreshShoes = findViewById(R.id.refreshShoes);







        checkBoxCoat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                randomCoat.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                coatHeader.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                refreshCoat.setVisibility(isChecked ? View.VISIBLE : View.GONE);


            }
        });

        checkBoxHoodie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                randomHoodie.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                hoodieHeader.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                refreshHoodie.setVisibility(isChecked ? View.VISIBLE : View.GONE);

            }
        });

        checkBoxShirt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                randomShirt.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                shirtHeader.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                refreshShirt.setVisibility(isChecked ? View.VISIBLE : View.GONE);

            }
        });

        checkBoxTrousers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                randomTrouser.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                trouserHeader.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                refreshTrousers.setVisibility(isChecked ? View.VISIBLE : View.GONE);

            }
        });

        checkBoxShoes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                randomShoes.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                shoesHeader.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                refreshShoes.setVisibility(isChecked ? View.VISIBLE : View.GONE);

            }
        });

        database = FirebaseDatabase.getInstance("https://workoutplanner-49f96-default-rtdb.europe-west1.firebasedatabase.app/");
        referenceCoat = database.getReference().child("Coats");
        referenceHoodie = database.getReference().child("HoodiesJumpers");
        referenceShirt = database.getReference().child("Shirts");
        referenceShoes = database.getReference().child("Shoes");
        referenceTrouser = database.getReference().child("Trousers");

        List<String> shirtList = new ArrayList<>();
        List<String> coatList = new ArrayList<>();
        List<String> hoodieList = new ArrayList<>();
        List<String> trouserList = new ArrayList<>();
        List<String> shoeList = new ArrayList<>();

        refreshCoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                referenceCoat.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String coat = snapshot.getValue(String.class);
                            coatList.add(coat);

                            Random r = new Random();
                            if (!coatList.isEmpty()) {
                                int randomListNumber = r.nextInt(coatList.size());
                                String randomCoatt = coatList.get(randomListNumber);
                                randomCoat.setText(randomCoatt);

                            } else {
                                randomCoat.setText("Add a coat to you list");
                            }
                        }
                    }

                    @Override

                    public void onCancelled (DatabaseError databaseError){
                    }
                });

            }
        });

        refreshHoodie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                referenceHoodie.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String hoodie = snapshot.getValue(String.class);
                            hoodieList.add(hoodie);

                            Random r = new Random();
                            if (!hoodieList.isEmpty()) {
                                int randomListNumber = r.nextInt(hoodieList.size());
                                String randomHoodiee = hoodieList.get(randomListNumber);
                                randomHoodie.setText(randomHoodiee);

                            } else {
                                randomHoodie.setText("Add a hoodie/jumper to you list");
                            }
                        }
                    }

                    @Override

                    public void onCancelled (DatabaseError databaseError){
                    }
                });


            }
        });

        refreshShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                referenceShirt.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String shirt = snapshot.getValue(String.class);
                            shirtList.add(shirt);

                            Random r = new Random();
                            if (!shirtList.isEmpty()) {
                                int randomListNumber = r.nextInt(shirtList.size());
                                String randomShirtt = shirtList.get(randomListNumber);
                                randomShirt.setText(randomShirtt);


                            } else {
                                randomShirt.setText("Add a shirt to you list");
                            }
                        }
                    }

                    @Override

                    public void onCancelled (DatabaseError databaseError){
                    }
                });

            }
        });

        refreshTrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                referenceTrouser.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String trouser = snapshot.getValue(String.class);
                            trouserList.add(trouser);

                            Random r = new Random();
                            if (!trouserList.isEmpty()) {
                                int randomListNumber = r.nextInt(trouserList.size());
                                String randomTrouserr = trouserList.get(randomListNumber);
                                randomTrouser.setText(randomTrouserr);

                            } else {
                                randomTrouser.setText("Add a trouser to you list");
                            }
                        }
                    }

                    @Override

                    public void onCancelled (DatabaseError databaseError){
                    }
                });

            }
        });

        refreshShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                referenceShoes.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String shoes = snapshot.getValue(String.class);
                            shoeList.add(shoes);

                            Random r = new Random();
                            if (!shoeList.isEmpty()) {
                                int randomListNumber = r.nextInt(shoeList.size());
                                String randomShoess = shoeList.get(randomListNumber);
                                randomShoes.setText(randomShoess);

                            } else {
                                randomShoes.setText("Add shoes to you list");
                            }
                        }
                    }

                    @Override

                    public void onCancelled (DatabaseError databaseError){
                    }
                });

                referenceTrouser.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String trouser = snapshot.getValue(String.class);
                            trouserList.add(trouser);

                            Random r = new Random();
                            if (!trouserList.isEmpty()) {
                                int randomListNumber = r.nextInt(trouserList.size());
                                String randomTrouserr = trouserList.get(randomListNumber);
                                randomTrouser.setText(randomTrouserr);

                            } else {
                                randomTrouser.setText("Add a trouser to you list");
                            }
                        }
                    }

                    @Override

                    public void onCancelled (DatabaseError databaseError){
                    }
                });

            }
        });




        generateOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        // Add a ValueEventListener to retrieve the data
                referenceShoes.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String shoes = snapshot.getValue(String.class);
                            shoeList.add(shoes);

                            Random r = new Random();
                            if (!shoeList.isEmpty()) {
                                int randomListNumber = r.nextInt(shoeList.size());
                                String randomShoess = shoeList.get(randomListNumber);
                                randomShoes.setText(randomShoess);

                            } else {
                                randomShoes.setText("Add shoes to you list");
                            }
                        }
                    }

                    @Override

                    public void onCancelled (DatabaseError databaseError){
                    }
                });

                referenceTrouser.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String trouser = snapshot.getValue(String.class);
                            trouserList.add(trouser);

                            Random r = new Random();
                            if (!trouserList.isEmpty()) {
                                int randomListNumber = r.nextInt(trouserList.size());
                                String randomTrouserr = trouserList.get(randomListNumber);
                                randomTrouser.setText(randomTrouserr);

                            } else {
                                randomTrouser.setText("Add a trouser to you list");
                            }
                        }
                    }

                    @Override

                    public void onCancelled (DatabaseError databaseError){
                    }
                });


                referenceHoodie.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String hoodie = snapshot.getValue(String.class);
                            hoodieList.add(hoodie);

                            Random r = new Random();
                            if (!hoodieList.isEmpty()) {
                                int randomListNumber = r.nextInt(hoodieList.size());
                                String randomHoodiee = hoodieList.get(randomListNumber);
                                randomHoodie.setText(randomHoodiee);

                            } else {
                                randomHoodie.setText("Add a hoodie/jumper to you list");
                            }
                        }
                    }

                    @Override

                    public void onCancelled (DatabaseError databaseError){
                    }
                });

                referenceCoat.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String coat = snapshot.getValue(String.class);
                            coatList.add(coat);

                            Random r = new Random();
                            if (!coatList.isEmpty()) {
                                int randomListNumber = r.nextInt(coatList.size());
                                String randomCoatt = coatList.get(randomListNumber);
                                randomCoat.setText(randomCoatt);

                            } else {
                                randomCoat.setText("Add a coat to you list");
                            }
                        }
                    }

                    @Override

                     public void onCancelled (DatabaseError databaseError){
                        }
                     });

                referenceShirt.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String shirt = snapshot.getValue(String.class);
                            shirtList.add(shirt);

                            Random r = new Random();
                            if (!shirtList.isEmpty()) {
                                int randomListNumber = r.nextInt(shirtList.size());
                                String randomShirtt = shirtList.get(randomListNumber);
                                randomShirt.setText(randomShirtt);


                            } else {
                                randomShirt.setText("Add a shirt to you list");
                            }
                        }
                    }

                    @Override

                    public void onCancelled (DatabaseError databaseError){
                    }
                });
                /////////
            }
            /////
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Outfits.this,Home.class);
                startActivity(intent);
            }
        });


    }



}