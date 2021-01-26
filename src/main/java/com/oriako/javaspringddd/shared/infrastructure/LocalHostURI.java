package com.oriako.javaspringddd.shared.infrastructure;

import java.net.URI;
import java.util.Map;

public class LocalHostURI {

    private URI localhostURI;

    public LocalHostURI(String mappingPath, Map<String,String[]> params) {
        try {
            localhostURI = new URI("http://127.0.0.1:8080/" + mappingPath);
        } catch (Throwable e) {

        }
    }

    public static URI createURI(String mappingPath) {
        return createURI(mappingPath, null);
    }

    public static URI createURI(String mappingPath, Map<String,String[]> params) {
        LocalHostURI object = new LocalHostURI(mappingPath, params);
        return object.localhostURI;
    }

}
