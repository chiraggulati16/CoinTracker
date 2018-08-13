package com.example.hp.cointracker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CoinAdapter adapter;
    private List<ListItem> items;
    private List<Pojo> pojo;
    private DataItem id;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        items=new ArrayList<>();
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

        /*Call<Pojo> pojoCall=apiInterface.getList();
        pojoCall.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                Pojo pojo1= response.body();

                      for (int i = 0; i < pojo1.getData().size(); i++) {
                          String id1 = String.valueOf(pojo1.getData().get(i).getId());
                          String name1 = pojo1.getData().get(i).getName();
                          String symbol1 = pojo1.getData().get(i).getSymbol();


                          ListItem listItem = new ListItem(id1, name1, symbol1);
                          items.add(listItem);
                      }
                      adapter = new CoinAdapter(items, MainActivity.this);
                      recyclerView.setAdapter(adapter);

                  }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t){

        }
        });*/

        Call<Coin> call=apiInterface.getCoins();
        call.enqueue(new Callback<Coin>() {
            @Override
            public void onResponse(Call<Coin> call, Response<Coin> response) {
                Coin coin=response.body();
                for(int i=0;i< coin.getData().size();i++)
                {
                    String id=String.valueOf(coin.getData().get(i).getId());
                    String name=coin.getData().get(i).getName();
                    String symbol=coin.getData().get(i).getSymbol();
                    DecimalFormat decimal=new DecimalFormat("0.00");
                    Double pricevalue=coin.getData().get(i).getQuotes().getUSD().getPrice();
                    String price= decimal.format(pricevalue);
                    Double marketvalue=coin.getData().get(i).getQuotes().getUSD().getMarketCap();
                    String market=decimal.format(marketvalue);
                    Double volumeValue=coin.getData().get(i).getQuotes().getUSD().getVolume24h();
                    String volume=decimal.format(volumeValue);
                    Double oneHourValue=coin.getData().get(i).getQuotes().getUSD().getPercentChange1h();
                    String oneHour=decimal.format(oneHourValue).concat("%");
                    Double twentyHourValue=coin.getData().get(i).getQuotes().getUSD().getPercentChange24h();
                    String twentyHour=decimal.format(twentyHourValue).concat("%");
                    Double sevenDayValue=coin.getData().get(i).getQuotes().getUSD().getPercentChange7d();
                    String sevenDay=decimal.format(sevenDayValue).concat("%");
                    if(oneHourValue<0) {

                    }
                    else {
                        oneHour="+"+oneHour;

                    }
                    if(twentyHourValue<0) {
                    }
                    else {
                        twentyHour="+"+twentyHour;

                    }
                    if(sevenDayValue<0) {
                    }
                    else {
                        sevenDay="+"+sevenDay;

                    }
                    ListItem listItem1 = new ListItem(id,name, symbol, price,market,volume, oneHour, twentyHour, sevenDay);
                    items.add(listItem1);

                }
                adapter=new CoinAdapter(items,MainActivity.this);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<Coin> call, Throwable t) {
                Log.e("tag=",""+t.getMessage());

            }
        });
   }
}
