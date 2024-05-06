package com.example.backend.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class RecipeParser {

    public static void main(String[] args) throws IOException {
        ParsedRecipe temp = parseRecipe("https://www.budgetbytes.com/orange-julius/");
        System.out.println(temp);
    }
    public static ParsedRecipe parseRecipe(String url) throws IOException {
        ParsedRecipe parsedRecipe = null;

        if(url.contains("budgetbytes.com")){
            parsedRecipe = parseBudgetBytes(url);
        }

        return parsedRecipe;
    }

    public static ParsedRecipe parseBudgetBytes(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String title = doc.getElementsByClass("entry-title").text();
        String imageUrl = doc.getElementsByClass("wprm-recipe-image").select("img").get(1).attr("src");

        return new ParsedRecipe(title, imageUrl);
    }
}
