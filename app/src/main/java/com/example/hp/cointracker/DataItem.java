package com.example.hp.cointracker;

import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("symbol")
	private String symbol;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("website_slug")
	private String websiteSlug;

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}

	public String getSymbol(){
		return symbol;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setWebsiteSlug(String websiteSlug){
		this.websiteSlug = websiteSlug;
	}

	public String getWebsiteSlug(){
		return websiteSlug;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"symbol = '" + symbol + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",website_slug = '" + websiteSlug + '\'' + 
			"}";
		}
}