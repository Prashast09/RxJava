package com.example.earthshaker.githubapi;

import java.util.List;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by earthshaker on 14/5/17.
 */

public interface GithubService {
    String SERVICE_ENDPOINT = "https://api.github.com";

    @GET("/{pathname}/issues")
    Observable<List<GithubResponseConfig>> getIssueList(@Path("pathname") String pathname);
}
