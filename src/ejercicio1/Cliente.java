package ejercicio1;

import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Cliente {
    public static void main(String[] args) {
        String cadena;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca una cadena con la siguiente estructura: num1;operacion;num2");
        cadena = sc.nextLine();
        try {
            // 1 - Crear un socket de tipo cliente indicando IP y puerto del servidor
            System.out.println("(Cliente) Estableciendo conexión con el servidor");
            Socket cliente = new Socket("127.0.0.1", 42000);
            // 2 - Abrir flujos de lectura y escritura
            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();
            System.out.println("(Cliente) Conexión establecida");
            // 3 - Intercambiar datos con el servidor
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(cadena);
            bw.newLine();
            bw.flush();
            // Leo mensajes que me envía el servidor
            int result = is.read();
            System.out.println("El resultado es " + result);
            // 4 - Cerrar flujos de lectura y escritura
            is.close();
            os.close();
            osw.close();
            bw.close();
            cliente.close();
        } catch (UnknownHostException e) {
            System.err.println("No se encuentra el host especificado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Se ha producido un error en la conexión con el servidor.");
            e.printStackTrace();
        }
    }
}
