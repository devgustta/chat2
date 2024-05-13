import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String mensagem;
        try {
            Socket socket = new Socket("localhost", 55573);

            // Inicia as threads de entrada e saída
            Input inputThread = new Input(socket);
            Output outputThread = new Output(socket);
            inputThread.start();
            outputThread.start();

            System.out.println("-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Escreva a mensagem: ");

            while (true) {
                mensagem = scanner.nextLine();
                outputThread.enviarMensagem(mensagem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Input extends Thread {
        private final DataInputStream entrada;

        // Construtor
        public Input(Socket socket) throws IOException {
            this.entrada = new DataInputStream(socket.getInputStream());
        }

        public void run() {
            while (true) {
                String novaMensagem;
                try {
                    novaMensagem = entrada.readUTF();
                    System.out.println(novaMensagem);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public static class Output extends Thread {
        private final DataOutputStream saida;

        // Construtor
        public Output(Socket socket) throws IOException {
            this.saida = new DataOutputStream(socket.getOutputStream());
        }

        public void enviarMensagem(String mensagem) throws IOException {
            saida.writeUTF(mensagem);
        }
    }
}
