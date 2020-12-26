package com.example.miwok;


import android.media.MediaPlayer;

/* This class contains a Bengali Translation for the default word*/
public class Word {
        private String mdefaultTranslation; //we add m to the variable name to show that it is private

        private String mbengalitranslation;

        private int mimg_resource_id;//to store the resource id of the image

        private int mAudio_res_id;


        //Constructor to define a word we need to provide its default,bengali translation,image and audio
        public Word(String defaultTranslation,String bengalitranslation,int Audio_res_id){
            mdefaultTranslation = defaultTranslation;
            mbengalitranslation = bengalitranslation;
            mAudio_res_id = Audio_res_id;
        }

        //Contructor to define a word by gving its default,bengali and img resource id
        public Word(String defaultTranslation,String bengalitranslation,int img_resource_id,int Audio_res_id){
            mdefaultTranslation = defaultTranslation;
            mbengalitranslation = bengalitranslation;
            mimg_resource_id = img_resource_id;
            mAudio_res_id = Audio_res_id;
        }


        //to get the Default Translation of the word
        public String getDefaultTranslation(){
            return mdefaultTranslation;
        };

       //to get the Bengali Translation of the word
        public String getBengaliTranslation(){
            return mbengalitranslation;
        };

        //to get the resource id of the image
        public int getImg_resource_id(){

            return mimg_resource_id;
        }

        public int getAudio_res_id(){
            return mAudio_res_id;
        }

}
