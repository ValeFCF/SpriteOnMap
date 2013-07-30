package com.raptor.rutasombria;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



public class Image extends FragmentActivity implements OnMapClickListener, OnMyLocationChangeListener {
	   private final LatLng UVG = new LatLng(19.394887617396666, -99.0910255908966 );
	    
	   GoogleMap mapa , mapaZombie;
	   LatLng NuevoPunto , Prueba, PruebaF, ResultadoPrue=null;
	   private Marker zombie=null;
	   private Marker personaje=null;
	   private int i=0;
	   
	  
	   @Override protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_image);
	      mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	      mapaZombie = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	      
	      mapaZombie.setMapType(GoogleMap.MAP_TYPE_NORMAL);//
	      mapaZombie.moveCamera(CameraUpdateFactory.newLatLngZoom(UVG, 19));
	      mapaZombie.setMyLocationEnabled(true);
	      mapaZombie.getUiSettings().setZoomControlsEnabled(false);
	      mapaZombie.getUiSettings().setCompassEnabled(true);
	      mapaZombie.setOnMapClickListener(this);
	      
	      mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	      mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(UVG, 21)); 
	      mapa.setMyLocationEnabled(true);
	      mapa.getUiSettings().setZoomControlsEnabled(false);
	      mapa.getUiSettings().setCompassEnabled(true);
	      personaje =	mapa.addMarker(ponerMarcador(UVG, "UVG", "TU",R.drawable.personaje01));
	     
	      mapa.setOnMapClickListener(this);
	      mapa.setOnMyLocationChangeListener(this);//
	      
	   }
	 
	   
	   
	   public void moveCamera(View view) {
		   	mapa.clear();
		   	NuevoPunto  = new LatLng( mapa.getMyLocation().getLatitude(),mapa.getMyLocation().getLongitude());
		   	
		   	personaje =	mapa.addMarker(ponerMarcador(NuevoPunto, "", "",R.drawable.personaje01));
		   	
		   	 mapa.moveCamera(CameraUpdateFactory.newLatLng(NuevoPunto));
		   
	   }
	 
	   public void animateCamera(View view) {
	      if (mapa.getMyLocation() != null)
	         mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(
	            new LatLng( mapa.getMyLocation().getLatitude(),                                                
	            		mapa.getMyLocation().getLongitude()), 21));
	     
	   }
	   
	   public void Mov(View view) {
		      Mounstro  c = new Mounstro(personaje, mapa, this, R.drawable.zombie01);
		      c.Mover();
		     
		   }
	   
	   
	   public void addMarker(View view) {
		   Prueba = new LatLng(mapaZombie.getCameraPosition().target.latitude,mapaZombie.getCameraPosition().target.longitude);
		   
		   zombie =	mapaZombie.addMarker(ponerMarcador(Prueba, "", "",R.drawable.zombie01));
		   
		   }
	   
	   
	   public void Follow(View view)
	   {
		  
		   double latZ=zombie.getPosition().latitude,longZ=zombie.getPosition().longitude;
		   double latP=personaje.getPosition().latitude,longP=personaje.getPosition().longitude;
		   
		   double lat=Math.abs(latZ - latP);
		   double lng=Math.abs(longZ - longP);
		  if((lat<=0.00005 && lng<=0.00005)&&( zombie!=null ))
		  {
			  personaje.remove();
			  
		  }
		   
		  double i=0.00000000001;
		  if((lat>0.00005 && lng>0.00005)&&( zombie!=null ))//NuevoPunto.latitude== Prueba.latitude )&& NuevoPunto.longitude == Prueba.longitude)
		  {
			  PruebaF = new LatLng(latZ += 0.00010, longZ += 0.00010);
			   
			   zombie =	mapaZombie.addMarker(ponerMarcador(PruebaF, "", "",R.drawable.zombie01));
			   
			   PruebaF = new LatLng(latZ += 0.00010, longZ += 0.00010);
			  
		  }
		  }   
	   
	  //Controles
	   public void ControlIzq(View view)
	   {
		 //izquierda
			switch (i) {
			case 0:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude-0.000010), "", "",R.drawable.personaje01));
				i=1;
				break;
			case 1:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude-0.000010), "", "",R.drawable.personaje02));
				i=2;
				break;
			case 2:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude-0.000010), "", "",R.drawable.personaje03));
				i=3;
				break;
			case 3:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude-0.000010), "", "",R.drawable.personaje03));
				i=4;
				break;
			case 4:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude-0.000010), "", "",R.drawable.personaje04));
				i=5;
				break;
			case 5:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude-0.000010), "", "",R.drawable.personaje05));
				i=6;
				break;
			case 6:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude-0.000010), "", "",R.drawable.personaje07));
				i=7;
				break;
			case 7:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude-0.000010), "", "",R.drawable.personaje08));
				i=8;
				break;
			case 8:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude-0.000010), "", "",R.drawable.personaje09));
				i=0;
				break;
			default:
				break;
			}
	   }
	   
	   public void ControlDer(View view)
	   {
		   switch (i) {
			case 0:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude+0.000010), "", "",R.drawable.personaje01));
				i=1;
				break;
			case 1:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude+0.000010), "", "",R.drawable.personaje02));
				i=2;
				break;
			case 2:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude+0.000010), "", "",R.drawable.personaje03));
				i=3;
				break;
			case 3:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude+0.000010), "", "",R.drawable.personaje03));
				i=4;
				break;
			case 4:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude+0.000010), "", "",R.drawable.personaje04));
				i=5;
				break;
			case 5:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude+0.000010), "", "",R.drawable.personaje05));
				i=6;
				break;
			case 6:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude+0.000010), "", "",R.drawable.personaje07));
				i=7;
				break;
			case 7:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude+0.000010), "", "",R.drawable.personaje08));
				i=8;
				break;
			case 8:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude, personaje.getPosition().longitude+0.000010), "", "",R.drawable.personaje09));
				i=0;
				break;
			default:
				break;
			}   
	   }
	   
	   public void ControlAbajo(View view)
	   {
		   switch (i) {
			case 0:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude-0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje01));
				i=1;
				break;
			case 1:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude-0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje02));
				i=2;
				break;
			case 2:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude-0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje03));
				i=3;
				break;
			case 3:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude-0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje03));
				i=4;
				break;
			case 4:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude-0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje04));
				i=5;
				break;
			case 5:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude-0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje05));
				i=6;
				break;
			case 6:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude-0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje07));
				i=7;
				break;
			case 7:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude-0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje08));
				i=8;
				break;
			case 8:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude-0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje09));
				i=0;
				break;
			default:
				break;
			}
		    
	   }
	   
	   public void ControlArriba(View view)
	   {
		      
		   switch (i) {
			case 0:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude+0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje01));
				i=1;
				break;
			case 1:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude+0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje02));
				i=2;
				break;
			case 2:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude+0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje03));
				i=3;
				break;
			case 3:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude+0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje03));
				i=4;
				break;
			case 4:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude+0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje04));
				i=5;
				break;
			case 5:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude+0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje05));
				i=6;
				break;
			case 6:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude+0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje07));
				i=7;
				break;
			case 7:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude+0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje08));
				i=8;
				break;
			case 8:
				personaje.remove();
				personaje=	mapa.addMarker(ponerMarcador(new LatLng(personaje.getPosition().latitude+0.000010, personaje.getPosition().longitude), "", "",R.drawable.personaje09));
				i=0;
				break;
			default:
				break;
			}   
		       
		}
	   
	   
	   
	   
	   
	 
	   @Override
	   public void onMapClick(LatLng puntoPulsado) {
	      mapa.addMarker(new MarkerOptions().position(puntoPulsado).
	         icon(BitmapDescriptorFactory
	            .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
	   }
	   
	   
	   private MarkerOptions ponerMarcador(LatLng sitio, String titulo, String descripcion, int imagen)
	   {
	   	
	   	MarkerOptions marcador=new MarkerOptions()
	   	.position(sitio)
	   	.title(titulo)
	   	.snippet(descripcion)
	   	.icon(BitmapDescriptorFactory.fromResource(imagen)).anchor(0.f, 0.1f);
	   	
	   	
	   	return marcador;
	   }

	   
	   
	   Location lastLocationloc =null;
	@Override
	public void onMyLocationChange(Location location) {
		zombie = mapaZombie.addMarker(ponerMarcador(UVG, "", "",R.drawable.good1));
		
	}

	public static LatLng locationToLatLng(Location loc) {
	    if(loc != null)
	        return new LatLng(loc.getLatitude(), loc.getLongitude());
	    return null;
	}
	   
	 
	   
	   
	}