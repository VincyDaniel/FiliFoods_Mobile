package com.example.filifoods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendActivity extends AppCompatActivity {

    EditText nameEditText, dietEditText, cuisineEditText, tagsEditText, ingredientsEditText, introductionEditText, imageUrlEditText;
    EditText servingSizeEditText, caloriesEditText, totalFatEditText, saturatedFatEditText, cholesterolEditText,
            sodiumEditText, totalCarboEditText, fiberEditText, sugarEditText, proteinEditText;
    Button sendButton;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                // Recipe creation successful
                Toast.makeText(SendActivity.this, "Recipe created successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                // Recipe creation failed
                Toast.makeText(SendActivity.this, "Failed to create recipe", Toast.LENGTH_SHORT).show();
                finish();
            }
            return true;
        }
    });

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);

        nameEditText = findViewById(R.id.send_name);
        dietEditText = findViewById(R.id.send_diet);
        cuisineEditText = findViewById(R.id.send_cuisine);
        tagsEditText = findViewById(R.id.send_tags);
        ingredientsEditText = findViewById(R.id.send_ingredients);
        introductionEditText = findViewById(R.id.send_introduction);
        imageUrlEditText = findViewById(R.id.send_imageUrl);
        sendButton = findViewById(R.id.save_button);
        servingSizeEditText = findViewById(R.id.send_serving_size);
        caloriesEditText = findViewById(R.id.send_calories);
        totalFatEditText = findViewById(R.id.send_total_fat);
        saturatedFatEditText = findViewById(R.id.send_saturated_fat);
        cholesterolEditText = findViewById(R.id.send_cholesterol);
        sodiumEditText = findViewById(R.id.send_sodium);
        totalCarboEditText = findViewById(R.id.send_total_carbohydrate);
        fiberEditText = findViewById(R.id.send_fiber);
        sugarEditText = findViewById(R.id.send_sugar);
        proteinEditText = findViewById(R.id.send_protein);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String diet = dietEditText.getText().toString();
                String cuisine = cuisineEditText.getText().toString();
                String tagsInput = tagsEditText.getText().toString();
                String ingredientsInput = ingredientsEditText.getText().toString();
                String introduction = introductionEditText.getText().toString();
                String imageUrl = imageUrlEditText.getText().toString();
                String servingSize = servingSizeEditText.getText().toString();
                String calories = caloriesEditText.getText().toString();
                String totalFat = totalFatEditText.getText().toString();
                String saturatedFat = saturatedFatEditText.getText().toString();
                String cholesterol = cholesterolEditText.getText().toString();
                String sodium = sodiumEditText.getText().toString();
                String totalCarbo = totalCarboEditText.getText().toString();
                String fiber = fiberEditText.getText().toString();
                String sugar = sugarEditText.getText().toString();
                String protein = proteinEditText.getText().toString();

                // Check for empty fields
                if (TextUtils.isEmpty(servingSize) || TextUtils.isEmpty(calories) || TextUtils.isEmpty(totalFat)
                        || TextUtils.isEmpty(saturatedFat) || TextUtils.isEmpty(cholesterol)
                        || TextUtils.isEmpty(sodium) || TextUtils.isEmpty(totalCarbo)
                        || TextUtils.isEmpty(fiber) || TextUtils.isEmpty(sugar)
                        || TextUtils.isEmpty(protein)) {
                    Toast.makeText(SendActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Split tags and ingredients input into arrays
                String[] tags = tagsInput.split(",");
                String[] ingredients = ingredientsInput.split(",");

                // Create Recipe object
                Recipe recipe = new Recipe(name, diet, cuisine, introduction, imageUrl, tags, ingredients,
                        servingSize, calories, totalFat, saturatedFat, cholesterol,
                        sodium, totalCarbo, fiber, sugar, protein);

                // Create RecipeRepo object and send the recipe to the server
                RecipeRepo recipeRepo = new RecipeRepo();
                recipeRepo.sendData(((FiliFoodsApp)getApplication()).srv, handler,recipe);
            }
        });
    }
}