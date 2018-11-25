package br.com.desafioserttel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Semaforo implements Serializable {

    public Semaforo(){

    }

    @SerializedName("utilizacao")
    @Expose
    private String utilizacao;

    @SerializedName("localizacao1")
    @Expose
    private String localizacao1;

    @SerializedName("localizacao2")
    @Expose
    private String localizacao2;

    @SerializedName("funcionamento")
    @Expose
    private String funcionamento;

    @SerializedName("sinalsonoro")
    @Expose
    private String sinalsonoro;

    @SerializedName("semaforo")
    @Expose
    private Integer semaforo;

    @SerializedName("sinalizadorCiclista")
    @Expose
    private String sinalizadorCiclista;

    @SerializedName("Latitude")
    @Expose
    private Float Latitude;

    @SerializedName("Longitude")
    @Expose
    private Float Longitude;

    @SerializedName("_id")
    @Expose
    private Integer _id;


    public String getUtilizacao() {
        return utilizacao;
    }

    public void setUtilizacao(String utilizacao) {
        this.utilizacao = utilizacao;
    }

    public String getLocalizacao1() {
        return localizacao1;
    }

    public void setLocalizacao1(String localizacao1) {
        this.localizacao1 = localizacao1;
    }

    public String getLocalizacao2() {
        return localizacao2;
    }

    public void setLocalizacao2(String localizacao2) {
        this.localizacao2 = localizacao2;
    }

    public String getFuncionamento() {
        return funcionamento;
    }

    public void setFuncionamento(String funcionamento) {
        this.funcionamento = funcionamento;
    }

    public String getSinalsonoro() {
        return sinalsonoro;
    }

    public void setSinalsonoro(String sinalsonoro) {
        this.sinalsonoro = sinalsonoro;
    }

    public Integer getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Integer semaforo) {
        this.semaforo = semaforo;
    }

    public String getSinalizadorCiclista() {
        return sinalizadorCiclista;
    }

    public void setSinalizadorCiclista(String sinalizadorCiclista) {
        this.sinalizadorCiclista = sinalizadorCiclista;
    }

    public Float getLatitude() {
        return Latitude;
    }

    public void setLatitude(Float latitude) {
        Latitude = latitude;
    }

    public Float getLongitude() {
        return Longitude;
    }

    public void setLongitude(Float longitude) {
        Longitude = longitude;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }
}