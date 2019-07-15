package com.smartshop.configuration.initial;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class InitializerData {

    public static int countTotal = 1;

    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/urd_smartshop?" +
                        "user=admin&password=admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ;

    private static String[] co = {"Phone", "Laptop", "Tablet"};

    public static void getFileName(String suffix){
        /*try (Stream<Path> paths = Files.walk(Paths.get("../resources/img/Phone"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException e){
            System.out.println("Khong tim thay file");
        }*/

        File folder = new File("./src/main/resources/img/" + suffix);
        File[] listOfFiles = folder.listFiles();

        int count = 100;

        for (File file : listOfFiles) {
            if (file.isFile()) {
                processed(file.getName(), suffix, ++count);
            }
        }
        System.out.println("Success!!!!!!!!!!");

    }

    public static void processed(String name, String suffix, int count) {
        String nameProduct = name.replace('-', ' ');
        nameProduct = nameProduct.replace(" 400x400", "");
        nameProduct = nameProduct.replace(".jpg", "");
        nameProduct = nameProduct.replace(" 200x200", "");

        nameProduct = capitalizeString(nameProduct);
        String productId = suffix + count;
            String brand = name.substring(0, name.indexOf('-'));

        String category = suffix;;

        try {
            String sql = "insert into `image` (`image_id`, `image_name`) value (?, ?);";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, countTotal);
            st.setString(2, name);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String sql2 = "insert into `product_info` value (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql2);
            st.setString(1, productId );
            st.setString(2, RandomData.getScreen());
            if (suffix == "Laptop"){
                st.setString(3, RandomData.getOsLap());
                st.setString(5, RandomData.getCpuLap());
            }else {
                st.setString(3, RandomData.getOsPhone());
                st.setString(5, RandomData.getCpuPhone());
            }
            st.setString(4, RandomData.getCamera());
            st.setString(5, RandomData.getCpuLap());
            st.setString(6, RandomData.getRam());
            st.setString(7, RandomData.getHardDisk());
            st.setString(8, RandomData.getBattery());
            st.setString(9, null);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(nameProduct);
        }

        try {
            String sql = "insert into `product` (`product_id`, `name`, `price`, `category`, `status`, `amount`, `product_info_id`) value (?, ?, ?, ?,  ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, productId);
            st.setString(2, nameProduct);
            st.setInt(3, RandomData.getPrice());
            st.setString(4, suffix);
            st.setString(5, "Availabel");
            st.setInt(6, RandomData.getAmount());
            st.setString(7, productId);
            st.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            String sql = "insert into `product_image` value (?, ?);";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, productId);
            st.setInt(2, countTotal++);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {


        for (int i = 0; i < 3; i ++) {
            getFileName(co[i]);
        }
    }
}
