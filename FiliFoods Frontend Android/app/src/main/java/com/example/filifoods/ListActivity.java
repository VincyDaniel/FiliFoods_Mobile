package com.example.filifoods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    RecyclerView recView;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            JSONArray arr = (JSONArray) msg.obj;
            List<Recipe> data = convertJSONArrayToList(arr);
            RecipeAdapter adp = new RecipeAdapter(ListActivity.this, data);
            recView.setAdapter(adp);
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random);

        recView = findViewById(R.id.recyclerViewList);
        recView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String searchType = intent.getStringExtra("searchType");
        String searchValue = intent.getStringExtra("searchValue");

        RecipeRepo repo = new RecipeRepo();

        if (searchType != null && searchValue != null) {
            if (searchType.equals("name")) {
                Log.w("Dev", searchValue);
                repo.getDataByName(((FiliFoodsApp) getApplication()).srv, handler, searchValue);
            } else if (searchType.equals("diet")) {
                repo.getDataByDiet(((FiliFoodsApp) getApplication()).srv, handler, searchValue);
            } else if (searchType.equals("cuisine")) {
                repo.getDataByCuisine(((FiliFoodsApp) getApplication()).srv, handler, searchValue);
            } else if (searchType.equals("tags")) {
                repo.getDataByTags(((FiliFoodsApp) getApplication()).srv, handler, searchValue);
            }
        } else {
            Intent i = new Intent(ListActivity.this, AllActivity.class);
            startActivity(i);
        }
    }

    public List<Recipe> convertJSONArrayToList(JSONArray jsonArray) {
        List<Recipe> recipeList = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject current = jsonArray.getJSONObject(i);

                String name = current.getString("name");
                String diet = current.getString("diet");
                String cuisine = current.getString("cuisine");
                String introduction = current.getString("introduction");
                String imageUrl = current.getString("imageUrl");

                // Process the tags JSON array
                JSONArray tagsJsonArray = current.getJSONArray("tags");
                String[] tags = new String[tagsJsonArray.length()];
                for (int j = 0; j < tagsJsonArray.length(); j++) {
                    tags[j] = tagsJsonArray.getString(j);
                }

                JSONArray ingredientsJsonArray = current.getJSONArray("ingredients");
                String[] ingredients = new String[ingredientsJsonArray.length()];
                for (int j = 0; j < ingredientsJsonArray.length(); j++) {
                    ingredients[j] = ingredientsJsonArray.getString(j);
                }

                // Parse additional fields
                String servingSize = current.getString("servingSize");
                String calories = current.getString("calories");
                String totalFat = current.getString("totalFat");
                String saturatedFat = current.getString("saturatedFat");
                String cholesterol = current.getString("cholesterol");
                String sodium = current.getString("sodium");
                String totalCarbo = current.getString("totalCarbo");
                String fiber = current.getString("fiber");
                String sugar = current.getString("sugar");
                String protein = current.getString("protein");

                Recipe recipe = new Recipe(name, diet, cuisine, introduction, imageUrl, tags, ingredients,
                        servingSize, calories, totalFat, saturatedFat, cholesterol,
                        sodium, totalCarbo, fiber, sugar, protein);
                recipeList.add(recipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipeList;
    }
}
