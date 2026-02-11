package edu.bsu.cs;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WikipediaRevisionService {

    private final WikipediaApiClient client;

    public WikipediaRevisionService(WikipediaApiClient client) {
        this.client = client;
    }

    public int getRevisionCount(String articleName) {
        String json = client.fetchRevisionsJson(articleName);

        JsonObject root = JsonParser.parseString(json).getAsJsonObject();
        JsonObject query = root.getAsJsonObject("query");
        JsonObject pages = query.getAsJsonObject("pages");

        JsonObject page = pages.entrySet().iterator().next().getValue().getAsJsonObject();

        if (!page.has("revisions")) {
            return 0;
        }

        return page.getAsJsonArray("revisions").size();
    }
}
