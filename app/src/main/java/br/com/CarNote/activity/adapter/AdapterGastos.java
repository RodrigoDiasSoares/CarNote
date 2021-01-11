package br.com.CarNote.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.CarNote.R;

public class AdapterGastos extends RecyclerView.Adapter<AdapterGastos.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_gastos, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titulo.setText("testando o teste do titulo de teste");
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        ImageView buttonEdit;
        ImageView buttonDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewAdapterItemLista);
            buttonEdit = itemView.findViewById(R.id.imageViewEdit);
            buttonDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
