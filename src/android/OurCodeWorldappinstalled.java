package com.ourcodeworld.plugins.appinstalled;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.WindowManager;

public class OurCodeWorldappinstalled extends CordovaPlugin {
    private static final String ACTION = "check";

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        if (ACTION.equals(action)) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    boolean installed = appInstalledOrNot("com.Ch.Example.pack");
                    if(installed) {
                        PluginResult result = new PluginResult(PluginResult.Status.OK, "exists");
                        result.setKeepCallback(true);
                        callbacks.sendPluginResult(result);
                    } else {
                        PluginResult result = new PluginResult(PluginResult.Status.OK, "doesnt_exists");
                        result.setKeepCallback(true);
                        callbacks.sendPluginResult(result);
                    }
                }
            });
        }

        PluginResult pluginResult = new  PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true); // Keep callback

        return true;
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }

        return app_installed;
    }
}
