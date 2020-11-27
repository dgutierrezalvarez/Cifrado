package com.company;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main<decryptedTxt> {


    public Main() throws IOException {
    }

    public static void main(String[] args) throws IOException {

            //Ejercicio 5
            int keysize = 128;
            String keyText = "puig123";
            SecretKey secretKey = Cifrado.keygenKeyGeneration(keyText, keysize);
            String text = "keytext encriptada";
            byte[] textEncriptado = Cifrado.encryptData(secretKey, text.getBytes());
            byte[] textDesencriptado = Cifrado.decryptData(secretKey, textEncriptado);
            System.out.println("text Encriptado" + new String(textEncriptado));
            System.out.println("text Encriptado" + new String(textDesencriptado));


            //Ejercicio 6
            int keysize2 = 128;
            String keyText2 = "puig123";
            SecretKey secretKey2 = Cifrado.passwordKeyGeneration(keyText, keysize);
            String text2 = "keytext encriptada";
            byte[] textEncriptado2 = Cifrado.encryptData(secretKey, text.getBytes());
            byte[] textDesencriptado2 = Cifrado.decryptData(secretKey, textEncriptado);
            System.out.println("text Encriptado" + new String(textEncriptado));
            System.out.println("text Encriptado" + new String(textDesencriptado));


            //Ejercicio 7
            System.out.println("1. Algorithm " + secretKey.getAlgorithm());
            System.out.println("2. Format " + secretKey.getFormat());
            System.out.println("2. HashCode " + secretKey.hashCode());

            byte encoded[] = secretKey.getEncoded();
            String encodedKey = Base64.getEncoder().encodeToString(encoded);

            System.out.println("4. Encoded " + encodedKey);


            //Ejercicio 8
            byte[] raw = secretKey2.getEncoded();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

            Cipher cipher;
            byte[] encrypted = null;
            try {
                cipher = Cipher.getInstance("AES");

                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

                encrypted = cipher.doFinal(text2.getBytes());
                System.out.println("raw is " + encrypted);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }


            //2
            Path path = Paths.get("/home/daniel/Escritorio/textamagat");
            byte[] txtByte = Files.readAllBytes(path);

            File file = new File("/home/daniel/Escritorio/passwd.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br =  new BufferedReader(fr);
            int keysize3 = 128;
            byte[] decryptedTxt = null;
            while (decryptedTxt == null) {
                String line = br.readLine();
                SecretKey secretKey3 = Cifrado.passwordKeyGeneration(line, keysize);
                decryptedTxt = Cifrado.decryptData(secretKey, txtByte);
            }

            System.out.println("Texto desencriptado: " + new String(decryptedTxt));




        }
}
