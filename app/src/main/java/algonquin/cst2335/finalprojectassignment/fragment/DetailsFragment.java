package algonquin.cst2335.finalprojectassignment.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import algonquin.cst2335.finalprojectassignment.R;
import algonquin.cst2335.finalprojectassignment.model.Photo;
import algonquin.cst2335.finalprojectassignment.util.DatabaseHandler;

public class DetailsFragment extends Fragment {

    View view;
    ImageView imageView, ivFav;
    TextView tvHeight, tvWidth, tvName;
    Photo photo;
    DatabaseHandler handler;
    boolean isFavourite = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_details, container, false);

        handler = new DatabaseHandler(getContext());

        ivFav = view.findViewById(R.id.iv_fav);
        imageView = view.findViewById(R.id.image_view);
        tvHeight = view.findViewById(R.id.tv_height);
        tvWidth = view.findViewById(R.id.tv_width);
        tvName = view.findViewById(R.id.tv_name);

        photo = getArguments().getParcelable("photo");

        if (handler.getFavourite(photo.getId()) == null) {
            isFavourite = false;
            ivFav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        } else {
            isFavourite = true;
            ivFav.setImageResource(R.drawable.ic_baseline_favorite_24);
        }

        Log.d("theS", "onCreateView: " + photo.getSrc().getOriginal());
        Glide.with(getContext()).load(photo.getSrc().getLarge()).into(imageView);

        tvHeight.setText("Height: " + photo.getHeight());
        tvWidth.setText("Width: " + photo.getWidth());
        tvName.setText("Name: " + photo.getPhotographer());

        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavourite) {
                    isFavourite = false;
                    handler.removeFromFavourite(photo.getId());
                    ivFav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                } else {
                    isFavourite = true;
                    handler.addToFavourite(photo);
                    ivFav.setImageResource(R.drawable.ic_baseline_favorite_24);
                }
            }
        });

        return view;
    }
}
