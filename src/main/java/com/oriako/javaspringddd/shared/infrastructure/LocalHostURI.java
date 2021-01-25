package com.oriako.javaspringddd.shared.infrastructure;

import java.net.URI;

public class LocalHostURI {

    private URI localhostURI;

    public LocalHostURI(String mappingPath) {
        try {
            localhostURI = new URI("http://127.0.0.1:8080/" + mappingPath);
        } catch (Throwable e) {

        }
    }

    public URI getURI() {
        return localhostURI;
    }
}
