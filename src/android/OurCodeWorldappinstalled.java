package com.ourcodeworld.plugins.appinstalled;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.pm.PackageManager;

public class OurCodeWorldappinstalled extends CordovaPlugin {
    private static final String ACTION = "check";
    private static final String OPEN = "open";

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        final CallbackContext callbacks = callbackContext;
        final JSONObject arg_object = data.getJSONObject(0);
        final String packageName = arg_object.getString("packagename");

        if (ACTION.equals(action)) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    boolean installed = appInstalledOrNot(packageName);
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
        }else if(OPEN.equals(action)){
            //This intent will help you to launch if the package is already installed
            //Intent LaunchIntent = cordova.getActivity().getPackageManager().getLaunchIntentForPackage(packageName);
            //startActivity(LaunchIntent);
        }

        PluginResult pluginResult = new  PluginResult(PluginResult.Status.NO_RESULT);
        pluginResult.setKeepCallback(true); // Keep callback

        return true;
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = cordova.getActivity().getPackageManager();
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
