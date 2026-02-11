package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class JsonPathLearningTest {
    @Test
    void canExtractFirstRevisionUsername() throws Exception {
        try(InputStream stream = getClass().getResourceAsStream("/sample.json")) {
            assert stream != null;
            String json = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
            List<String> users = JsonPath.read(json, "$.query.pages.*.revisions[0].user");
            String firstUser = users.getFirst();
            assertEquals("Username1", firstUser);
        } // end try
    } // close void
} // end class
