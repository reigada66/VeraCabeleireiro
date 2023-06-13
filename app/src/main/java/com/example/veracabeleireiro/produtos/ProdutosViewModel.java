package com.example.veracabeleireiro.produtos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProdutosViewModel extends ViewModel {

    private MutableLiveData<List<Produto>> produtosLiveData;

    public LiveData<List<Produto>> getProdutosLiveData() {
        if (produtosLiveData == null) {
            produtosLiveData = new MutableLiveData<>();
            loadData();
        }
        return produtosLiveData;
    }

    public void loadData() {
        new FetchDataAsyncTask().execute();
    }

    private class FetchDataAsyncTask extends AsyncTask<Void, Void, List<Produto>> {

        @Override
        protected List<Produto> doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            List<Produto> produtos = new ArrayList<>();

            try {
                URL url = new URL("http://leonardomcp640.pythonanywhere.com/produtos/api/");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) {
                    return produtos;
                }

                StringBuilder buffer = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    return produtos;
                }

                String jsonData = buffer.toString();
                produtos = parseJsonData(jsonData);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return produtos;
        }

        @Override
        protected void onPostExecute(List<Produto> produtos) {
            produtosLiveData.postValue(produtos);
        }

        private List<Produto> parseJsonData(String jsonData) {
            List<Produto> produtos = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String nome = jsonObject.optString("Nome");
                    double precoDouble = jsonObject.optDouble("preco");
                    String imagemUrl = jsonObject.optString("imagem");

                    BigDecimal preco = BigDecimal.valueOf(precoDouble);
                    Produto produto = new Produto(nome, preco, imagemUrl);
                    produtos.add(produto);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return produtos;
        }
    }
}
