package com.wwsystems.bomcidadao.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wwsystems.bomcidadao.R;
import com.wwsystems.bomcidadao.model.ItemTelefone;

import java.util.List;

public class TelefoneAdapter  extends RecyclerView.Adapter<TelefoneAdapter.ViewHolder> {

    private List<ItemTelefone> listaTelefone;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNomeTelefone, tvDescricaoContato;
        public LinearLayout linearContatos;

        public ViewHolder(View itemView) {
            super(itemView);
            linearContatos = (LinearLayout) itemView.findViewById(R.id.linearContatos);
            tvNomeTelefone = (TextView) itemView.findViewById(R.id.tvNomeContato);
            tvDescricaoContato = (TextView) itemView.findViewById(R.id.tvDescricaoContato);
        }
    }


    public TelefoneAdapter(List<ItemTelefone> listaContatos, Context context) {
        this.listaTelefone = listaContatos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_telefone, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int posicao) {
        final ItemTelefone contato = listaTelefone.get(posicao);
        if(contato != null){
            holder.tvDescricaoContato.setText(contato.getTelefoneContato()+ " "+ contato.getNomeCidade());
            holder.tvNomeTelefone.setText(contato.getNomeTelefone());
        }
        holder.linearContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:"+contato.getTelefoneContato());
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaTelefone.size();
    }






}
