package com.example.android.educationalapp;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mohamed on 26/09/2018.
 */

  public class WordAdapter extends ArrayAdapter<Word> {

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();
//    id for the color for each lis utem
    private int set_Color;
    public WordAdapter(Activity context, ArrayList<Word> Word,int sColor) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, Word);
        set_Color=sColor;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);
        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView ArabicTextView = (TextView) listItemView.findViewById(R.id.Arabic);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        ArabicTextView.setText(currentWord.GetArabichWord());
        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView English_TextView = (TextView) listItemView.findViewById(R.id.English);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        English_TextView.setText(currentWord.GetEglishWord());
//         Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.add_imag);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
//        we make if thers imge show her than give it it's src '
        if (currentWord.HaveImge()) {
//            set the src for the image
            iconView.setImageResource(currentWord.getImage_iconesrc());
//            make it to be visible
            iconView.setVisibility(View.VISIBLE);
        }
        else
        {
//            if ther are no imahes like in phrases then delete the whole thing about image even it's space'
            iconView.setVisibility(View.GONE);
        }


//      set them color for listItemView
        View textContainer=listItemView.findViewById(R.id.text_Contanier);
//        find the clor that the id resorce map to
        int color = ContextCompat.getColor(getContext(),set_Color);
        textContainer.setBackgroundColor(color);
//set display icon image
        ImageView icon_player = (ImageView) listItemView.findViewById(R.id.ic_imge);
        icon_player.setImageResource(currentWord.ic_id_());


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
