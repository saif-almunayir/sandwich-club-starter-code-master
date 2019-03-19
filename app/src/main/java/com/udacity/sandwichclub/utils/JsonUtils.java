package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static Sandwich parseSandwichJson(String json) {
          List<String> alsoKnownAsArray=new ArrayList<String>();
          List<String> ingredientsArray=new ArrayList<String>();
        Sandwich sandwich;
        try {
            JSONObject sandwich_details=new JSONObject(json);
            JSONObject Name=sandwich_details.getJSONObject("name");
            String mainName=Name.getString("mainName");
            JSONArray alsoKnownAs=Name.getJSONArray("alsoKnownAs");
            for (int i=0;i<alsoKnownAs.length();i++){
                alsoKnownAsArray.add(alsoKnownAs.getString(i));
            }
            String placeOfOrigin=sandwich_details.getString("placeOfOrigin");
            String description=sandwich_details.getString("description");
            String image=sandwich_details.getString("image");
            JSONArray ingredients=sandwich_details.getJSONArray("ingredients");
            for (int i1=0;i1<ingredients.length();i1++){
                ingredientsArray.add(ingredients.getString(i1));
            }
            sandwich=new Sandwich(mainName,alsoKnownAsArray,placeOfOrigin,description,image,ingredientsArray);
             return  sandwich;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;

    }
}
