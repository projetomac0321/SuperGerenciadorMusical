package com.mac0321.SuperGerenciadorMusical.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class GeradorDeJson {

	public JsonArray uriParaJsonArray(String [] uris) {
		JsonArray jsonArray;
		String jsonEmString = "[";
		
	    for(int contador = 0; contador < uris.length; contador ++) {
	    	if (contador != uris.length - 1)
	    		jsonEmString += "{\"uri\":\"" + uris[contador] + "\"},";
	    	else
	    		jsonEmString += "{\"uri\":\"" + uris[contador] + "\"}";
	    } 
	    jsonEmString += "]";
	    jsonArray = JsonParser.parseString(jsonEmString).getAsJsonArray();
	    return jsonArray;
	}
}
