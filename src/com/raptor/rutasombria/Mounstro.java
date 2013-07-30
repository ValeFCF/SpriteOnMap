package com.raptor.rutasombria;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class Mounstro {

	LatLng PosInicial;
	Marker Personaje;
	GoogleMap Mapa;
	FragmentActivity contexto;
	int Image;
	 
	public Mounstro(Marker newPersonaje, GoogleMap newMapa, FragmentActivity newContexto, int newImage)
	{
		Mapa = newMapa;
		PosInicial = new LatLng(Mapa.getCameraPosition().target.latitude,Mapa.getCameraPosition().target.longitude);
		Personaje = newPersonaje;
		this.contexto=newContexto;
		Image = newImage;
		
	}
	
	public void Mover()
	{
		ThreadMovimiento t = new ThreadMovimiento(Mapa, contexto, Image, PosInicial, Personaje.getPosition());
		t.start();
	}

	public LatLng getPosInicial() {
		return PosInicial;
	}

	public void setPosInicial(LatLng posInicial) {
		PosInicial = posInicial;
	}

	public Marker getPersonaje() {
		return Personaje;
	}

	public void setPersonaje(Marker personaje) {
		Personaje = personaje;
	}

	public GoogleMap getMapa() {
		return Mapa;
	}

	public void setMapa(GoogleMap mapa) {
		Mapa = mapa;
	}

	public FragmentActivity getContexto() {
		return contexto;
	}

	public void setContexto(FragmentActivity contexto) {
		this.contexto = contexto;
	}

	public int getImage() {
		return Image;
	}

	public void setImage(int image) {
		Image = image;
	}
	
	
}
