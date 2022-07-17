package com.aatec.bit.Models;

import java.io.Serializable;

public class Notice_Model implements Serializable {
    private String title;
    private String body;
    private String date;
    private String link;

    public Notice_Model() {
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public String getLink() {
        return link;
    }
}
