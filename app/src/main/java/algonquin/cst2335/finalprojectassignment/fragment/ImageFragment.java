package algonquin.cst2335.finalprojectassignment.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import algonquin.cst2335.finalprojectassignment.R;
import algonquin.cst2335.finalprojectassignment.model.Photo;

public class ImageFragment extends Fragment {
    View view;
    ImageView imageView;
    TextView tvHeight, tvWidth, tvUrl;
    Photo photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_image, container, false);
        imageView = view.findViewById(R.id.image_view);
        tvHeight = view.findViewById(R.id.tv_height);
        tvWidth = view.findViewById(R.id.tv_width);
        tvUrl = view.findViewById(R.id.tv_url);

        photo = getArguments().getParcelable("photo");

        Glide.with(getContext()).load(photo.getSrc().getOriginal()).into(imageView);

        tvHeight.setText("Height: "+photo.getHeight());
        tvWidth.setText("Width: "+photo.getWidth());
        tvUrl.setText("Url: "+photo.getSrc().getOriginal());

        tvUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(photo.getSrc().getOriginal()));
                startActivity(intent);
            }
        });
        return view;
    }
}