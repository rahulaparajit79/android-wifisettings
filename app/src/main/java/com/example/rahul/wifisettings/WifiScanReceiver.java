package com.example.rahul.wifisettings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class WifiScanReceiver extends BroadcastReceiver {

    WifiManager wifiManager;
    StringBuilder sb;
    ListView wifiDeviceList;
    List<ScanResult> wifiList;
    WifiActivity.IWifiScanResult iWifiScanResult;
    public WifiScanReceiver(WifiManager wifiManager, ListView wifiDeviceList,WifiActivity.IWifiScanResult iWifiScanResult) {
        this.wifiManager = wifiManager;
        this.wifiDeviceList = wifiDeviceList;
        this.iWifiScanResult = iWifiScanResult;
    }
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
            sb = new StringBuilder();
            wifiList = wifiManager.getScanResults();
            iWifiScanResult.onResult(wifiList);
            ArrayList<String> deviceList = new ArrayList<>();
            for (ScanResult scanResult : wifiList) {
                sb.append("\n").append(scanResult.SSID);
                deviceList.add(scanResult.SSID);
            }
//            Toast.makeText(context, sb, Toast.LENGTH_SHORT).show();
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, deviceList.toArray());
            wifiDeviceList.setAdapter(arrayAdapter);
        }
    }


}