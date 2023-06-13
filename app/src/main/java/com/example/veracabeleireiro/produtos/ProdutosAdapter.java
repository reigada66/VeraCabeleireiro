package com.example.veracabeleireiro.produtos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.veracabeleireiro.R;

import java.util.ArrayList;
import java.util.List;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ProdutoViewHolder> {

    private List<Produto> produtos;

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.bind(produto);
    }

    @Override
    public int getItemCount() {
        return produtos != null ? produtos.size() : 0;
    }

    static class ProdutoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textNome;
        private TextView textPreco;

        ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textNome = itemView.findViewById(R.id.textNome);
            textPreco = itemView.findViewById(R.id.textPreco);
        }

        void bind(Produto produto) {
            textNome.setText(produto.getNome());
            textPreco.setText(String.valueOf(produto.getPreco()));

            // Load image using Glide or any other image loading library
            Glide.with(itemView.getContext())
                    .load(produto.getImagemUrl())
                    .placeholder(R.drawable.placeholder_image)
                    .into(imageView);
        }
    }
}
