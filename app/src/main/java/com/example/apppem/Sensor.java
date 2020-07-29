package com.example.apppem;

public class Sensor {
    private int estado;
    private long temperatura;
    private double humedad;
    private String dispositivo;

    public Sensor() {
        //Es obligatorio incluir constructor por defecto
    }

    public Sensor(int estado, long temperatura, double humedad, String dispositivo)
    {
        this.dispositivo = dispositivo;
        this.estado = estado;
        this.temperatura = temperatura;
        this.humedad = humedad;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public long getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(long temperatura) {
        this.temperatura = temperatura;
    }

    public double getHumedad() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "dispositivo='" + dispositivo + '\'' +
                ", estado=" + estado  +
                ", temperatura=" + temperatura +
                ", humedad=" + humedad +
                '}';
    }
}


