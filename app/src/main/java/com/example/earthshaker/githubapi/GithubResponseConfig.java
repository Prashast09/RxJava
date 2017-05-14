package com.example.earthshaker.githubapi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by earthshaker on 14/5/17.
 */

public class GithubResponseConfig {

    private String url;
    private String body;
    private String title;

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
