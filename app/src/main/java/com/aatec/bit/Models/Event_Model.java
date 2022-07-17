package com.aatec.bit.Models;

import java.io.Serializable;

public class Event_Model implements Serializable {
    private String date;
    private String event_body;
    private String event_title;
    private String ins_link;
    private String society;
    private String web_link;

    public Event_Model() {
    }

    public String getDate() {
        return date;
    }

    public String getEvent_body() {
        return event_body;
    }

    public String getEvent_title() {
        return event_title;
    }

    public String getIns_link() {
        return ins_link;
    }

    public String getSociety() {
        return society;
    }

    public String getWeb_link() {
        return web_link;
    }
}
