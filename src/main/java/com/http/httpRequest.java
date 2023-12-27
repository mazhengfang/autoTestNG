package com.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.*;

import java.io.File;
import java.net.Proxy;
import java.util.*;
import java.util.concurrent.TimeUnit;



//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



        import com.alibaba.fastjson.JSON;
        import com.alibaba.fastjson.JSONArray;
        import com.alibaba.fastjson.JSONObject;
        import com.alibaba.fastjson.serializer.SerializerFeature;

        import java.io.File;
        import java.net.Proxy;
        import java.util.ArrayList;
        import java.util.Collection;
        import java.util.HashMap;
        import java.util.Iterator;
        import java.util.List;
        import java.util.concurrent.TimeUnit;
        import okhttp3.Cookie;
        import okhttp3.CookieJar;
        import okhttp3.HttpUrl;
        import okhttp3.MediaType;
        import okhttp3.MultipartBody;
        import okhttp3.OkHttpClient;
        import okhttp3.Request;
        import okhttp3.RequestBody;
        import okhttp3.Response;
        import okhttp3.Request.Builder;

public class httpRequest {
    private httpHeaders _hh = null;
    private APISetting _apiSetting = null;
//    private IAPIReporter _reporter;
    private Proxy _proxy = null;
    private final int defaultTimeout = 30;
    private HashMap<String, List<Cookie>> cookieStore = new HashMap();

    public httpRequest() {
    }

    public httpRequest(httpHeaders hh) {
        this._hh = hh;
    }

    public httpRequest(httpHeaders hh, APISetting as) {
        this._hh = hh;
        this._apiSetting = as;

    }

    public httpResponse httpGet(String url) {
        return this.httpGet(url, 30);
    }

    public httpResponse httpGet(String url, int timeout) {
        if (this._apiSetting != null) {
            url = this._apiSetting.get_server() + "/" + url;
        }

        Request.Builder builder = (new Request.Builder()).url(url).get();
        if (this._hh != null) {
            builder.headers(this._hh.build());
        }

        Request request = builder.build();
        return this.responseBuilder(request, (JSON)null, timeout);
    }

    public <J extends JSON> httpResponse httpPost(String url, J bodyContent) {
        return this.httpPost(url, bodyContent, 30);
    }

    public <J extends JSON> httpResponse httpPost(String url, J bodyContent, int timeout) {
        if (this._apiSetting != null) {
            url = this._apiSetting.get_server() + "/" + url;
        }

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, JSONObject.toJSONString(bodyContent, new SerializerFeature[]{SerializerFeature.WriteMapNullValue}));
        Request.Builder builder = (new Request.Builder()).url(url).post(body).addHeader("content-type", "application/json");
        if (this._hh != null) {
            builder.headers(this._hh.build());
        }

