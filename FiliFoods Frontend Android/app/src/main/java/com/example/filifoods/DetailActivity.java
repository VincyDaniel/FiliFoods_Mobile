package com.example.filifoods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    ImageView img;
    TextView txtName;
    TextView txtDetails, txtTags;
    TextView txtDiet,txtIngre, txtProtein, txtServingSize, txtCalories, txtTotalFat, txtSaturatedFat, txtCholesterol, txtSodium, txtTotalCarbohydrates, txtDietaryFiber, txtSugar;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            try {
                JSONArray arr = (JSONArray) msg.obj;
                JSONObject current = arr.getJSONObject(0);

                String name = current.getString("name");
                String diet = current.getString("diet");
                String cuisine = current.getString("cuisine");
                String introduction = current.getString("introduction");
                String imageUrl = current.getString("imageUrl");

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

                // Display basic details
                txtDetails.setText(recipe.getIntroduction());
                txtName.setText(recipe.getName());
                txtDiet.setText(recipe.getDiet());
                txtProtein.setText(recipe.getProtein());
                txtServingSize.setText(recipe.getServingSize());
                txtCalories.setText(recipe.getCalories());
                txtTotalFat.setText(recipe.getTotalFat());
                txtSaturatedFat.setText(recipe.getSaturatedFat());
                txtCholesterol.setText(recipe.getCholesterol());
                txtSodium.setText(recipe.getSodium());
                txtTotalCarbohydrates.setText(recipe.getTotalCarbo());
                txtDietaryFiber.setText(recipe.getFiber());
                txtSugar.setText(recipe.getSugar());

                // Display ingredients
                String[] ingredientsStr = recipe.getIngredients();
                StringBuilder ingredientsBuilder = new StringBuilder();
                for (String ingredient : ingredientsStr) {
                    ingredientsBuilder.append(ingredient).append("\n");
                }
                String ingredientsString = ingredientsBuilder.toString();
                txtIngre.setText(ingredientsString);

                // Download and display image
                RecipeRepo repo = new RecipeRepo();
                repo.downloadImage(((FiliFoodsApp) getApplication()).srv, imgHandler, recipe.getImageUrl());

                return true;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    });


    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            img.setImageBitmap((Bitmap) msg.obj);

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img = findViewById(R.id.imgDetails);
        txtName = findViewById(R.id.txtNameDetail);
        txtDiet = findViewById(R.id.textPreCookTime);
        txtDetails = findViewById(R.id.textIntro);
        txtIngre = findViewById(R.id.textIngredient);
        txtProtein = findViewById(R.id.pro_tv);
        txtServingSize = findViewById(R.id.ss_tv);
        txtCalories = findViewById(R.id.cal_tv);
        txtTotalFat = findViewById(R.id.tf_tv);
        txtSaturatedFat = findViewById(R.id.sf_tv);
        txtCholesterol = findViewById(R.id.chol_tv);
        txtSodium = findViewById(R.id.sodi_tv);
        txtTotalCarbohydrates = findViewById(R.id.tc_tv);
        txtDietaryFiber = findViewById(R.id.df_tv);
        txtSugar = findViewById(R.id.sg_tv);

        Button btn =findViewById(R.id.btnBack);
        btn.setOnClickListener((v)->{
            finish();
        });

        String name = getIntent().getExtras().getString("name");
        RecipeRepo repo = new RecipeRepo();
        repo.getDataByName(((FiliFoodsApp)getApplication()).srv,handler,name);


    }
}