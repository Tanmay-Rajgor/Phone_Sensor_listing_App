package com.tanmay.sensorlistactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private SensorManager mSensorManager;
    private ListView mSensorListView;
    private ListAdapter mListAdapter;
    private List<Sensor> mSensorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        mSensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        mSensorListView = (ListView) findViewById(R.id.session_list);
        mListAdapter = new ListAdapter();
        mSensorListView.setAdapter(mListAdapter);
        mSensorListView.setOnItemClickListener(this);
    }

    private class ListAdapter extends BaseAdapter{
        private TextView mSensorName;


        @Override
        public int getCount() {
            return mSensorList.size();
        }

        @Override
        public Object getItem(int position) {
            return mSensorList.get(position).getName();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.list_rows,parent,false);
            }

            mSensorName = (TextView) convertView.findViewById(R.id.sensor_name);
            mSensorName.setText(mSensorList.get(position).getName());
            return convertView;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Intent intent = new Intent(getApplicationContext(),SensorCapabilityActivity.class);
        intent.putExtra(getResources().getResourceName(R.string.sensor_type),mSensorList.get(position).getType());
        startActivity(intent);
    }
}