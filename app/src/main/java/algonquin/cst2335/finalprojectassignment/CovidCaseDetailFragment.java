package algonquin.cst2335.finalprojectassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class CovidCaseDetailFragment extends BottomSheetDialogFragment {
    CasesHolder holder;

    TextView DateTv;
    TextView TotalCasesTv;
    TextView FatalitiesTv;
    TextView HospitalizationsTv;
    TextView VaccinationsTv;
    TextView RecoveriesTv;

    ImageView FavBtn;

    CovidDatabaseHelper databaseHelper;

    String Sender;

    public CovidCaseDetailFragment(CasesHolder holder,String SENDER) {
        this.holder = holder;
        this.Sender =SENDER;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_covid_case_detail, container, false);

        DateTv = view.findViewById(R.id.DateTv);
        TotalCasesTv = view.findViewById(R.id.TotalCasesTv);
        FatalitiesTv = view.findViewById(R.id.FatalitiesTv);
        HospitalizationsTv = view.findViewById(R.id.HospitalizationsTv);
        VaccinationsTv = view.findViewById(R.id.VaccinationsTv);
        RecoveriesTv = view.findViewById(R.id.RecoveriesTv);

        TextView textView = view.findViewById(R.id.MySavedBtn);

        FavBtn = view.findViewById(R.id.FavBtn);
        databaseHelper = new CovidDatabaseHelper(getActivity());



        if (Sender.equals("COVID19_ACTIVITY")){
            textView.setVisibility(View.VISIBLE);
            FavBtn.setVisibility(View.VISIBLE);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),SavedCases.class);
                    startActivity(intent);
                }
            });

            if(databaseHelper.getRow(holder)){
                FavBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_selected));
            }else {
                FavBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_unselected));
            }
            FavBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(databaseHelper.getRow(holder)){
                        FavBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_unselected));
                        databaseHelper.removeData(holder);
                        Toast.makeText(getActivity(), "Removed", Toast.LENGTH_SHORT).show();

                    }else {
                        FavBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_selected));
                        databaseHelper.addData(holder);
                        Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }else {
            textView.setVisibility(View.GONE);
            FavBtn.setVisibility(View.GONE);
        }






        DateTv.setText(holder.getDate());
        TotalCasesTv.setText(holder.getTotalCases());
        FatalitiesTv.setText(holder.getTotalFatalities());
        HospitalizationsTv.setText(holder.getTotalHospitalizations());
        VaccinationsTv.setText(holder.getTotalVaccinations());
        RecoveriesTv.setText(holder.getTotalRecoveries());



        return view;
    }
}