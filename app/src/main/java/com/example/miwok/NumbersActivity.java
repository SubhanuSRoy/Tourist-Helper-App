package com.example.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
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
        setContentView(R.layout.activity_numbers);

        //Create an ArrayList to store the words
        //final specifier only so that we can fetch the positon of the word to play the respective audio file
        //final is not compulsory unless you have to do something similar
        final ArrayList<Word> words = new ArrayList<Word>();
        //Initialise the elements

        //directly add a new word into our ArrayList without creating
        // a  separate object of the class Word
        words.add(new Word("one","ack",R.drawable.number_one,R.raw.ack));
        words.add(new Word("two","dui",R.drawable.number_two,R.raw.dui));
        words.add(new Word("three","theen",R.drawable.number_three,R.raw.theen));
        words.add(new Word("four","chaar",R.drawable.number_four,R.raw.chaar));
        words.add(new Word("five","paanch",R.drawable.number_five,R.raw.paanch));
        words.add(new Word("six","chhoy",R.drawable.number_six,R.raw.choy));
        words.add(new Word("seven","shaath",R.drawable.number_seven,R.raw.shaath));
        words.add(new Word("eight","aat",R.drawable.number_eight,R.raw.aath));
        words.add(new Word("nine","noy",R.drawable.number_nine,R.raw.noy));
        words.add(new Word("ten","dosh",R.drawable.number_ten,R.raw.dosh));
        words.add(new Word("50","ponchaash",R.drawable.fifty,R.raw.ponchaash));
        words.add(new Word("100","ackshow",R.drawable.hundred,R.raw.ackshow));
        words.add(new Word("500","paanch-show",R.drawable.fivehundred,R.raw.paanchshow));
        words.add(new Word("1000","hajaar",R.drawable.thousand,R.raw.hajaar));

        //Next three lines of code shows the ListView & ArrayAdapter cooperation to display the list
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                //get the exact position of a word
                Word word = words.get(position);
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this,word.getAudio_res_id());
                mMediaPlayer.start();

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

        listView.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //When the activity is stopped by pressing the back button or closing the app
        //then release all the resouces of the audio files as we won't be playing
        //any sounds.
        releaseMediaPlayer();
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer(){
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    };




}


