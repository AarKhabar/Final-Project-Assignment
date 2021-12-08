package algonquin.cst2335.finalprojectassignment.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import algonquin.cst2335.finalprojectassignment.R;
import algonquin.cst2335.finalprojectassignment.adapter.PreviousAdapter;
import algonquin.cst2335.finalprojectassignment.util.PreferenceManager;

public class HomeFragment extends Fragment {

    View view;
    EditText etText;
    Button btnSearch;
    RecyclerView rvPrevious;
    PreviousAdapter previousAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().setTitle("Search");

        etText = view.findViewById(R.id.et_text);
        btnSearch = view.findViewById(R.id.btn_search);
        rvPrevious = view.findViewById(R.id.rv_previous);

        previousAdapter = new PreviousAdapter(getContext());
        rvPrevious.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvPrevious.setAdapter(previousAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etText.getText().toString().trim();
                if (TextUtils.isEmpty(text)) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter text.", Snackbar.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getContext(), "Search on "+text, Toast.LENGTH_SHORT).show();
                    etText.setText("");
                    PreferenceManager.getInstance().savePreviousText(getContext(), text);
                    Fragment fragment = new PhotosFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("text", text);
                    fragment.setArguments(bundle);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    fragmentTransaction.addToBackStack(fragment.getClass().getName());
                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (previousAdapter != null)
            previousAdapter.setStringList();
    }
}
