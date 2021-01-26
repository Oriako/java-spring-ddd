package com.oriako.javaspringddd.shared.infrastructure;

import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LocalHostURI {

    private URI localhostURI;

    public LocalHostURI(String mappingPath, Map<String,Object> params) {
        try {
            String paramSuffix = "";
            if (params != null && !params.isEmpty()) {
                Set<String> paramSet = new HashSet<>();
                for (Map.Entry<String,Object> entry : params.entrySet()) {
                    paramSet.add(entry.getKey() + "=" + entry.getValue().toString());
                }
                paramSuffix = "?" + StringUtils.join(paramSet, "&");
            }

            localhostURI = new URI("http://127.0.0.1:8080/" + mappingPath + paramSuffix);
        } catch (Throwable e) {

        }
    }

    public static URI createURI(String mappingPath) {
        return createURI(mappingPath, null);
    }

    public static URI createURI(String mappingPath, Map<String,Object> params) {
        LocalHostURI object = new LocalHostURI(mappingPath, params);
        return object.localhostURI;
    }

}
