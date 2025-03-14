package com.esei.uvigo.futbolapp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class Utils {


    public static boolean isValidPasswd(String password){
        // Al menos 8 caracteres, con al menos una letra y un número
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        return password != null && Pattern.compile(passwordRegex).matcher(password).matches();

    }

    public static boolean isValidTeam(String teamname){
        String teamRegex = "^[a-zA-Z0-9]{4,}$"; //Solo letras y números, al menos cuatro caracteres
        return teamname != null && Pattern.compile(teamRegex).matcher(teamname).matches();
    }

    public static boolean isValidEmail(String email) {
        // Expresión regular para validar el formato del correo electrónico
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email != null && pattern.matcher(email).matches();
    }

    public static String hashPassword(String normalPassword){ //Metodo para hashear la contraseña

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(normalPassword.getBytes()); //HASHING DE LOS BYTES

            // Convertir bytes a formato hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        }catch(NoSuchAlgorithmException alg){

            throw new RuntimeException("Error al crear hash de contraseña", alg);
        }

    }

    public static boolean checkPassword(String normalPassword, String hashedPassword){  //Metodo para comprobar que la contraseña coincide

        String generatedHash = hashPassword(normalPassword); // Generar el hash de la contraseña
        return generatedHash.equals(hashedPassword); // Comparar el hash generado con el hash almacenado

    }
}
