package com.example.earthshaker.githubapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Retrofit;
import rx.Observable;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CardAdapter mCardAdapter;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Set up Android CardView/RecycleView
         */

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCardAdapter = new CardAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mCardAdapter);

        /**
         * START: button set up
         */
        final EditText path = (EditText) findViewById(R.id.path);
        Button clearButton = (Button) findViewById(R.id.clear);
        Button findButon = (Button) findViewById(R.id.search);

        clearButton.setOnClickListener(v -> mCardAdapter.clear());


        findButon.setOnClickListener(v -> {
            String pathName = path.getText().toString().trim();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GithubService.SERVICE_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            GithubService service = retrofit.create(GithubService.class);

            Observable<List<GithubResponseConfig>> call = service.getIssueList(pathName);

            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<GithubResponseConfig>>() {

                        @Override
                        public void onCompleted() {

                        }


                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(List<GithubResponseConfig> githubResponseConfigs) {
                            mCardAdapter.setData(githubResponseConfigs);
                            mRecyclerView.setAdapter(mCardAdapter);
                        }
                    });

        });
    }

    protected void onDestroy() {
        this.subscription.unsubscribe();
        super.onDestroy();
    }
}
