package com.example.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_phrases);

        //Create an ArrayList to store the words
        //final specifier only so that we can fetch the positon of the word to play the respective audio file
        //final is not compulsory unless you have to do something similar
        final ArrayList<Word> words = new ArrayList<Word>();
        //Initialise the elements

        //directly add a new word into our ArrayList without creating
        // a  separate object of the class Word
        words.add(new Word("What is your name?","Apnaar naam ki?",R.raw.apnar_naam_ki));
        words.add(new Word("Where are you going?","Apni kothay jachchen?",R.raw.apni_kothay_jachchen));
        words.add(new Word("My name is ______","Amar naam ______",R.raw.amar_naam));
        words.add(new Word("I will go to ______","Ami _______ jabo",R.raw.ami_jabo));
        words.add(new Word("What is the price of this ?","Etar daam koto?",R.raw.etar_daam_koto));
        words.add(new Word("The price of this is _____","Etar daam holo ____",R.raw.etar_daam_holo));
        words.add(new Word("How much money needed?","Koto taka lagbe?",R.raw.koto_taka_lagbe));
        words.add(new Word("I need _____ money","Amar ____ taka lagbe",R.raw.amar_taka_lagbe));
        words.add(new Word("How much time will it take?","Kotokhhon lagbe?",R.raw.kotokhon_lagbe));
        words.add(new Word("hours","Ghonta",R.raw.ghonta));
        words.add(new Word("Let's go","Cholo jai",R.raw.cholo_jai));
        words.add(new Word("Come here","Ekhane asho",R.raw.ekhane_asho));
        words.add(new Word("good","bhalo",R.raw.bhalo));
        words.add(new Word("bad","baaje",R.raw.baaje));
        words.add(new Word("This is","Eta",R.raw.eta));
        words.add(new Word("That is","ota",R.raw.ota));
        words.add(new Word("I will eat","Ami khabo",R.raw.ami_khabo));
        words.add(new Word("I","ami",R.raw.ami));
        words.add(new Word("you","tumi",R.raw.tumi));
        words.add(new Word("we","amra",R.raw.amra));

        //Next three lines of code shows the ListView & ArrayAdapter cooperation to display the list
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                //get the exact position of a word
                Word word = words.get(position);
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this,word.getAudio_res_id());
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