        Request request = builder.build();
        return this.responseBuilder(request, bodyContent, timeout);
    }

    public httpResponse httpPost_String(String url, String bodyContent, String bodyMediaType) {
        return this.httpPost_String(url, bodyContent, bodyMediaType, 30);
    }

    public httpResponse httpPost_String(String url, String bodyContent, String bodyMediaType, int timeout) {
        if (this._apiSetting != null) {
            url = this._apiSetting.get_server() + "/" + url;
        }

        MediaType mediaType = MediaType.parse(bodyMediaType);
        RequestBody body = RequestBody.create(mediaType, bodyContent);
        Request.Builder builder = (new Request.Builder()).url(url).post(body);
        if (this._hh != null) {
            builder.headers(this._hh.build());
        }

        Request request = builder.build();
        JSONObject jo = new JSONObject();
        jo.put("StringBody", bodyContent);
        return this.responseBuilder(request, jo, timeout);
    }

    public <J extends JSON> httpResponse httpPut(String url, J bodyContent) {
        return this.httpPut(url, bodyContent, 30);
    }

    public <J extends JSON> httpResponse httpPut(String url, J bodyContent, int timeout) {
        if (this._apiSetting != null) {
            url = this._apiSetting.get_server() + "/" + url;
        }

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, bodyContent.toJSONString());
        Request.Builder builder = (new Request.Builder()).url(url).put(body).addHeader("content-type", "application/json");
        if (this._hh != null) {
            builder.headers(this._hh.build());
        }

        Request request = builder.build();
        return this.responseBuilder(request, bodyContent, timeout);
    }

    public httpResponse httpPut_String(String url, String bodyContent, String bodyMediaType) {
        return this.httpPut_String(url, bodyContent, bodyMediaType, 30);
    }

    public httpResponse httpPut_String(String url, String bodyContent, String bodyMediaType, int timeout) {
        if (this._apiSetting != null) {
            url = this._apiSetting.get_server() + "/" + url;
        }

        MediaType mediaType = MediaType.parse(bodyMediaType);
        RequestBody body = RequestBody.create(mediaType, bodyContent);
        Request.Builder builder = (new Request.Builder()).url(url).put(body);
        if (this._hh != null) {
            builder.headers(this._hh.build());
        }

        Request request = builder.build();
        JSONObject jo = new JSONObject();
        jo.put("StringBody", bodyContent);
        return this.responseBuilder(request, jo, timeout);
    }

    public httpResponse httpPatch_String(String url, String bodyContent, String bodyMediaType, int timeout) {
        if (this._apiSetting != null) {
            url = this._apiSetting.get_server() + "/" + url;
        }

        MediaType mediaType = MediaType.parse(bodyMediaType);
        RequestBody body = RequestBody.create(mediaType, bodyContent);
        Request.Builder builder = (new Request.Builder()).url(url).patch(body);
        if (this._hh != null) {
            builder.headers(this._hh.build());
        }

        Request request = builder.build();
        JSONObject jo = new JSONObject();
        jo.put("StringBody", bodyContent);
        return this.responseBuilder(request, jo, timeout);
    }

    public <J extends JSON> httpResponse httpPatch(String url, J bodyContent) {
        return this.httpPatch(url, bodyContent, 30);
    }

    public <J extends JSON> httpResponse httpPatch(String url, J bodyContent, int timeout) {
        if (this._apiSetting != null) {
            url = this._apiSetting.get_server() + "/" + url;
        }

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, bodyContent.toJSONString());
        Request.Builder builder = (new Request.Builder()).url(url).patch(body).addHeader("content-type", "application/json");
        if (this._hh != null) {
            builder.headers(this._hh.build());
        }

        Request request = builder.build();
        return this.responseBuilder(request, bodyContent, timeout);
    }



    public httpResponse httpDelete(String url) {
        return this.httpDelete(url, 30);
    }

    public httpResponse httpDelete(String url, int timeout) {
        if (this._apiSetting != null) {
            url = this._apiSetting.get_server() + "/" + url;
        }

        Request.Builder builder = (new Request.Builder()).url(url).delete();
        if (this._hh != null) {
            builder.headers(this._hh.build());
        }

        Request request = builder.build();
        return this.responseBuilder(request, (JSON)null, timeout);
    }

    public httpResponse httpPostFormData(String url, httpRequest.FormData fdata) {
        return this.httpPostFormData(url, fdata, 30);
    }

    public httpResponse httpPostFormData(String url, httpRequest.FormData fdata, int timeout) {
        if (this._apiSetting != null) {
            if (url != null && !url.equals("")) {
                url = this._apiSetting.get_server() + "/" + url;
            } else {
                url = this._apiSetting.get_server();
            }
        }

        RequestBody body = fdata.getBody();
        Request.Builder builder = (new Request.Builder()).url(url).post(body).addHeader("content-type", "multipart/form-data");
        if (this._hh != null) {
            builder.headers(this._hh.build());
        }

        Request request = builder.build();
        return this.responseBuilder(request, fdata.getJSONArray(), timeout);
    }

    public httpResponse httpPostFormDataEncoded(String url, httpRequest.FormDataEncoded fdata) {
        return this.httpPostFormDataEncoded(url, fdata, 30);
    }

    public httpResponse httpPostFormDataEncoded(String url, httpRequest.FormDataEncoded fdata, int timeout) {
        if (this._apiSetting != null) {
            url = this._apiSetting.get_server() + "/" + url;
        }

        RequestBody body = fdata.getBody();
        Request.Builder builder = (new Request.Builder()).url(url).post(body).addHeader("content-type", "application/x-www-form-urlencoded");
        if (this._hh != null) {
            builder.headers(this._hh.build());
        }

        Request request = builder.build();
        return this.responseBuilder(request, fdata.getJSONArray(), timeout);
    }

    public void set_proxy(Proxy proxy) {
        this._proxy = proxy;
    }

    public httpHeaders get_httpHeaders() {
        return this._hh;
    }

    public void set_httpHeaders(httpHeaders hds) {
        this._hh = hds;
    }

    public HashMap<String, List<Cookie>> get_cookieManager() {
        return this.cookieStore;
    }

    public void set_cookieManager(HashMap<String, List<Cookie>> cookieManager) {
        this.cookieStore = cookieManager;
    }

    private httpResponse responseBuilder(Request request, JSON bodyForReport, int timeout) {
        okhttp3.OkHttpClient.Builder clientBuilder = (new OkHttpClient()).newBuilder().connectTimeout((long)timeout, TimeUnit.SECONDS).writeTimeout((long)timeout, TimeUnit.SECONDS).readTimeout((long)timeout, TimeUnit.SECONDS).cookieJar(new CookieJar() {
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                List<Cookie> comboCookies = new ArrayList();
                comboCookies.addAll(cookies);
                Iterator var4 = httpRequest.this.cookieStore.keySet().iterator();

                while(var4.hasNext()) {
                    String key = (String)var4.next();
                    if (key.equals(url.host())) {
                        comboCookies.removeAll((Collection)httpRequest.this.cookieStore.get(key));
                        comboCookies.addAll((Collection)httpRequest.this.cookieStore.get(key));
                    }
                }

                httpRequest.this.cookieStore.put(url.host(), comboCookies);
            }

            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = (List)httpRequest.this.cookieStore.get(url.host());
                return (List)(cookies != null ? cookies : new ArrayList());
            }
        });
        if (this._proxy != null) {
            clientBuilder.proxy(this._proxy);
        }

        OkHttpClient client = clientBuilder.build();
        httpResponse hrp = null;

        try {
            Response response = client.newCall(request).execute();
            String rBodyString = response.body().string();
//            this.addReportForHttp(request, bodyForReport, response, rBodyString, (Exception)null);
            hrp = new httpResponse(rBodyString, response.code(), response.headers());
            response.close();
            return hrp;
        } catch (Exception var9) {
//            this.addReportForHttp(request, bodyForReport, (Response)null, "", var9);
            return new httpResponse(var9);
        }
    }


    public class FormDataEncoded {
        private JSONArray ja = new JSONArray();
        private MediaType CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded");
        private okhttp3.FormBody.Builder builder = new okhttp3.FormBody.Builder();

        public FormDataEncoded() {
        }

        public void add(String keyname, String value) {
            this.builder.add(keyname, value);
            JSONObject jo = new JSONObject();
            jo.put(keyname, value);
            this.ja.add(jo);
        }

        public void addEncoded(String keyname, String value) {
            this.builder.addEncoded(keyname, value);
            JSONObject jo = new JSONObject();
            jo.put(keyname, value);
            this.ja.add(jo);
        }

        public RequestBody getBody() {
            return this.builder.build();
        }

        public JSONArray getJSONArray() {
            return this.ja;
        }
    }

    public class FormData {
        private JSONArray ja = new JSONArray();
        private okhttp3.MultipartBody.Builder builder;

        public FormData() {
            this.builder = (new okhttp3.MultipartBody.Builder()).setType(MultipartBody.FORM);
        }

        public void add(String keyname, String value) {
            this.builder.addFormDataPart(keyname, value);
            JSONObject jo = new JSONObject();
            jo.put(keyname, value);
            this.ja.add(jo);
        }

        public void add(String keyName, File file) {
            MediaType mediaType = MediaType.parse("multipart/form-data");
            RequestBody rb = RequestBody.create(mediaType, file);
            this.builder.addFormDataPart(keyName, file.getName(), rb);
        }

        public RequestBody getBody() {
            return this.builder.build();
        }

        public JSONArray getJSONArray() {
            return this.ja;
        }
    }
}
