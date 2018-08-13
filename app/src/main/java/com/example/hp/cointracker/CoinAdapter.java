package com.example.hp.cointracker;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> {
    private String imageUrl="https://s2.coinmarketcap.com/static/img/coins/128x128/";
   List<ListItem> listItems;

    private Context context;

    public CoinAdapter(List<ListItem> listItems, Context context) {
        this.listItems=listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CoinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinAdapter.ViewHolder holder, int position) {
        ListItem list=listItems.get(position);
        Picasso.get()
                .load(new String(imageUrl).concat(Integer.toString(Integer.parseInt(list.getId()))).concat(".png")).into(holder.imageView);
      //  holder.idView.setText(list.getId());
        holder.name.setText(list.getName());
        holder.symbol.setText(list.getSymbol());
        holder.price.setText(list.getPrice() );
        holder.volume.setText(list.getVolume24h());
        holder.market.setText(list.getMarketCap());
        holder.oneHour.setText(list.getPercentChange1h());
        holder.twentyFourHour.setText(list.getPercentChange24h());
        holder.sevenDay.setText(list.getPercentChange7d());
        if(list.getPercentChange1h().contains("-")) {
            holder.oneHour.setTextColor(Color.parseColor("#FF0000"));
        }
        else
        {
            holder.oneHour.setTextColor(Color.parseColor("#7FFF00"));
        }
        if(list.getPercentChange24h().contains("-")) {
            holder.twentyFourHour.setTextColor(Color.parseColor("#FF0000"));
        }
        else
        {
            holder.twentyFourHour.setTextColor(Color.parseColor("#7FFF00"));
        }
        if(list.getPercentChange7d().contains("-")) {
            holder.sevenDay.setTextColor(Color.parseColor("#FF0000"));
        }
        else
        {
            holder.sevenDay.setTextColor(Color.parseColor("#7FFF00"));
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
    public void updateCoin(List<ListItem> data) {
        this.listItems=data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    //    TextView idView;
        ImageView imageView;
        TextView name;
        TextView symbol;
        TextView price;
        TextView volume;
        TextView market;
        TextView oneHour;
        TextView twentyFourHour;
        TextView sevenDay;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.coin_name);
            symbol=itemView.findViewById(R.id.coin_symbol);
            price=itemView.findViewById(R.id.price_value);
            volume=itemView.findViewById(R.id.vol_value);
            market=itemView.findViewById(R.id.market_value);
            oneHour=itemView.findViewById(R.id.one_hour_value);
            twentyFourHour=itemView.findViewById(R.id.twenty_four_value);
            sevenDay=itemView.findViewById(R.id.seven_day_value);
        }
    }
}
