package com.example.veracabeleireiro.produtos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veracabeleireiro.R;

import java.util.List;

public class ProdutosFragment extends Fragment {

    private ProdutosViewModel produtosViewModel;
    private ProdutosAdapter produtosAdapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        produtosViewModel = new ViewModelProvider(this).get(ProdutosViewModel.class);
        produtosViewModel.getProdutosLiveData().observe(this, new Observer<List<Produto>>() {
            @Override
            public void onChanged(List<Produto> produtos) {
                produtosAdapter.setProdutos(produtos);
            }
        });

        produtosAdapter = new ProdutosAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_produtos, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(produtosAdapter);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    private void loadData() {
        // Call the loadData() method in the ViewModel
        produtosViewModel.loadData();
    }
}
