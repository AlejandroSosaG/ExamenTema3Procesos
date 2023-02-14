package ejercicio1;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
public class GestorProcesos extends Thread{
    private Socket socket;
    private OutputStream os;
    public GestorProcesos(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void realizarProceso() throws IOException {
        int num1, num2, sol = 0;
        char op;
        String mensaje;
        System.out.println("Sevidor: Abriendo flujos de entrada y salida");
        InputStream is = this.socket.getInputStream();
        OutputStream os = this.socket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        try {
            mensaje = br.readLine();
            num1 = Integer.parseInt(Arrays.toString(mensaje.split(";")));
            op = mensaje.split(";");
            num2 = Integer.parseInt(Arrays.toString(mensaje.split(";")));
            switch (op){
                case '+':
                    sol = num1+num2;
                    break;
                case '-':
                    sol = num1 - num2;
                    break;
                case '*':
                    sol = num1 * num2;
                    break;
                case '/':
                    sol = num1 / num2;
                    break;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            os.write(sol);
            is.close();
            os.close();
            isr.close();
            br.close();
        }
    }
}
