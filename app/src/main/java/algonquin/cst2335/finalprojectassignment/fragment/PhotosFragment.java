package algonquin.cst2335.finalprojectassignment.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import algonquin.cst2335.finalprojectassignment.R;
import algonquin.cst2335.finalprojectassignment.adapter.PhotosAdapter;
import algonquin.cst2335.finalprojectassignment.model.PexelsResponse;
import algonquin.cst2335.finalprojectassignment.util.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosFragment extends Fragment {
    View view;
    String text;
    RecyclerView rvPhotos;
    PhotosAdapter photosAdapter;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_photos, container, false);

        text = getArguments().getString("text");

        rvPhotos = view.findViewById(R.id.rv_photos);

        progressDialog = ProgressDialog.show(getContext(),"","Searching...",false,false);
        Call<PexelsResponse> responseCall = RetrofitClient.getInstance().getMyRetrofitApi().getData(text);
        responseCall.enqueue(new Callback<PexelsResponse>() {
            @Override
            public void onResponse(Call<PexelsResponse> call, Response<PexelsResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(getContext(), "Photos fetched.", Toast.LENGTH_SHORT).show();
                        photosAdapter = new PhotosAdapter(getContext(), response.body().getPhotos(),false);
                        rvPhotos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        rvPhotos.setAdapter(photosAdapter);
                    }else {
                        Snackbar.make(getActivity().findViewById(android.R.id.content), "Response is null.", Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(getActivity().findViewById(android.R.id.content), "Response is unSuccessful. "+response.code(), Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PexelsResponse> call, Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(getActivity().findViewById(android.R.id.content), t.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
