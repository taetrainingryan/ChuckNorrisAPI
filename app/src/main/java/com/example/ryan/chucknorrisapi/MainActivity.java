package com.example.ryan.chucknorrisapi;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.ryan.chucknorrisapi.model.ChuckJoke;
import com.example.ryan.chucknorrisapi.model.ChuckNorrisModel;
import com.example.ryan.chucknorrisapi.services.RequestInterface;
import com.example.ryan.chucknorrisapi.services.ServerConnection;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RequestInterface requestInterface;
    private List<ChuckJoke> jokes;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        recyclerView = (RecyclerView) findViewById(R.id.rvJokes);

        requestInterface = ServerConnection.getServerConnection();

        checkNetwork();

    }

    public void initializeRecyclerView(){

        recyclerView = (RecyclerView) findViewById(R.id.rvJokes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        viewAdapter = new ViewAdapter(jokes, R.layout.row, getApplicationContext());

        recyclerView.setAdapter(viewAdapter);

    }

    public void initializeSwipeListener(){
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        Toast.makeText(MainActivity.this, "being run", Toast.LENGTH_SHORT).show();

                        viewAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);

                    }
                }
        );
    }

    public void checkNetwork(){

        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override public void accept(Boolean isConnectedToInternet) {

                        if (isConnectedToInternet == true){

                            makeConnectiontoAPI();
                        }

                        else{

                            Toast.makeText(MainActivity.this, "You have no internet dude wtf", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void makeConnectiontoAPI(){
        requestInterface.getChuckNorrisModel()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ChuckNorrisModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChuckNorrisModel value) {

                        jokes = new ArrayList<> (value.getValue());

                        initializeRecyclerView();

                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {
                        initializeSwipeListener();

                    }
                });
    }


}
