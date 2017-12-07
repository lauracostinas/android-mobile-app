package com.example.laura.myfirstapplication;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Laura on 16-Jan-18.
 */

public class EnumConverter {

    @TypeConverter
    public List<CustomGenre> storedStringToEnum(String value) {
        List<String> dbValues = Arrays.asList(value.split("\\s*,\\s*"));
        List<CustomGenre> enums = new ArrayList<>();

        for (String s: dbValues)
            enums.add(CustomGenre.valueOf(s));

        return enums;
    }

    @TypeConverter
    public String languagesToStoredString(List<CustomGenre> cl) {
        String value = "";

        for (CustomGenre lang : cl)
            value += lang.name() + ",";

        return value;
    }
}
