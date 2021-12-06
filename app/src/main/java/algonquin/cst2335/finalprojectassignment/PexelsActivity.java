package algonquin.cst2335.finalprojectassignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class PexelsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pexels_main);
        EditText et = findViewById(R.id.editTextPersonName);
        Button bt = findViewById(R.id.button2);

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String email =  prefs.getString("pictures", "");
        et.setText(email);

        bt.setOnClickListener(clk ->{
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("pictures", et.getText().toString());
            editor.apply();

            String read = et.getText().toString();
            if(read.isEmpty()){
                Snackbar.make(findViewById(R.id.snackbarlayout),"Search bar is empty",Snackbar.LENGTH_LONG).show();
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "Searching for " + et.getText().toString() + " picture";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
    @Override
    public void onBackPressed(){
        myAlert(PexelsActivity.this);
    }

    public void myAlert(Context c){
        new AlertDialog.Builder(c)
                .setTitle("Exit")
                .setMessage("Do you want to leave Pexels?")
                .setPositiveButton("Yes", (dialogInterface, i) -> System.exit(0))
                .setNegativeButton("No", null)
                .show();
    }
}
