package algonquin.cst2335.finalprojectassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OwlBotDictionary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owl_main);
        Button btn = findViewById(R.id.button);
        EditText edt = findViewById(R.id.editText);

        btn.setOnClickListener( clk -> {
            Context context = getApplicationContext();
            CharSequence text = "Searching for " + edt.getText().toString();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });
    }
}