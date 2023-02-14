package ejercicio2;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            //  Obtener dirección IP local
            InetAddress direccion = InetAddress.getByName("127.0.0.1");
            // Creación del socket
            DatagramSocket socket = new DatagramSocket();
            //  Creación del mensaje
            String respuesta;
            do {
                System.out.println("Introduzca una cadena");
                String cadena = sc.nextLine();
                byte[] buffer = cadena.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, 42000);
                socket.send(packet);
                socket.receive(packet);
                respuesta = new String(packet.getData());
                System.out.println(respuesta);
            }while (respuesta!="Es el número secreto ");

        } catch (UnknownHostException e) {
            System.err.println("Error al crear el Socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error al enviar el paquete");
            e.printStackTrace();
        }
    }
}
