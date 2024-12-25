package com.example.networkscanner.entidades;

import java.io.Serializable;

public class Host implements Serializable {

    private String ip;
    private String mac;
    private String OperatingSystem;

    public Host(String ip, String mac, String operatingSystem) {
        this.ip = ip;
        this.mac = mac;
        OperatingSystem = operatingSystem;
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
}
