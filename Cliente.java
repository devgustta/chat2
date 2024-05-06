import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String mensagem;
        try{
            Socket socket = new Socket("localhost", 55573);

            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            
            while (true) {
                System.out.println("-=-=-=-=-=-=-=-=-=-=");
                System.out.println("Escreva a mensagem: ");
                mensagem = scanner.nextLine();
                saida.writeUTF(mensagem);

                String novaMensagem = entrada.readUTF();
                System.out.println(novaMensagem);
                
            }
        } catch (IOException e){
            e.printStackTrace();
        }


    }
}