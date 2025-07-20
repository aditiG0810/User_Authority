package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class UserAuthority {
    Scanner s = new Scanner(System.in);
    Connection conn;

    public UserAuthority() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b193", "root", "root");
    }

    public void run() throws SQLException {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = s.nextInt();
            s.nextLine();

            if (choice == 1) {
                registerUser();
            } else if (choice == 2) {
                loginUser();
            } else if (choice == 3) {
                System.out.println("Logout successful...");
                System.exit(0);
            } else {
                System.out.println("Invalid choice...");
            }
        }
    }

    public void registerUser() throws SQLException {
    	System.out.println("Enter user_id:");
    	int user_id = s.nextInt();
    	s.nextLine();
        System.out.print("Enter Name: ");
        String name = s.nextLine();
        System.out.print("Enter Email: ");
        String email = s.nextLine();
        System.out.print("Enter Mob no: ");
        String mobNo = s.nextLine();
        System.out.print("Enter DOB (yyyy-mm-dd): ");
        String dob = s.nextLine();

        String username = generateUsername(name);
        String password = generatePassword();

        String insertData = "insert into users values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(insertData);
        pstmt.setInt(1, user_id);
        pstmt.setString(2, name);
        pstmt.setString(3, email);
        pstmt.setString(4, mobNo);
        pstmt.setString(5, dob);
        pstmt.setString(6, username);
        pstmt.setString(7, password);
        pstmt.executeUpdate();

        System.out.println("User registered successfully...");
        System.out.println("Your username is: " + username);
        System.out.println("Your password is: " + password);
    }

    public void loginUser() throws SQLException{
    	System.out.print("Enter Username: ");
        String username = s.nextLine();
        System.out.print("Enter Password: ");
        String password = s.nextLine();

        String str = "Select * from users where username = ? AND password = ?";
        PreparedStatement pstmt = conn.prepareStatement(str);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            System.out.println("Login successful...");
        } else {
            System.out.println("Invalid username or password...");
        }
    }

    public String generateUsername(String name) {
        return name.toLowerCase().replaceAll(" ", "") + new Random().nextInt(1000);
    }

    public String generatePassword() {
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$&*";
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        password.append(getRandomChar(uppercaseLetters, random));
        password.append(getRandomChar(lowercaseLetters, random));
        password.append(getRandomChar(numbers, random));
        password.append(getRandomChar(symbols, random));

        String allCharacters = uppercaseLetters + lowercaseLetters + numbers + symbols;
        for (int i = 0; i < 4; i++) {
            password.append(getRandomChar(allCharacters, random));
        }

        String passwordStr = password.toString();
        char[] passwordArr = passwordStr.toCharArray();
        shuffleArray(passwordArr, random);
        return new String(passwordArr);
    }

    private char getRandomChar(String str, Random random) {
        return str.charAt(random.nextInt(str.length()));
    }

    private void shuffleArray(char[] arr, Random random) {
        for (int i = arr.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) throws Exception {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.run();
    }
}



