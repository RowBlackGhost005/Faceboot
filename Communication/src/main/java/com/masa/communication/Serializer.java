package com.masa.communication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Request;

/**
 * Class that Serialize request into JSON format and De-Serializes JSONs into
 * Request objects.
 *
 * @author Luis Angel Marin
 */
public class Serializer {

    //Serializer
    private Gson gson;

    /**
     * Creates a new Serializer.
     */
    public Serializer() {
        this.gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
    }

    /**
     * Serialize the request into a String
     *
     * @param request Request to serialzie.
     * @return Request serialized as a String.
     */
    public String Serialize(Request request) {

        String operation = gson.toJson(request, Request.class);

        return operation;
    }

    /**
     * Deserializes the message received.
     *
     * @param requestSerialized Message to deserialize.
     * @return Request that represents the message deserialized.
     */
    public Request deSerialize(String requestSerialized) {

        Request request = gson.fromJson(requestSerialized, Request.class);

        return request;
    }
}
