package ar.edu.utn.tacs.group5.parser;

import java.lang.reflect.Type;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

public class GAEGson {

    private Gson gson;

    public GAEGson() {
        GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        gsonBuilder.registerTypeAdapter(Key.class, new KeySerializer());
        gsonBuilder.registerTypeAdapter(Key.class, new KeyDeserializer());
        this.gson = gsonBuilder.create();
    }

    @SuppressWarnings("rawtypes")
    public String toJsonString(List list) {
        Type collectionType = new TypeToken<List>() {}.getType();
        return gson.toJson(list, collectionType);
    }

    public <T> String toJsonString(T object, Class<T> clazz) {
        return gson.toJson(object, clazz);
    }

    public JsonArray toJsonArray(String jsonArrayString) {
        return gson.fromJson(jsonArrayString, JsonArray.class);
    }

}
