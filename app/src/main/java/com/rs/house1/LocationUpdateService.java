package com.rs.house1;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

//import com.simhasthaadminapp.helper.Util;

public class LocationUpdateService extends Service {

	Context context;
	Thread backgroundThread;
	String strDate;
	Intent in;
	double lat;
	double lng;
	String phno;
	MyTimerTask myTask;
	Timer timer;
	String email;
	String msg;
	String gcm;
	String url = "http://www.house1.in/app/api/v1/index.php/UpdateLocation";

	//	String url = "http://sabkuchhai.in/home/api/v1/index.php/UpdateLocation";
	HttpEntity resEntity;
	SharedPreferences sharedPreferences;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		this.context = this;

		System.out.println("Service Created");
		// Toast.makeText(getApplicationContext(), "Service Created",
		// Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroy() {
		System.out.println("Service Destroyed");
		// Toast.makeText(getApplicationContext(), "Service Destroyed",
		// Toast.LENGTH_SHORT).show();
		stopSelf();
		timer.cancel();
		myTask.cancel();
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		System.out.println("Service Started");
		// Toast.makeText(getApplicationContext(), "Service Started",
		// Toast.LENGTH_SHORT).show();
		startTimer(context);

	}

	private void startTimer(Context context) {
		myTask = new MyTimerTask(context);
		timer = new Timer();
		timer.scheduleAtFixedRate(myTask, 0, 15000);

	}

	class MyTimerTask extends TimerTask {
		Context context;

		public MyTimerTask(Context context) {
			this.context = context;
		}

		@Override
		public void run() {

			Handler handler = new Handler(context.getMainLooper());
			handler.post(new Runnable() {
				public void run() {
					try {
						GPSTracker tracker = new GPSTracker(context);
						Location l = tracker.getLocation();
						lat = l.getLatitude();
						lng = l.getLongitude();

						/*phno = Util.getTelNumber(context);
						email = Util.getEmail(context);*/
						/*Toast.makeText(
								context,
								"LatLng" + lat + " " + lng + " mail " + email
										+ " phno " + phno, Toast.LENGTH_LONG)
								.show();*/
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			// Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
			System.out.println("LAT LNG= " + lat + " " + lng);
			if (lat == 0.0 || lng == 0.0)
				System.out.println("if " + lat + " " + lng);
			else
				callWebservice(lat, lng, phno, email);
		}

	}

	public void callWebservice(double latitude, double longitude, String phno,
			String email) {
		try {
			System.out.println("service called");
			doFileUpload();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private String doFileUpload() {
		try {
			Log.e("GAYA", "Starting Http File Sending to URL");
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			sharedPreferences=context.getSharedPreferences("House1", MODE_PRIVATE);
			gcm=sharedPreferences.getString("id", null);
			MultipartEntityBuilder entityBuilder = MultipartEntityBuilder
					.create();
			entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			entityBuilder.addTextBody("user_id", gcm);
			entityBuilder.addTextBody("lat", String.valueOf(lat));
			entityBuilder.addTextBody("lng", String.valueOf(lng));
			HttpEntity entity = entityBuilder.build();

			post.setEntity(entity);

			HttpResponse response = client.execute(post);
			resEntity = response.getEntity();
			System.out.println(resEntity.toString());
			String response_str = EntityUtils.toString(resEntity);
			if (resEntity != null) {
				Log.i("RESPONSE", response_str);
				try {
					JSONObject jsonObject = new JSONObject(response_str);
					String status = jsonObject.getString("status");
					if (status.equals("1")) {
						msg = jsonObject.getString("message");
					}
				} catch (Exception e) {
					e.printStackTrace();
					msg = "Server is Down or Busy, Please try after sometime";
				}

			}
		} catch (Exception ex) {
			Log.e("Debug", "error: " + ex.getMessage(), ex);
			msg = "Server is Down or Busy, Please try after sometime";

		}
		return msg;
	}

	private class GPSTracker implements LocationListener {

		private final Context mContext;

		// flag for GPS status
		boolean isGPSEnabled = false;

		// flag for network status
		boolean isNetworkEnabled = false;

		// flag for GPS status
		@SuppressWarnings("unused")
		boolean canGetLocation = false;

		Location location; // location
		@SuppressWarnings("unused")
		double latitude; // latitude
		@SuppressWarnings("unused")
		double longitude; // longitude

		// The minimum distance to change Updates in meters
		private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 2; // 2
																		// meters

		// The minimum time between updates in milliseconds
		private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1
																		// minute

		// Declaring a Location Manager
		protected LocationManager locationManager;

		public GPSTracker(Context context) {
			this.mContext = context;
			getLocation();
		}

		public Location getLocation() {
			try {
				locationManager = (LocationManager) mContext
						.getSystemService(LOCATION_SERVICE);

				// getting GPS status
				isGPSEnabled = locationManager
						.isProviderEnabled(LocationManager.GPS_PROVIDER);

				// getting network status
				isNetworkEnabled = locationManager
						.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

				if (!isGPSEnabled && !isNetworkEnabled) {
					// Utility.dialogtoturnGPSOn(mContext);
					showSettingsAlert();
					// no network provider is enabled
				}
				if (isGPSEnabled || isNetworkEnabled) {
					this.canGetLocation = true;
					if (isNetworkEnabled) {
						locationManager.requestLocationUpdates(
								LocationManager.NETWORK_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("Network", "Network");
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
					// if GPS Enabled get lat/long using GPS Services
					if (isGPSEnabled) {
						if (location == null) {
							locationManager.requestLocationUpdates(
									LocationManager.GPS_PROVIDER,
									MIN_TIME_BW_UPDATES,
									MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
							Log.d("GPS Enabled", "GPS Enabled");
							if (locationManager != null) {
								location = locationManager
										.getLastKnownLocation(LocationManager.GPS_PROVIDER);
								if (location != null) {
									latitude = location.getLatitude();
									longitude = location.getLongitude();
								}
							}
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return location;
		}

		/**
		 * Function to show settings alert dialog On pressing Settings button
		 * will lauch Settings Options
		 * */
		public void showSettingsAlert() {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

			// Setting Dialog Title
			alertDialog.setTitle("GPS is settings");

			// Setting Dialog Message
			alertDialog
					.setMessage("To Get Near By Facilities please on your gps?");

			// On pressing Settings button
			alertDialog.setPositiveButton("Settings",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(
									Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							mContext.startActivity(intent);

						}
					});

			// on pressing cancel button
			alertDialog.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});

			// Showing Alert Message
			alertDialog.show();

			// Utility.dialogtoturnGPSOn(mContext);
		}

		@Override
		public void onLocationChanged(Location location) {
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}

}