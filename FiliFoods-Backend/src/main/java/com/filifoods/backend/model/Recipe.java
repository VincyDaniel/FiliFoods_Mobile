package com.filifoods.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "recipes")
public class Recipe {
    @Id
    private String id;
    private String name;
    private String diet;
    private String cuisine;
    private List<String> tags;
    private List<String> ingredients;
    private String introduction;
    private String imageUrl;
    private String servingSize;
    private double calories;
    private double totalFat;
    private double saturatedFat;
    private double cholesterol;
    private double sodium;
    private double totalCarbo;
    private double fiber;
    private double sugar;
    private double protein;

    public Recipe() {
    }

    public Recipe(String name, String diet, String cuisine, List<String> tags, List<String> ingredients,
                  String introduction, String imageUrl, String servingSize, double calories,
                  double totalFat, double saturatedFat, double cholesterol, double sodium,
                  double totalCarbo, double fiber, double sugar, double protein) {
        this.name = name;
        this.diet = diet;
        this.cuisine = cuisine;
        this.tags = tags;
        this.ingredients = ingredients;
        this.introduction = introduction;
        this.imageUrl = imageUrl;
        this.servingSize = servingSize;
        this.calories = calories;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedFat;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.totalCarbo = totalCarbo;
        this.fiber = fiber;
        this.sugar = sugar;
        this.protein = protein;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Recipe [name=" + name + ", cuisine=" + cuisine + ", tags=" + tags + ", ingredients=" + ingredients
                + ", introduction=" + introduction + "]";
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getTotalCarbo() {
        return totalCarbo;
    }

    public void setTotalCarbo(double totalCarbo) {
        this.totalCarbo = totalCarbo;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }
}
