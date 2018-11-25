package br.com.desafioserttel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Record {
    Record(){

    }

    @SerializedName("records")
    @Expose
    List<Semaforo> records;


    public List<Semaforo> getRecords() {
        return records;
    }

    public void setRecords(List<Semaforo> records) {
        this.records = records;
    }
}
