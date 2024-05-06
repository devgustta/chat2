import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private static List<Threads> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(55573);
        System.out.println("Porta 55572 aberta!!");
        System.out.println("Esperando cliente se conectar");
        try{
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente Conectado");

                Thread clientsThread = new Threads(socket);
                clients.add((Threads) clientsThread);
                clientsThread.start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void enviarParaTodos(String mensagem){
        for(Threads client: clients){
            client.enviarMensagem(mensagem);
        }
    }

    public static List<Threads> getClients(){
        return clients;
    }
}