package br.com.desafioserttel.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SerttelService {

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://desafio.serttel.com.br/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    SerttelAPI service = retrofit.create(SerttelAPI.class);
}
