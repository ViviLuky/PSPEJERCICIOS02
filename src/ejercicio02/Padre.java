package ejercicio02;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Padre {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ecribe una palabra en minúscula");
        String palabra=scanner.nextLine();

        try {

            while (!palabra.isEmpty()){
                String palabraMayuscula= transformarMayusculas(palabra);
                System.out.println(palabraMayuscula);
                palabra=scanner.nextLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String transformarMayusculas(String palabra) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("java","src/ejercicio02/HijoCambioMayusculas.java");
        Process hijo = pb.start();


       if(hijo.isAlive()) {
           OutputStream outputStream = hijo.getOutputStream();
           PrintStream pshijo = new PrintStream(outputStream);

           pshijo.println(palabra);
           pshijo.flush();
           InputStream ishijo=hijo.getInputStream();

           InputStreamReader isrhijo=new InputStreamReader(ishijo);
           BufferedReader brhijo=new BufferedReader(isrhijo);

           return brhijo.readLine();
       }
       return null;
    }
}
