package algonquin.cst2335.finalprojectassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import algonquin.cst2335.finalprojectassignment.R;
import algonquin.cst2335.finalprojectassignment.util.PreferenceManager;

public class PreviousAdapter extends RecyclerView.Adapter<PreviousAdapter.PreviousHolder> {
    Context context;
    List<String> stringList;

    public PreviousAdapter(Context context) {
        this.context = context;
        setStringList();
    }

    public void setStringList(){
        this.stringList = PreferenceManager.getInstance().getPreviousList(context);
    }

    @NonNull
    @Override
    public PreviousHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_previous, parent, false);
        return new PreviousHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousHolder holder, int position) {
        holder.tvText.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class PreviousHolder extends RecyclerView.ViewHolder {
        TextView tvText;

        public PreviousHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_text);
        }
    }
}
