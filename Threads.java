import java.io.*;
import java.net.Socket;

public class Threads extends Thread {

    DataInputStream in;
    DataOutputStream saida;
    private Socket socket;

    public Threads(Socket s) {
        this.socket = s;

        try{
             in = new DataInputStream(socket.getInputStream());
             saida = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void outroClient(String mensagem){
        try {
            saida.writeUTF(mensagem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarMensagem(String mensagem){
        for(Threads client: Servidor.getClients()){
            if(client != this){
                client.outroClient(mensagem);
            }
        }
    }

    public void run() {
        try {

            String mensagem = in.readUTF();
            enviarMensagem(mensagem);

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


}