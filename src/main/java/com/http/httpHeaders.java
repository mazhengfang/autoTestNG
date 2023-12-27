package com.http;

import com.alibaba.fastjson.JSONObject;
import java.net.URL;
import java.util.HashMap;
import okhttp3.Headers;


public class httpHeaders {
    private String _tokenName;
    private String _token;
    private URL _domain;
    private HashMap<String, String> _headers = new HashMap();

    public httpHeaders() {
    }

    public httpHeaders(HashMap<String, String> headers) {
        this._headers = headers;
    }

    public httpHeaders Add(String name, String value) {
        this._headers.put(name, value);
        return this;
    }

    public String get(String name) {
        return (String)this._headers.get(name);
    }

    public JSONObject toBodyJSON() {
        JSONObject jo = new JSONObject();
        jo.putAll(this._headers);
        return jo;
    }

    public Headers build() {
        return Headers.of(this._headers);
    }

    public httpHeaders copy() {
        return new httpHeaders((HashMap) this._headers.clone());
    }
}
