package com.mac0321.SuperGerenciadorMusical.models.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class GeradorDeJson {

	public JsonArray urisParaJsonArray(String [] array) {
		JsonArray jsonArray;
		String jsonEmString = "[";
		
	    for(int contador = 0; contador < array.length; contador ++) {
	    	if (contador != array.length - 1)
	    		jsonEmString += "{\"uri\":\"" + array[contador] + "\"},";
	    	else
	    		jsonEmString += "{\"uri\":\"" + array[contador] + "\"}";
	    } 
	    jsonEmString += "]";
	    jsonArray = JsonParser.parseString(jsonEmString).getAsJsonArray();
	    return jsonArray;
	}
	
	public JsonArray uriParaJsonArray(String uri) {
		String[] array = new String[1];
		array[0] = uri;
		return this.urisParaJsonArray(array);
	}
}
