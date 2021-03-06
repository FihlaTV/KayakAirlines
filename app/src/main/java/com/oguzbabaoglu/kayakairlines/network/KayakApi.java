package com.oguzbabaoglu.kayakairlines.network;

import com.oguzbabaoglu.kayakairlines.network.model.AirlineResponse;

import java.util.List;

import retrofit2.http.GET;
import rx.Single;

public interface KayakApi {

  @GET("/h/mobileapis/directory/airlines") Single<List<AirlineResponse>> getAirlines();
}
