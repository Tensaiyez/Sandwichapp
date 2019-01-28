package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;


    private TextView descriptions;
    private TextView origin;
    private TextView alsoKnownAs;
    private TextView ingredients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        List<String> alsoknownas = sandwich.getAlsoKnownAs();
        List<String> ingredient = sandwich.getIngredients();
        String placeoforigin = sandwich.getPlaceOfOrigin();
        String description = sandwich.getDescription();


        descriptions = (TextView) findViewById(R.id.description_tv);
        origin = (TextView) findViewById(R.id.origin_tv);
        alsoKnownAs = (TextView) findViewById(R.id.also_known_tv);
        ingredients = (TextView) findViewById(R.id.ingredients_tv);

        origin.setText(placeoforigin);
        descriptions.setText(description);

        for (int i = 0; i < alsoknownas.size(); i++) {
            alsoKnownAs.append(alsoknownas.get(i));
            alsoKnownAs.append("\n");
        }
        for (int i = 0; i < ingredient.size(); i++) {
            ingredients.append(ingredient.get(i));
            ingredients.append("\n");
        }


    }
}
