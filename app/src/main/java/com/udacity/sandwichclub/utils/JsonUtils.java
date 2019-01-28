package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        if (json == null) {
            return null;
        }

        String Name = "name";
        String MainName = "mainName";
        String AKA = "alsoKnownAs";
        String PlaceOfOrigin = "placeOfOrigin";
        String Description = "description";
        String Image = "image";
        String Ingredients = "ingredients";
        try {
            JSONObject originalObject, nameObject;
            JSONArray alsoKnownAsArray, IngredientsArray;
            String placeOfOriginS, descriptionS, imageS, mainNameS;

            originalObject = new JSONObject(json);
            nameObject = originalObject.getJSONObject(Name);
            mainNameS = nameObject.getString(MainName);
            alsoKnownAsArray = nameObject.getJSONArray(AKA);

            List<String> AKAList = new ArrayList<>();

            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                String AKAarray=alsoKnownAsArray.getString(i);
                AKAList.add(AKAarray);
            }

            IngredientsArray = originalObject.getJSONArray(Ingredients);
            List<String> IngredientList = new ArrayList<>();
            for (int i = 0; i < IngredientsArray.length(); i++) {
                String IngredientA=IngredientsArray.getString(i);
                IngredientList.add(IngredientA);
            }

            placeOfOriginS = originalObject.getString(PlaceOfOrigin);
            descriptionS = originalObject.getString(Description);
            imageS = originalObject.getString(Image);


            Sandwich sandwich = new Sandwich(mainNameS, AKAList, placeOfOriginS, descriptionS, imageS, IngredientList);

            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}