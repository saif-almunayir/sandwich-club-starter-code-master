package com.udacity.sandwichclub;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView PlaceOforigin;
    private ImageView imageView;
    private TextView Decribtion;
    private TextView Ingredans;
    private TextView AlsoKnowAs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        PlaceOforigin=(TextView)findViewById(R.id.origin_tv);
        imageView=(ImageView) findViewById(R.id.image_iv);
        Decribtion=(TextView)findViewById(R.id.description_tv);
        Ingredans=(TextView)findViewById(R.id.ingredients_tv);
        AlsoKnowAs=(TextView)findViewById(R.id.also_known_tv);
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
        PlaceOforigin.setText(sandwich.getPlaceOfOrigin());
        Decribtion.setText(sandwich.getDescription());
        for(int i=0;i<sandwich.getAlsoKnownAs().size();i++){
            AlsoKnowAs.append(sandwich.getAlsoKnownAs().get(i));
        }
        for(int i1=0;i1<sandwich.getIngredients().size();i1++){
            Ingredans.append(sandwich.getIngredients().get(i1));
        }
    }

}
