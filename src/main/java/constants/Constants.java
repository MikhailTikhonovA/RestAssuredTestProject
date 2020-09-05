package constants;

import static constants.Constants.Servers.SWAPI_URL;

public class Constants {

    public static class RunVariable {
        public static String server = SWAPI_URL;
        public static String path = "";
    }

    public static class Servers {
        public static String SWAPI_URL = "https://swapi.dev/";
        public static String JSON_PLACEHOLDER_URL = "http://jsonplaceholder.typicode.com";
        public static String REQUEST_BIN = "https://6cd618e5c8681556c29e4910dc4ae994.m.pipedream.net";
    }

    public static class Path {
        public static String SWAPI_PATH = "api/";
    }

    public static class Actions {
        public static String SWAPI_GET_PEOPLE = "people/";

        public static String JSON_PLACEHOLDER_GET = "comments/";
        public static String JSON_PLACEHOLDER_PUT = "posts/1";
        public static String JSON_PLACEHOLDER_DELETE = "posts/1";
        public static String JSON_PLACEHOLDER_POST = "posts/";

    }
}
