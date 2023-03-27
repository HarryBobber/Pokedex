package com.example.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView pokedex;
    ArrayList<Pokemon> pokemon = new ArrayList<>();
    static ArrayList<String> removed = new ArrayList<>();
    TextView number;
    TextView type;
    TextView description;
    static boolean orientationChanged;
    static int currentIndex;
    static boolean isClicked;
    static boolean isLandscape;


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentIndex = savedInstanceState.getInt("currentIndex");
        orientationChanged = savedInstanceState.getBoolean("orientationChanged");
        isClicked = savedInstanceState.getBoolean("isClicked");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pokemon.add(new Pokemon("Garchomp", 445, "Dragon/Ground", "A land shark with the ability to fly at hypersonic speeds. Has access to a mega evolution that raises its attack to absurd levels.", R.drawable.garchomp));
        pokemon.add(new Pokemon("Gardevoir", 282, "Psychic/Fairy", "A powerful psychic pokemon with the ability to read minds and see into the future. Evolves based of gender.", R.drawable.gardevoir));
        pokemon.add(new Pokemon("Sceptile", 254, "Grass", "A tree dwelling lizard that excels at fast maneuvers. Sceptile can even beat legendary pokemon in battle like Darkrai.", R.drawable.sceptile));
        pokemon.add(new Pokemon("Groudon", 383, "Ground", "Every step this pokemon takes land is created beneath its feet. Has a legendary rivalry with Kyogry", R.drawable.groudon));
        pokemon.add(new Pokemon("Deoxys", 386, "Psychic", "Not much is known about this pokemon as it is an alien species. This pokemon has 4 forms allowing it to adapt to its current situation.", R.drawable.deoxys));
        pokemon.add(new Pokemon("Lugia", 249, "Psychic/Flying", "The protector of the ocean despite the fact it is not a water type. Lugia's wings are so powerful they can create hurricanes.", R.drawable.lugia));
        pokemon.add(new Pokemon("Lucario", 448, "Fighting/Steel", "A loyal pokemon that can sense the aura of both surrounding pokemon and people. It's signature attack aura sphere can never miss its target.", R.drawable.lucario));
        if(savedInstanceState!=null){
            removed =savedInstanceState.getStringArrayList("removed");
            for(int i=pokemon.size()-1; i>=0; i--){
                if(removed.contains(pokemon.get(i).getName()))
                    pokemon.remove(i);
            }
        }
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_mainportrait);
            number = findViewById(R.id.mainportrait_textView);
            type = findViewById(R.id.mainportrait_textView2);
            pokedex = findViewById(R.id.mainportrait_listView);
            isLandscape = false;
        }
        else{
            setContentView(R.layout.activity_mainlandscape);
            number = findViewById(R.id.mainlandscape_textView);
            type = findViewById(R.id.mainlandscape_textView2);
            description = findViewById(R.id.mainlandscape_textView3);
            pokedex = findViewById(R.id.mainlandscape_listView);
            isLandscape = true;
        }
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.activity_customadapter, pokemon, number, type);
        customAdapter.setDescription(description);
        pokedex.setAdapter(customAdapter);
        pokedex.setSelection(currentIndex);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("orientationChanged", true);
        outState.putInt("currentIndex", currentIndex);
        outState.putBoolean("isClicked", isClicked);
        outState.putStringArrayList("removed", removed);
    }
}