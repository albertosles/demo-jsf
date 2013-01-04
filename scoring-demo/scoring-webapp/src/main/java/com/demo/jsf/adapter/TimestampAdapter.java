package com.demo.jsf.adapter;


import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampAdapter extends XmlAdapter<String, Timestamp> {
    public String marshal(Timestamp v) {
        return v.toString();
    }
    public Timestamp unmarshal(String v) {
        return Timestamp.valueOf(v);
    }
}