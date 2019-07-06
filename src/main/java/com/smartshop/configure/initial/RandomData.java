package com.smartshop.configure.initial;

import java.util.Random;

public class RandomData {
    private static Random rn = new Random();
    public static int getPrice(){
        int suffix = 99000;
        int answer = rn.nextInt(20) + 1;
        int prefixx = answer*100000;
        return prefixx + suffix;
    }

    public static String getStatus() {
        return (Math.random() > 0.5) ? "Available" : "Unavailable";
    }

    public static int getAmount() {
        return (int)(Math.random() * 1000);
    }

    public static String getScreen() {
        String[] list = {"LCD", "IPS", "AMOLED", "SUPERAMOLED", "OLED"};
        String[] resolution = {"HD", "FULL HD", "2K", "3k", "4k"};
        return list[rn.nextInt(5)] +" " + resolution[rn.nextInt(5)];
    }

    public static String getOsPhone() {
        String[] os = {"IOS", "Android", "WindowsPhone", "Symbian"};
        return os[rn.nextInt(4)];
    }

    public static String getOsLap() {
        String[] os = {"Windows", "MacOs", "Ubuntu", "Linux", "CentOs", "DOS"};
        return os[rn.nextInt(6)];
    }

    public static String getCamera() {
        return rn.nextInt(20) + 2 + " MP";
    }

    public static String getCpuPhone() {
        String[] brand = {"Intel Atom", "Snapdragon", "Mediatek", "Exynos"};
        return brand + " " + (rn.nextInt(900) + 100);
    }

    public static String getCpuLap() {
        String[] gen1 = {"Celeron", "Pentium", "i3","i5", "i7"};
        String[] gen2 = {"Athon", "Fx", "Ryzen3", "Ryzen5", "Ryzen7"};
        if (Math.random() > 0.5){
            return "Intel " + gen1[rn.nextInt(5)] + " " + (rn.nextInt(3) + 2) + "." + rn.nextInt(10) + "GHz";
        }
        return "Amd " + gen2[rn.nextInt(5)] + " " + rn.nextInt(3) + 2 + "." + rn.nextInt(10) + "GHz";
    }

    public static String getRam() {
        return 2*(rn.nextInt(8) + 1) + "GB";
    }

    public static String getHardDisk() {
        return Math.pow(2, rn.nextInt(5) + 5) + "GB";
    }

    public static String getBattery() {
        return rn.nextInt(8) + 1 + "000 mAh";
    }
}
