package com.fmproducts.rysgal.talkingdictionaryapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Data {
    private static final String FILE_NAME = "data.json";

    private static String getFileText(AssetManager manager) throws IOException {
        InputStream stream = manager.open("data.json");
        int size = stream.available();
        byte[] buffer = new byte[size];
        stream.read(buffer);
        stream.close();
        return new String(buffer);
    }

    public static ArrayList<Word> getData(AssetManager manager) throws IOException {
        JsonModel model = new GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create()
                .fromJson(getFileText(manager) , JsonModel.class);
        if (model == null) return new ArrayList<>();
        return model.getWords();
    }

    private class JsonModel{
        @SerializedName("words")
        @Expose
        private ArrayList<Word> words;

        public ArrayList<Word> getWords() {
            return words;
        }

        public void setWords(ArrayList<Word> words) {
            this.words = words;
        }
    }
}
