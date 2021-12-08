package algonquin.cst2335.finalprojectassignment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import algonquin.cst2335.finalprojectassignment.activity.PexelActivity;

public class CovidActivity extends AppCompatActivity {

    EditText dateInput;
    Button searchBtn;
    RecyclerView dateList;
    String dateSearched;
    SharedPreferences prefs;
    String prevDate;
    ArrayList<CasesHolder> datesArray = new ArrayList<>();
    MyDateAdapter adt;
    String WebURL;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_FinalProjectAssignment2);
        setContentView(R.layout.nav_covid_activity);



        dateInput = findViewById(R.id.COVeditText);
        searchBtn = findViewById(R.id.COVbutton);
        dateList = findViewById(R.id.COVmyrecycler);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        Toast.makeText(CovidActivity.this, "Hello", Toast.LENGTH_SHORT).show();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.popout_menu);
        navigationView.setNavigationItemSelectedListener((item) -> {
            switch (item.getItemId()) {

                case R.id.help:
                    new AlertDialog.Builder((CovidActivity.this)).setMessage(getString(R.string.HelpMessage))
                            .setTitle(getString(R.string.HelpTitle))
                            .setNeutralButton(getString(R.string.close), (dialog, cl) -> dialog.cancel())
                            .create().show();
                    drawer.closeDrawer(GravityCompat.START);
                    break;

                        case R.id.owlbot:
                            Intent nextPage = new Intent(CovidActivity.this, OwlBotActivity.class);
                            startActivity(nextPage);
                            break;

                        case R.id.pexles:
                            Intent nextPage1 = new Intent(CovidActivity.this, PexelActivity.class);
                            startActivity(nextPage1);
                            break;

                        case R.id.C02image:
                            Intent nextPage2 = new Intent(CovidActivity.this, CarbonActivity.class);
                            startActivity(nextPage2);
                            break;
                    }

            return false;
        });


        //SHARED PREFERENCES
        getPreviousDate();

        //RECYCLER VIEW
        adt = new MyDateAdapter();
        dateList.setAdapter(adt);
        dateList.setLayoutManager(new LinearLayoutManager(this));

        //SEARCH BUTTON
        searchBtn.setOnClickListener((click) -> {
            dateSearched = dateInput.getText().toString();

            //SHARED PREFERENCES
            storePreviousDate();

            if (checkNotValidDate(dateSearched)) {
                Snackbar.make(searchBtn, "The date you have entered is invalid: " + dateSearched, Snackbar.LENGTH_LONG)
                        .show();

            } else {
                datesArray.clear();

                //RECYCLER VIEW
                getData();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder((CovidActivity.this));
        switch (item.getItemId()) {

            case R.id.help:
                builder.setMessage(getString(R.string.HelpMessage))
                        .setTitle(getString(R.string.HelpTitle))
                        .setNeutralButton(getString(R.string.close), (dialog, cl) -> dialog.cancel())
                        .create().show();
                break;
            case R.id.owlbot:
                Intent nextPage = new Intent(CovidActivity.this, OwlBotActivity.class);
                startActivity(nextPage);
                break;

            case R.id.pexles:
                Intent nextPage1 = new Intent(CovidActivity.this, PexelActivity.class);
                startActivity(nextPage1);
                break;

            case R.id.C02image:
                Intent nextPage2 = new Intent(CovidActivity.this, CarbonActivity.class);
                startActivity(nextPage2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    boolean checkNotValidDate(String date) {
        boolean isNotValid = false;
        // Check if date is 'null'
        if (!date.equals(""))
            isNotValid = !(Pattern.matches("[0-9]{4}-(((0)[0-9])|((1)[0-2]))-([0-2][0-9]|(3)[0-1])", date));
        return isNotValid;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void getData(){
        Executor newThread = Executors.newSingleThreadExecutor();

        newThread.execute(() -> {
            try {
                WebURL = "https://api.covid19tracker.ca/reports?after="
                        + URLEncoder.encode(dateSearched, "UTF-8");

                URL url = new URL(WebURL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                String text = (new BufferedReader(
                        new InputStreamReader(in, StandardCharsets.UTF_8))).lines().collect(Collectors.joining("\n"));

                //converts string to json obj
                JSONObject theDocument = new JSONObject( text );
                JSONArray data = theDocument.getJSONArray ( "data" );

                for(int i=0;i<data.length();i++) {
                    JSONObject currentItem = data.getJSONObject(i);
                    String dateString = currentItem.getString("date");
                    String total_cases = currentItem.getString("total_cases");
                    String total_fatalities = currentItem.getString("total_fatalities");
                    String total_hospitalizations = currentItem.getString("total_hospitalizations");
                    String total_vaccinations = currentItem.getString("total_vaccinations");
                    String total_recoveries = currentItem.getString("total_recoveries");

                    CasesHolder Case = new CasesHolder(dateString,total_cases,total_fatalities,total_hospitalizations,total_vaccinations,total_recoveries);
                    datesArray.add(Case);
                }

                runOnUiThread( (  )  -> {
                    adt.notifyDataSetChanged();
                    if(datesArray.isEmpty()){
                        Toast.makeText(getApplicationContext(), "No results found for " + dateSearched, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Displaying results for " + dateSearched, Toast.LENGTH_LONG).show();
                    }
                });

            } catch (IOException | JSONException e) {
                Log.e("Connection error:", e.getMessage());
            }
        });
    }

    /**
     * Gets previous data
     */
    void getPreviousDate(){
        prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        prevDate = prefs.getString("COVIDDate", ""); //"" is the default value
        dateInput.setText(prevDate); //set the date to the previous input
    }

    /**
     * Stores previous date
     */
    void storePreviousDate(){
        //save string to MyData file under variable name COVIDDate
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("COVIDDate", dateInput.getText().toString());
        editor.apply();
    }


    /** RecyclerView
     *
     */

    private class MyDateAdapter extends RecyclerView.Adapter<MyDateAdapter.MyDateViews>{
        @Override
        public MyDateViews onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();

            View loadedRow = inflater.inflate(R.layout.covid_dateview, parent, false);
            return new MyDateViews(loadedRow);
        }

        @Override
        public void onBindViewHolder(MyDateViews holder, int position) {
            CasesHolder casesHolder = datesArray.get(position);

            holder.dateText.setText(casesHolder.getDate());
            holder.dateText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CovidCaseDetailFragment covidCaseDetailFragment = new CovidCaseDetailFragment(casesHolder,"COVID19_ACTIVITY");
                    covidCaseDetailFragment.show(getSupportFragmentManager(),covidCaseDetailFragment.getTag());
                }
            });

        }

        @Override
        public int getItemCount() {
            return datesArray.size();
        }
        private class MyDateViews extends RecyclerView.ViewHolder{
            TextView dateText;

            public MyDateViews(View itemView) {
                super(itemView);
                dateText = itemView.findViewById(R.id.COVdate);
            }

        }
    }

}
