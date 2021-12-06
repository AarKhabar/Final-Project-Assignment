package algonquin.cst2335.finalprojectassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class OwlBotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owl_main);
        Button btn = findViewById(R.id.button);
        EditText edt = findViewById(R.id.editText);

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String email =  prefs.getString("email", "");
        edt.setText(email);

        btn.setOnClickListener(v -> {

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", edt.getText().toString());
            editor.apply();

            String read = edt.getText().toString();
            if(read.isEmpty()){
                Snackbar.make(findViewById(R.id.snackbarlayout),"Search bar is empty",Snackbar.LENGTH_LONG).show();
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "Searching for " + edt.getText().toString();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }
    @Override
    public void onBackPressed(){
        myAlert(OwlBotActivity.this);
    }

    public void myAlert(Context c){
        new AlertDialog.Builder(c)
            .setTitle("Exit")
            .setMessage("Do you want to leave OwlBot Dictionary?")
                .setPositiveButton("Yes", (dialogInterface, i) -> System.exit(0))
            .setNegativeButton("No", null)
                .show();
    }
}