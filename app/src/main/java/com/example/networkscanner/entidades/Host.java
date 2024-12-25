package com.example.networkscanner.entidades;

import java.io.Serializable;

public class Host implements Serializable {

    private String ip;
    private String mac;
    private String OperatingSystem;
    private String fabricante;

    public Host(String ip, String mac, String operatingSystem) {
        this.ip = ip;
        this.mac = mac;
        OperatingSystem = operatingSystem;
    }

    public Host(String ip, String mac, String operatingSystem, String fabricante) {
        this.ip = ip;
        this.mac = mac;
        OperatingSystem = operatingSystem;
        this.fabricante = fabricante;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getOperatingSystem() {
        return OperatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        OperatingSystem = operatingSystem;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
}
