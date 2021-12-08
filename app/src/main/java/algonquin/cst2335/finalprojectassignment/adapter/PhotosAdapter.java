package algonquin.cst2335.finalprojectassignment.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import algonquin.cst2335.finalprojectassignment.R;
import algonquin.cst2335.finalprojectassignment.fragment.DetailsFragment;
import algonquin.cst2335.finalprojectassignment.fragment.ImageFragment;
import algonquin.cst2335.finalprojectassignment.model.Photo;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosHolder> {
    Context context;
    List<Photo> photoList;

    boolean isFav;
    public PhotosAdapter(Context context, List<Photo> photoList,boolean isFav) {
        this.context = context;
        this.photoList = photoList;
        this.isFav = isFav;
    }

    @NonNull
    @Override
    public PhotosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ietm_photo, parent, false);
        return new PhotosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosHolder holder,  int position) {
        Glide.with(context).load(photoList.get(position).getSrc().getSmall()).into(holder.ivThumbnail);
        holder.tvName.setText(photoList.get(position).getPhotographer());
        holder.ivThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFav){
                    Fragment fragment = new ImageFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("photo", photoList.get(position));
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    fragmentTransaction.addToBackStack(fragment.getClass().getName());
                    fragmentTransaction.commit();
                }
                }

        });

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  if (!isFav){
                Fragment fragment = new DetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("photo", photoList.get(position));
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment);
                fragmentTransaction.addToBackStack(fragment.getClass().getName());
                fragmentTransaction.commit();
            }

            }
        });

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class PhotosHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        TextView tvName;

        public PhotosHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
