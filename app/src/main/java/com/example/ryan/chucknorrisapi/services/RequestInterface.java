package com.example.ryan.chucknorrisapi.services;

import com.example.ryan.chucknorrisapi.model.ChuckNorrisModel;
import com.example.ryan.chucknorrisapi.util.API_List;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Ryan on 22/11/2017.
 */

public interface RequestInterface {

    @GET(API_List.CHUCKNORRIS_API)
    Observable<ChuckNorrisModel>getChuckNorrisModel();

}
