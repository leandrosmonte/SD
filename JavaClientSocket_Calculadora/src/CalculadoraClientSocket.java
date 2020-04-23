import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CalculadoraClientSocket {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		double oper1=10,oper2=20;
		int operacao=1; //1-somar 2-subtrair 3-dividir 4-multiplicar
		String result="";
        try {

        	//Conexão com o Servidor
        	//criando socket para iniciar a conexao, informando IP e porta
            Socket clientSocket = new Socket("192.168.0.11", 9090);
            //com o socket criado é possivel enviar mensagems por meio do out stream criada
            DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());
            
            //Enviando os dados
            //escrita de mensagens para o servidor por meio do metodo writeBytes
            socketSaidaServer.writeBytes(operacao+"\n");
            socketSaidaServer.writeBytes(oper1+ "\n");
            socketSaidaServer.writeBytes( oper2+ "\n");
            //liberando a stream
            socketSaidaServer.flush();

            //Recebendo a resposta
            //buffer criado para receber a mesnagem do servido, para ser lida
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            //mensagem lida do buffer e armazenada na variavel result e escrita no out print
            result=messageFromServer.readLine();
            System.out.println("resultado="+result);
            //conexao fechada
            clientSocket.close();
            
            //se ouver erro de entrada ou saida
        } catch (IOException e) {
            e.printStackTrace();
        }


	}

}
