package br.com.desafioserttel.service;

import br.com.desafioserttel.model.Record;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SerttelAPI {

    @GET("dadosRecifeSemaforo.json")
    Call<Record> getSemaforos();

}