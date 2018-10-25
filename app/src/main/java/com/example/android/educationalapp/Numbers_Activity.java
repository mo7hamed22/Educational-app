package com.example.android.educationalapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class Numbers_Activity extends AppCompatActivity {
  private   MediaPlayer mMediaPlayer;
    AudioManager maudioManager;
    AudioManager.OnAudioFocusChangeListener monAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {

                     if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                       mMediaPlayer.pause();
                         mMediaPlayer.seekTo(0);

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                         //resum will use Start to resum
                         mMediaPlayer.start();
                    }
                    else if(focusChange == AudioManager.AUDIOFOCUS_LOSS)
                     {
                         // when you loss focus will release the mediaplayer
                         releaseMediaPlayer();
                     }
                }
            };


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_);



        // deling with system services throw object
        maudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        make arraylist type of class Word so we can add 2 words add the same time
       final ArrayList<Word> words=new ArrayList<Word>();
//        by this method we made inner object and set two constractor parameter than add single one in the list word
     words.add(new Word(R.drawable.number_one,"one","واحد",R.raw.onee,R.drawable.baseline_play_arrow_white_24dp ));
        words.add(new Word(R.drawable.number_two,"two","أثنين" ,R.raw.twoo,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.number_three,"three","ثلاثة" ,R.raw.three,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.number_four,"four", "أربعة " ,R.raw.four,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.number_five,"five", "خمسة" ,R.raw.four,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.number_six,"six", "ستة" ,R.raw.onee,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.number_seven,"seven", "سبعة" ,R.raw.onee,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.number_eight,"eight", "ثمانية" ,R.raw.onee,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.number_nine,"nine", "تسعة" ,R.raw.onee,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.number_ten,"ten", "عشرة" ,R.raw.onee,R.drawable.baseline_play_arrow_white_24dp));

       for (int index=0;index<words.size();index++)
        {
            WordAdapter wordadapter = new WordAdapter(this, words, R.color.category_numbers);

          ListView listView = (ListView)findViewById(R.id.list_words);

            listView.setAdapter(wordadapter);

      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Word word=words.get(position);
                releaseMediaPlayer();
                // Request audio focus for playback
                int result = maudioManager.requestAudioFocus(monAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback>>>we have audio focus now

                mMediaPlayer = MediaPlayer.create(Numbers_Activity.this,word.getSounedId_());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
            }
        });

    }}
////    method for release the un usable sound and give default value null
//    private void ReleaseMediaPlayer()
//    {
//        if(mMediaPlayer!=null) {
//            mMediaPlayer.release();
//
//            mMediaPlayer = null;
//        }
    /**}
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            maudioManager.abandonAudioFocus(monAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}