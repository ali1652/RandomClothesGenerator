package multi.screen.workoutplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class customCard extends ArrayAdapter<String> {

    DatabaseReference reference;
    FirebaseDatabase database;


    public customCard(Context context, List<String> testList) {
        super(context,0,testList);
        database = FirebaseDatabase.getInstance("https://workoutplanner-49f96-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Shirts");
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        String string = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card,parent,false);

            Button deleteButton = convertView.findViewById(R.id.deleteButtonTS);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    database = FirebaseDatabase.getInstance("https://workoutplanner-49f96-default-rtdb.europe-west1.firebasedatabase.app/");
                    reference = database.getReference().child("Shirts");
                    // delte from firebase
                    String itemId = getItem(position);

                    ////////???????????
                    reference.child(itemId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Removes from your list and updates adapter
                                remove(getItem(position));
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(getContext(), "Could not remove items", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }

        //setting data in firebase to card items
        TextView textView = convertView.findViewById(R.id.shirtItem);
        textView.setText(string);
        return convertView;
    }
}
