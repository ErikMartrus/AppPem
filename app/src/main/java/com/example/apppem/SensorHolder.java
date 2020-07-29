package com.example.apppem;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SensorHolder extends RecyclerView.ViewHolder {
    private View mView;

    public SensorHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setDispositivo(String dispositivo) {
        TextView field = (TextView) mView.findViewById(R.id.lblDispositivo);
        field.setText(dispositivo);
    }

    public void setEstado(String estado) {
        TextView field = (TextView) mView.findViewById(R.id.lblEstado);
        field.setText(estado);
    }

    public void setTemperatura(String temp) {
        TextView field = (TextView) mView.findViewById(R.id.lblTemperatura);
        field.setText(temp);
    }

    public void setHumedad(String hum) {
        TextView field = (TextView) mView.findViewById(R.id.lblHumedad);
        field.setText(hum);
    }
}
