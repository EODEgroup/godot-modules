package com.android.godot;

import android.app.Activity;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

import android.provider.Settings;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class Advertising extends Godot.SingletonBase {
	//===================================== MEMBERS ===========================================//
	InterstitialAd interstitial;
	AdView adView;
	boolean initialized;
	private Activity activity;

	public boolean leftApplication; 
	public boolean failedToLoad;
	public boolean loaded; 
	public boolean opened;
	public boolean closed;
    public int script_id;

	//===================================== INIT SINGLETON MODULE FROM GODOT ===========================================//
	static public Godot.SingletonBase initialize(Activity p_activity) {
		return new Advertising(p_activity);
	}

	//===================================== MAIN ===========================================//
	public Advertising(Activity p_activity) {
		activity = p_activity;
		initialized = false;
		registerClass("Advertising", new String[]{"show_interstitial"});

		activity.runOnUiThread(new Runnable() {
			public void run() {
				InitializeUiThread(GodotLib.getGlobal("advertising/api_key"));
			}
		});

	}
	//===================================== MISC ===========================================//
	private String get_device_id(){
		String device_id_string = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
		String deviceId = md5(device_id_string).toUpperCase();

		return deviceId;
	}

	private String md5(final String s) {
		try {
			// Create MD5 Hash
			MessageDigest   digest  = java.security.MessageDigest.getInstance("MD5");

			digest.update(s.getBytes());
			byte        messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer    hexString       = new StringBuffer();
			for(int i=0; i<messageDigest.length; i++) {
				String h = Integer.toHexString(0xFF & messageDigest[i]);
				while (h.length() < 2) {
					h = "0" + h;
				}
				hexString.append(h);
			}
			return hexString.toString();
		}
		catch(NoSuchAlgorithmException e) {
			Log.d("--------- godot ----------", "failed to get android DEVICE ID from Java");
		}
		return "";
	}

	//===================================== BUILD AD REQUEST ===========================================//
	private AdRequest buildAdRequest() {
		return new AdRequest.Builder().build(); 
	}

	//===================================== INTERSTITIAL ===========================================//
	public void show_interstitial(){
		if (!is_init()){
			showNotInitErr();
			return;
		}

		activity.runOnUiThread(new Runnable() {
			public void run() {
				if (interstitial.isLoaded()) {
					interstitial.show();
				}
				else { 
					Log.e("godot", "Advertising [Interstitial] : can't show interstitial, not loaded yet");
				}
			}
		});
	}
	//===================================== INIT TEST ===========================================//
	public boolean is_init(){
		return initialized;
	}		

	private void showNotInitErr(){
		Log.e("godot", "Advertising : Invalid Call, module not Initialized");
	}

	//===================================== INIT ===========================================//
	public void InitializeUiThread(final String admob_unit_id){
		initInterstitial(admob_unit_id);
		initialized = true;
		Log.d("godot", "Advertising : Initialized");
	}

	private void initInterstitial(final String admob_unit_id) {
		// Create InterstitialAd and start loading one
		interstitial = new InterstitialAd(activity);
		interstitial.setAdUnitId(admob_unit_id);
		interstitial.setAdListener(new AdListener() {
			@Override
			public void onAdClosed() {
				//Start loading next interstitial
				AdRequest ad = buildAdRequest();	
				interstitial.loadAd(ad);
				Log.i("godot", "Advertising [Interstitial] : Closed");
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				String err;

				switch(errorCode) {
					case AdRequest.ERROR_CODE_INTERNAL_ERROR:
						err = "ERROR_CODE_INTERNAL_ERROR";
						break;
					case AdRequest.ERROR_CODE_INVALID_REQUEST:
						err = "ERROR_CODE_INVALID_REQUEST";
						break;
					case AdRequest.ERROR_CODE_NETWORK_ERROR:
						err = "ERROR_CODE_NETWORK_ERROR";
						break;
					case AdRequest.ERROR_CODE_NO_FILL:
						err = "ERROR_CODE_NO_FILL";
						break;
					default:
						err = "m_interstitial failed to load an ad because of unknown error code";
						break;
				}

				Log.e("godot", "Advertising [Interstitial] : Failed to load" + err);
			}

			@Override
			public void onAdLoaded() {
				Log.i("godot", "Advertising [Interstitial] : Loaded");
			}

			@Override
			public void onAdOpened() {
				Log.i("godot", "Advertising [Interstitial] : Opened");
			}

			@Override
			public void onAdLeftApplication() {
				Log.i("godot", "Advertising [Interstitial] : Left application");
			}
		});

		//Start Loading an interstitial ad
		AdRequest ad = buildAdRequest();
		interstitial.loadAd(ad);
	}
}
