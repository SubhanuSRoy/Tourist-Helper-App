package com.example.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_colors);

        //Create an ArrayList to store the words
        //final specifier only so that we can fetch the positon of the word to play the respective audio file
        //final is not compulsory unless you have to do something similar
        final ArrayList<Word> words = new ArrayList<Word>();
        //Initialise the elements

        //directly add a new word into our ArrayList without creating
        // a  separate object of the class Word
        words.add(new Word("red", "laal",R.drawable.red,R.raw.laal));
        words.add(new Word("green", "sobuujh",R.drawable.green,R.raw.sobuujh));
        words.add(new Word("yellow", "holuud",R.drawable.yellow,R.raw.holud));
        words.add(new Word("blue", "neel",R.drawable.blue,R.raw.neel));
        words.add(new Word("orange", "komola",R.drawable.orange,R.raw.komola));
        words.add(new Word("pink", "go-lapi",R.drawable.pink,R.raw.golapi));
        words.add(new Word("purple", "bae-guni",R.drawable.purple,R.raw.baeguni));
        words.add(new Word("light blue", "halka-neel",R.drawable.lightblue,R.raw.halka_neel));
        words.add(new Word("black", "kaalo",R.drawable.black,R.raw.kaalo));
        words.add(new Word("white", "shaada",R.drawable.white,R.raw.shaada));
        words.add(new Word("brown", "kho-ae-ri",R.drawable.brown,R.raw.khoaeri));
        words.add(new Word("dark green", "garho-sobuujh",R.drawable.darkgreen,R.raw.garho_sobuujh));


        //Next three lines of code shows the ListView & ArrayAdapter cooperation to display the list
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();



                //get the exact position of a word
                Word word = words.get(position);
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getAudio_res_id());
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