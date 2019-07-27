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

    public static void generateProduct(){
        for (int i = 0; i < 3; i ++) {
            getFileName(co[i]);
        }
    }

    public static void generateCart(int maxCar, int maxNumberProduct, int maxAmount){
        for (int i = 0; i < maxCar; i ++) {
            int cardId = getRandomCarId();
            if (checkCarId(cardId)) {
                for (int j = 0; j < maxNumberProduct; j++) {
                    String productId = getRandomProductId();
                    if (checkProductId(cardId, productId)){
                        try {
                            PreparedStatement st = conn.prepareStatement("insert into cart_product(`cart_id`, `product_id`, `amount`) value(?, ?, ?)");
                            st.setInt(1, cardId);
                            st.setString(2, productId);
                            st.setInt(3, (int)(Math.random()*maxAmount));
                            st.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static String getRandomProductId() {
        String result = "";
        try {
            PreparedStatement st = conn.prepareStatement("select product_id from product  order by rand() limit 1");
            ResultSet rs = st.executeQuery();
            if (rs.next())
            result = rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getRandomCarId() {
        int result = 1;
        try {
            PreparedStatement st = conn.prepareStatement("select cart_id from cart order by rand() limit 1");
            ResultSet rs = st.executeQuery();
            if (rs.next()){
            result = rs.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkCarId(int carId) {
        boolean result;
        int cache = 0;
        try {
            PreparedStatement st = conn.prepareStatement("select count(*) from cart_product where cart_id = ? ");
            st.setInt(1, carId);

            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
                cache = rs.getInt(1);
            }
            if(cache == 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkProductId(int cartId, String productId){
        boolean result;
        int cache = 0;
        try {
            PreparedStatement st = conn.prepareStatement("select count(*) from cart_product where cart_id = ? and product_id = ?");
            st.setInt(1, cartId);
            st.setString(2, productId);
            ResultSet rs =  st.executeQuery();
            if (rs.next())
             cache = rs.getInt(1);
            if (cache == 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        generateProduct();
        generateCart(4, 20, 10);
    }
}
