package com.http;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Headers;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;

public class httpResponse {
    private String _bodyString;
    private int _code;
    private Headers _headers;
    private Exception _exception = null;

    public httpResponse(String bodyString, int code, Headers headers) {
        this._bodyString = bodyString;
        this._code = code;
        this._headers = headers;
    }

    public httpResponse(Exception e) {
        this._exception = e;
    }

    public Exception get_exception() {
        return this._exception;
    }

    public <T> T getBody(Class<T> classT) {
        if (this._exception != null) {
            if (classT == JSONObject.class) {
                return (T) JSONObject.parseObject("{\"exception\":\"" + this._exception.getMessage() + "\"}");
            } else {
                return classT == JSONArray.class ? (T) JSONArray.parseArray("[{\"exception\":\"" + this._exception.getMessage() + "\"]}") : (T) this._exception.getMessage();
            }
        } else {
            return this.convertContent(classT);
        }
    }

    public int getCode() {
        return this._code;
    }

    public Headers getHeaders() {
        return this._headers;
    }

    public String schemaVerify(String schmaJSON) {
        try {
            org.json.JSONObject obj = new org.json.JSONObject(schmaJSON);
            Schema schema = SchemaLoader.load(obj);
            schema.validate(new org.json.JSONObject((String)this.getBody(String.class)));
            return "";
        } catch (Exception var4) {
            return var4.getMessage();
        }
    }

    private <T> T convertContent(Class<T> classT) {
        T myT = null;
        if (classT == JSONObject.class) {
            myT = (T) JSONObject.parseObject(this._bodyString);
        } else if (classT == JSONArray.class) {
            myT = (T) JSONArray.parseArray(this._bodyString);
        } else {
            myT = (T) this._bodyString;
        }

        return myT;
    }
}
