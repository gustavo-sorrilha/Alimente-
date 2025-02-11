package services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import model.Bancos;

public class BancosService {

    public List<Bancos> getBancos() throws ClientProtocolException, IOException {

        List<Bancos> bancos = null;

        HttpGet request = new HttpGet("https://brasilapi.com.br/api/banks/v1");

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);

                Gson gson = new Gson();

                bancos = Arrays.asList(gson.fromJson(result, Bancos[].class));
            }
        }

        return bancos;
    }
}
