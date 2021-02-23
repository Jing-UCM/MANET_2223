package d2d.testing.gui;

import android.net.wifi.p2p.WifiP2pDevice;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import d2d.testing.MainActivity;
import d2d.testing.R;

public class FragmentDevices extends Fragment {
    public View view;

    private Button btnSend;
    private ListView listView;
    private TextView textView;
    private EditText editTextMsg;
    private TextView redMsg;
    private TextView myName;
    private TextView myAdd;
    private TextView myStatus;

    MainActivity mainActivity;

    WifiP2pDevice[] mDeviceArray;

    private WifiP2pDevice aux;


    public FragmentDevices(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.devices_fragment,container,false);
        initialWork();
        execListener();
        return view;
    }

    private void initialWork() {

        btnSend = view.findViewById(R.id.sendButton);
        listView = view.findViewById(R.id.peerListView);
        textView = view.findViewById(R.id.connectionStatus);
        editTextMsg = view.findViewById(R.id.writeMsg);
        redMsg = view.findViewById(R.id.readMsg);
        myAdd = view.findViewById(R.id.my_address);
        myName = view.findViewById(R.id.my_name);
        myStatus = view.findViewById(R.id.my_status);

        textView.setVisibility(TextView.INVISIBLE); // this text was used for debug, maybe u want to activate it again
    }

    public void setTextView(String status){
        textView.setText(status);
    }

    public static String getDeviceStatus(int deviceStatus) {
        //Log.d(MainActivity.TAG, "Peer status :" + deviceStatus);
        switch (deviceStatus) {
            case WifiP2pDevice.AVAILABLE:
                return "Available";
            case WifiP2pDevice.INVITED:
                return "Invited";
            case WifiP2pDevice.CONNECTED:
                return "Connected";
            case WifiP2pDevice.FAILED:
                return "Failed";
            case WifiP2pDevice.UNAVAILABLE:
                return "Unavailable";
            default:
                return "Unknown";

        }
    }
    public void updateMyDeviceStatus (WifiP2pDevice device) {
        if(myName != null && myStatus != null && myAdd != null) {
            myName.setText(device.deviceName);
            myStatus.setText(getDeviceStatus(device.status));
            myAdd.setText(device.deviceAddress);
        }
        else {
            aux = device;
            UpdateMyInfo task = new UpdateMyInfo();
            task.execute();

        }

    }

    public void updateMsg(String msg){
        redMsg.setText(msg);
    }

    public void updateList(DeviceListAdapter deviceListAdapter){
        listView.setAdapter(deviceListAdapter);
    }


    public void updateDeviceArray(WifiP2pDevice[] deviceArray){
        mDeviceArray = deviceArray;
    }

    public void setMainActivity(MainActivity activity){
        mainActivity = activity;
    }

    private void execListener() {


    }

    //esto quiza se pueda arreglar de otra manera,esto se hace porque
    //se intenta hacer el updateMyDeviceStatus y a veces se lanza una excepcion porque
    //los textViews aun no se han inicializado
    private class UpdateMyInfo extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            while(myName == null && myStatus == null && myAdd == null){

            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Boolean result) {
            myName.setText(aux.deviceName);
            myStatus.setText(getDeviceStatus(aux.status));
            myAdd.setText(aux.deviceAddress);
        }

        @Override
        protected void onCancelled() {
        }
    }
}
