package com.rs.helper;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * 
 * @author Richa
 * 
 *
 */
public class StatusCheckerUtility {
	public static boolean checkInternetConnectivity(Context context,Intent intent) {
		@SuppressWarnings("deprecation")
		NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
		String state = getNetworkStateString(info.getState());
		if (!state.equals("Connected") || !state.equals("Connecting")) {
			return false;
		}
		return true;
	}

	public static boolean checkGPSStatus(Context context) 
	{
		final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	public static String getNetworkStateString(NetworkInfo.State state) {
		String stateString = "Unknown";

		switch (state) {
		case CONNECTED:
			stateString = "Connected";
			break;
		case CONNECTING:
			stateString = "Connecting";
			break;
		case DISCONNECTED:
			stateString = "Disconnected";
			break;
		case DISCONNECTING:
			stateString = "Disconnecting";
			break;
		case SUSPENDED:
			stateString = "Suspended";
			break;
		default:
			stateString = "Unknown";
			break;
		}

		return stateString;
	}

	public static boolean isConnected(Context context) {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		 ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	NetworkInfo[] netInfo = cm.getAllNetworkInfo();
for(NetworkInfo ni : netInfo)
{
		if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting())
		{
		haveConnectedWifi = true;
		}else
		if ( cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!=null)
		{
			if(ni.isConnected())
		haveConnectedMobile = true;
		}

}
		return haveConnectedWifi || haveConnectedMobile;
	}
}
