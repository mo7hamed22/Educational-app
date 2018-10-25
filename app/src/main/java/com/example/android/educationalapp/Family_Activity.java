package com.example.android.educationalapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Family_Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_family_);
        //deling with service
        maudioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);

      final   ArrayList<Word> words=new ArrayList<Word>();
//        by this method we made inner object and set two constractor parameter than add single one in the list word
        words.add(new Word(R.drawable.family_father,"Father","أب" ,R.raw.twoo,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.family_mother,"Mother","أم" ,R.raw.twoo,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.family_son,"Son","أبن",R.raw.twoo,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.family_daughter,"Daughter", "أختي ",R.raw.twoo,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.family_older_brother,"Older brother", "الأخ الأكبر",R.raw.twoo,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.family_younger_brother,"younger brother", "الأخ الأصغر",R.raw.twoo,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.family_older_sister,"Older Sister", "اختي الكبيرة",R.raw.twoo,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.family_younger_sister,"younger sister", "اختي الصغيرة",R.raw.twoo,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.family_grandmother,"grandmother ", "جدة",R.raw.twoo,R.drawable.baseline_play_arrow_white_24dp));
        words.add(new Word(R.drawable.family_grandfather,"grandfather ", "جد",R.raw.twoo,R.drawable.baseline_play_arrow_white_24dp));


        for (int index=0;index<words.size();index++)
        {
            WordAdapter wordadapter = new WordAdapter(this, words, R.color.category_family);

            ListView listView = (ListView)findViewById(R.id.list_family_);

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

                        mMediaPlayer = MediaPlayer.create(Family_Activity.this,word.getSounedId_());
                        mMediaPlayer.start();
                        mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    }
                }
            });

        }

    }
    /**
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
