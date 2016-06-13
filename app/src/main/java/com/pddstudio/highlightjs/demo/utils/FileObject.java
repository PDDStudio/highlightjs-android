package com.pddstudio.highlightjs.demo.utils;

import java.io.Serializable;
import java.net.URL;

/**
 * This Class was created by Patrick J
 * on 13.06.16. For more Details and Licensing
 * have a look at the README.md
 */

public class FileObject implements Serializable {
    
    private final String name;
    private final URL url;

    FileObject(String name, URL url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public URL getUrl() {
        return url;
    }

}
