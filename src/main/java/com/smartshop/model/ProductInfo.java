package com.smartshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product_info")
public class ProductInfo {

    @Id
    @Column(name="product_info_id")
    private String productInfoId;

    @Column(name="screen")
    private String screen;

    @Column(name="os")
    private String os;

    @Column(name="camera")
    private String camera;

    @Column(name="cpu")
    private String cpu;

    @Column(name="ram")
    private String ram;

    @Column(name="hard_disk")
    private String hardDisk;

    @Column(name="battery")
    private String battery;

    @Column(name="detail")
    private String detail;

    public ProductInfo() {
    }

    public ProductInfo(String productInfoId, String screen, String os, String camera, String cpu, String ram, String hardDisk, String battery, String detail) {
        this.productInfoId = productInfoId;
        this.screen = screen;
        this.os = os;
        this.camera = camera;
        this.cpu = cpu;
        this.ram = ram;
        this.hardDisk = hardDisk;
        this.battery = battery;
        this.detail = detail;
    }

    public String getProductInfoId() {
        return productInfoId;
    }

    public void setProductInfoId(String productInfoId) {
        this.productInfoId = productInfoId;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
