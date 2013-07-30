package com.raptor.rutasombria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class ThreadMovimiento extends Thread
{
	private GoogleMap mapa;
	private Marker marcador;
	private LatLng origen,destino;
	private FragmentActivity contexto;
	private int caracter;
	public static  ArrayList<LatLng> points = null;
	
	public ThreadMovimiento(GoogleMap mapa, FragmentActivity contexto, int caracter, LatLng origen, LatLng destino) {
		this.mapa=mapa;
	
		this.contexto=contexto;
		this.caracter=caracter;
		this.origen=origen;
		marcador=	mapa.addMarker(ponerMarcador(origen, "", ""));
		this.destino=destino;
	}

	public void run() {
		contexto.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						
			
						try{
//								for(int i=0;i<10;i++){
//									double latitud=marcador.getPosition().latitude;
//									double longitud=marcador.getPosition().longitude;
//									marcador.remove();
//									Thread.sleep(5);
//									marcador=	mapa.addMarker(ponerMarcador(new LatLng(latitud+0.00010, longitud-0.00010), "", ""));
//									
//									
							String url = getDirectionsUrl(origen,destino);
							DownloadTask downloadTask = new DownloadTask();
							downloadTask.setMapa(mapa);
							 // Start downloading json data from Google Directions AP
							 downloadTask.execute(url);
							
								
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						
						
					}
				});
				
			}

private MarkerOptions ponerMarcador(LatLng sitio, String titulo, String descripcion )
{
	
	MarkerOptions marcador=new MarkerOptions()
	.position(sitio)
	.title(titulo)
	.snippet(descripcion)
	.icon(BitmapDescriptorFactory.fromResource(caracter)).anchor(0.5f, 0.5f);
	
	
	return marcador;
}
private String getDirectionsUrl(LatLng origin,LatLng dest)
{
	 
    // Origin of route
    String str_origin = "origin="+origin.latitude+","+origin.longitude;
    // Destination of route
    String str_dest = "destination="+dest.latitude+","+dest.longitude;
    // Sensor enabled
    String sensor = "sensor=false";
    // Building the parameters to the web service
    String parameters = str_origin+"&"+str_dest+"&"+sensor;
    // Output format
    String output = "json";
    // Building the url to the web service
    String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

    return url;
}
//Fetches data from url passed
private class DownloadTask extends AsyncTask<String, Void, String>{

	
	public GoogleMap getMapa() {
		return mapa;
	}

	public void setMapa(GoogleMap mapa) {
		this.mapa = mapa;
	}

	public GoogleMap mapa;
 

	// Downloading data in non-ui thread
 @Override
 
 protected String doInBackground(String... url) {

     // For storing data from web service
     String data = "";

     try{
         // Fetching the data from web service
         data = downloadUrl(url[0]);
     }catch(Exception e){
         Log.d("Background Task",e.toString());
     }
     return data;
 }

 // Executes in UI thread, after the execution of
 // doInBackground()
 @Override
 protected void onPostExecute(String result) {
     super.onPostExecute(result);

     ParserTask parserTask = new ParserTask();

     // Invokes the thread for parsing the JSON data
     parserTask.execute(result);
 }
private String downloadUrl(String strUrl) throws IOException{
 String data = "";
 InputStream iStream = null;
 HttpURLConnection urlConnection = null;
 try{
     URL url = new URL(strUrl);

     // Creating an http connection to communicate with url
     urlConnection = (HttpURLConnection) url.openConnection();

     // Connecting to url
     urlConnection.connect();

     // Reading data from url
     iStream = urlConnection.getInputStream();

     BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

     StringBuffer sb  = new StringBuffer();

     String line = "";
     while( ( line = br.readLine())  != null){
         sb.append(line);
     }

     data = sb.toString();

     br.close();

 }catch(Exception e){
     Log.d("Exception while downloading url", e.toString());
 }finally{
     iStream.close();
     urlConnection.disconnect();
 }
 return data;
}

/** A class to parse the Google Places in JSON format */
private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

 // Parsing the data in non-ui thread
 @Override
 protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

     JSONObject jObject;
     List<List<HashMap<String, String>>> routes = null;

     try{
         jObject = new JSONObject(jsonData[0]);
         DibujoRuta parser = new DibujoRuta();

         // Starts parsing data
         routes = parser.parse(jObject);
     }catch(Exception e){
         e.printStackTrace();
     }
     return routes;
 }

 // Executes in UI thread, after the parsing process
 @Override
 protected void onPostExecute(List<List<HashMap<String, String>>> result) {
     
     // Traversing through all the routes
     for(int i=0;i<result.size();i++){
         points = new ArrayList<LatLng>();
         // Fetching i-th route
         List<HashMap<String, String>> path = result.get(i);

         // Fetching all the points in i-th route
         for(int j=0;j<path.size();j++){
             HashMap<String,String> point = path.get(j);
             double lat = Double.parseDouble(point.get("lat"));
             double lng = Double.parseDouble(point.get("lng"));
             LatLng position = new LatLng(lat, lng);
             points.add(position);
     }
         for(LatLng punto: points )
		 {
//			    double latitud=punto.latitude;
//				double longitud=punto.longitude;
		//		marcador.remove();
				try {
					Thread.sleep(10);
				
				marcador=	mapa.addMarker(ponerMarcador(punto, "", ""));
			 
//				Thread.sleep(10);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		 }
     }

 }
}



}
}
