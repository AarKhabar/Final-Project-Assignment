package algonquin.cst2335.finalprojectassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btnPexels = findViewById(R.id.imageButton7);
        ImageButton btnCovid = findViewById(R.id.imageButton8);
        ImageButton btnOwl = findViewById(R.id.imageButton9);
        ImageButton btnCo2 = findViewById(R.id.imageButton);
        btnPexels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.rootlayout), "Welcome to Pexels", Snackbar.LENGTH_LONG);

                snackbar.show();
            }
        });
    }

    }


