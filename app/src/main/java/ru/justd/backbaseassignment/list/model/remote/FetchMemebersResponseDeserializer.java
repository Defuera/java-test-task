package ru.justd.backbaseassignment.list.model.remote;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.justd.backbaseassignment.list.model.Department;
import ru.justd.backbaseassignment.list.model.Member;

/**
 * Created by defuera on 20/04/2017.
 */

public class FetchMemebersResponseDeserializer implements JsonDeserializer<FetchMemebersResponse> {

    @Override
    public FetchMemebersResponse deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException {
        if (json.isJsonObject()) {

            JsonObject object = json.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entries = object.entrySet();

            List<Department> departments = new ArrayList<>();
            for (Map.Entry<String, JsonElement> entry : entries) {
                departments.add(
                        new Department(
                                entry.getKey(),
                                deserializeList(context, entry.getValue().getAsJsonArray(), Member.class)
                        )
                );
            }

            return new FetchMemebersResponse(departments);
        } else {
            return null;
        }
    }

    private static <T> List<T> deserializeList(
            JsonDeserializationContext context,
            JsonArray array,
            Class<T> clazz) {
        List<T> result = new ArrayList<>();

        if(array != null) {
            for (JsonElement element : array) {
                result.add(context.deserialize(element, clazz));
            }
        }

        return result;
    }

}
