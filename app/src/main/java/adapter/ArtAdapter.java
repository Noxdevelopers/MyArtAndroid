package adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import objects.ProductsObject;

public class ArtAdapter extends RecyclerView.Adapter<ArtAdapter.ArtViewHolder> {


    Activity activity;
    List<ProductsObject> list;
    public ArtAdapter (Activity activity, List<ProductsObject> list){
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ArtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ArtAdapter.ArtViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ArtViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ArtViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
