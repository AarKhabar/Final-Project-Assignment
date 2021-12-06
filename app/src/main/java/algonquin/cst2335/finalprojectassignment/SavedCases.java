package algonquin.cst2335.finalprojectassignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SavedCases extends AppCompatActivity {
    CovidDatabaseHelper covidDatabaseHelper;
    ArrayList<CasesHolder> allData;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_cases);

        covidDatabaseHelper = new CovidDatabaseHelper(this);
        allData = covidDatabaseHelper.getAllData();

        MyDateAdapter adapter = new MyDateAdapter(allData);


        recyclerView = findViewById(R.id.recyclerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private class MyDateAdapter extends RecyclerView.Adapter<MyDateAdapter.MyDateViews>{
        ArrayList<CasesHolder> list;
        public MyDateAdapter(ArrayList<CasesHolder> list){
            this.list = list;
        }

        @Override
        public MyDateViews onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();

            View loadedRow = inflater.inflate(R.layout.covid_dateview, parent, false);
            return new MyDateViews(loadedRow);
        }

        @Override
        public void onBindViewHolder(MyDateViews holder, int position) {
            CasesHolder casesHolder = list.get(position);

            holder.dateText.setText(casesHolder.getDate());
            holder.dateText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CovidCaseDetailFragment covidCaseDetailFragment = new CovidCaseDetailFragment(casesHolder,"SavedCases_Activity");
                    covidCaseDetailFragment.show(getSupportFragmentManager(),covidCaseDetailFragment.getTag());
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private class MyDateViews extends RecyclerView.ViewHolder{
            TextView dateText;

            public MyDateViews(View itemView) {
                super(itemView);
                dateText = itemView.findViewById(R.id.COVdate); }


        }
    }
}