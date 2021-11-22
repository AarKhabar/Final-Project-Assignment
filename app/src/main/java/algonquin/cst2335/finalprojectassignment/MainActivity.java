package algonquin.cst2335.finalprojectassignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btnPexels = findViewById(R.id.imageButton7);
        ImageButton btnCovid = findViewById(R.id.imageButton8);
        ImageButton btnOwl = findViewById(R.id.imageButton9);
        ImageButton btnCo2 = findViewById(R.id.imageButton);

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.rootlayout), "Welcome to Final Project", Snackbar.LENGTH_LONG);

        snackbar.show();

        btnPexels.setOnClickListener(click -> {
            Context context = getApplicationContext();
            CharSequence text = "Welcome to Pexels ";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });

        btnOwl.setOnClickListener( (click) ->{
            Context context = getApplicationContext();
            CharSequence text = "Welcome to OwlBot Dictionary";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
                startActivity( new Intent(MainActivity.this, OwlBotActivity.class));
        });

        btnCovid.setOnClickListener(click ->{
            Context context = getApplicationContext();
            CharSequence text = "Welcome to Covid-19 Information Tracker";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });

        btnCo2.setOnClickListener(click->{
            Context context = getApplicationContext();
            CharSequence text = "Welcome to Carbone Dioxide Interface";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });
    }
    @Override
    public void onBackPressed(){
        myAlert(MainActivity.this);
    }

    public void myAlert(Context c){
        new AlertDialog.Builder(c)
                .setTitle("Exit")
                .setMessage("Do you want to leave Final Project?")
                .setPositiveButton("Yes", (dialogInterface, i) -> System.exit(0))
                .setNegativeButton("No", null)
                .show();
    }

    }


