package multi.screen.workoutplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        String tutorials[]
                = { "Algorithms", "Data Structures",
                "Languages", "Interview Corner",
                "GATE", "ISRO CS",
                "UGC NET CS", "CS Subjects",
                "Web Technologies" };


        ListView listView = findViewById(R.id.list_view);
        //sets the view of list
        // ArrayAdapter<String> arrayList = getList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,tutorials);
        //l.setChoiceMode(textview.Choice);
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String items = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(MainScreen.this, items, Toast.LENGTH_SHORT).show();
            }
        });

         */


        SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            int position = checked.keyAt(i);
            if (checked.valueAt(i)) {
                String item = tutorials[position];
                selectedItems.add(item);
            }
        }












    }
}