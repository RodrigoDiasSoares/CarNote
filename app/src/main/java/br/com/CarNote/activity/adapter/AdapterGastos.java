package br.com.CarNote.activity.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.CarNote.R;
import br.com.CarNote.activity.model.Gastos;

public class AdapterGastos extends RecyclerView.Adapter<AdapterGastos.MyViewHolder> {
    private List<Gastos> gastosList;

    public AdapterGastos(List<Gastos> list) {
        this.gastosList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_gastos, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Gastos gastos = gastosList.get(position);
        holder.itenList.setText(gastos.getTitulo());
        holder.preco.setText(gastos.getPreco());
        holder.data.setText(gastos.getData());
        Log.i("tarefaAdapter", gastos.getTitulo());
    }

    @Override
    public int getItemCount() {
        return this.gastosList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView itenList;
        TextView preco;
        TextView data;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itenList = itemView.findViewById(R.id.textViewAdapterItemLista);
            preco = itemView.findViewById(R.id.textViewPrecoAdapter);
            data = itemView.findViewById(R.id.textViewData);
        }
    }
}
