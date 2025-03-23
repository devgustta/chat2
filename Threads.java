import java.io.*;
import java.net.Socket;

public class Threads extends Thread {

    DataInputStream in;
    DataOutputStream saida;
    private final Socket socket;

    // meu Construtor Criando os objetos stream de entrada 'in' e saida 'saida'
    public Threads(Socket s) {
        this.socket = s;
        try {
            in = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    // Enviando a mensagem de fato para o outro cliente
   /* public void outroClient(String mensagem){
        try {
            saida.writeUTF(mensagem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    // pegando cada cliente conectado e passando a mensagem para o metodo outroClient do cliente
    public void ConectandoClients(String mensagem) throws IOException {
        for(Threads client: Servidor.getClients()){
            if(client != this){ // se o cliente for diferente do meu proprio cliente
               // client.outroClient(mensagem);
                client.saida.writeUTF(mensagem); // envia a mensagem para o outro cliente sem precisar do metodo outroClient
            }
        }
    }

    // Metodo run da classe thread
    public void run() {
        while (true) {
            try {


                String mensagem = in.readUTF();
                //System.out.println("Voce: " + mensagem);
                ConectandoClients(mensagem);


            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }


}
