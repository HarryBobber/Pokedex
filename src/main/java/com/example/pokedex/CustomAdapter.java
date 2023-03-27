package com.example.pokedex;

import static com.example.pokedex.MainActivity.isLandscape;
import static com.example.pokedex.MainActivity.orientationChanged;
import static com.example.pokedex.MainActivity.removed;
import static com.example.pokedex.MainActivity.currentIndex;
import static com.example.pokedex.MainActivity.isClicked;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Pokemon> {
    private ViewHolder holder;
    private List<Pokemon> pokemons;
    private TextView number;
    private TextView type;
    private TextView description;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Pokemon> objects, TextView number, TextView type) {
        super(context, resource, objects);
        pokemons = objects;
        this.number = number;
        this.type = type;
    }

    public void setDescription(TextView description){
        this.description = description;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(orientationChanged){
            convertView = null;
            orientationChanged = false;
        }
        else {
            isClicked = false;
        }
        number.setText("");
        type.setText("");
        if(isLandscape)
            description.setText("");
        currentIndex = position;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_customadapter, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.customadapter_imageView);
            holder.textView = convertView.findViewById(R.id.customadapter_textView);
            holder.button = convertView.findViewById(R.id.customadapter_button);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        Pokemon currentPokemon = getItem(position);
        if(currentPokemon!=null){
            holder.imageView.setImageResource(currentPokemon.getDrawableId());
            if(isLandscape)
                description.setText(currentPokemon.getDescription());
            if(isClicked){
                number.setText(""+currentPokemon.getNumber());
                type.setText(currentPokemon.getType());
            }
            holder.imageView.setOnClickListener(view -> {
                    number.setText(""+currentPokemon.getNumber());
                    type.setText(currentPokemon.getType());
                    isClicked = true;
            });
            holder.textView.setText(currentPokemon.getName());
            holder.button.setOnClickListener(view -> {
                pokemons.remove(currentPokemon);
                removed.add(currentPokemon.getName());
                this.notifyDataSetChanged();
            });
        }
        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
        Button button;
    }
}
