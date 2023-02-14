package ejercicio2;

import java.io.*;
import java.net.*;
public class Servidor {
    public static void main(String[] args) {
        DatagramSocket socket;
        String mensaje;
        try {
            File fichero = new File("C:\\Users\\Alumnos.txt");
            FileWriter fw = new FileWriter("C:\\Users\\Alumnos.txt");
            fichero.createNewFile();
            socket = new DatagramSocket(42000);
            while (true) {
                System.out.println("Creación del array de bytes");
                byte[] buffer = new byte[64];
                System.out.println("Creación del datagrama");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                System.out.println("A la espera de recibir datagrama");
                socket.receive(packet);
                mensaje = new String(packet.getData()).trim();
                String respuesta = null;
                if (mensaje.contains("CREATE")){
                    fw.write(mensaje.substring(7));
                    fw.flush();
                    respuesta = "Alumno introducido correctamente";
                }else if (mensaje.contains("SELECT")){
                    FileReader fr = new FileReader(fichero);
                    String linea;
                    while ((linea = String.valueOf(fr.read()))!=null) respuesta += linea;
                }
                byte[] bufferEnvio = respuesta.getBytes();
                DatagramPacket packetEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, packet.getAddress(), packet.getPort());
                socket.send(packetEnvio);
            }
        } catch (SocketException e) {
            System.out.println("Error al crear el Socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al recibir el paquete");
            e.printStackTrace();
        }
    }
}
