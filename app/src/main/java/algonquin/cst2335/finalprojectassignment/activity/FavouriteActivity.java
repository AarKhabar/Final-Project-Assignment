package algonquin.cst2335.finalprojectassignment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import algonquin.cst2335.finalprojectassignment.R;
import algonquin.cst2335.finalprojectassignment.adapter.PhotosAdapter;
import algonquin.cst2335.finalprojectassignment.util.DatabaseHandler;

public class FavouriteActivity extends AppCompatActivity {

    RecyclerView rvFav;
    PhotosAdapter photosAdapter;
    DatabaseHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        rvFav = findViewById(R.id.rv_fav);

        handler = new DatabaseHandler(FavouriteActivity.this);
        photosAdapter = new PhotosAdapter(FavouriteActivity.this, handler.getAllFavourites(),true);
        rvFav.setLayoutManager(new LinearLayoutManager(FavouriteActivity.this, LinearLayoutManager.VERTICAL, false));
        rvFav.setAdapter(photosAdapter);
    }
}