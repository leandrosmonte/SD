import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServerSocket {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket welcomeSocket;
		DataOutputStream socketOutput;     	
	    DataInputStream socketInput;
	    BufferedReader socketEntrada;
	    Calculadora calc = new Calculadora();
		try {
			//socket criado onde responderá à solicitações na porta 9090
			welcomeSocket = new ServerSocket(9090);
		  int i=0; //número de clientes
	  
	      System.out.println ("Servidor no ar");
	      //servidor sempre "ouvindo" na porta indicada 
	      while(true) { 
	    	  //conexao é feita
	           Socket connectionSocket = welcomeSocket.accept(); 
	           i++;
	           System.out.println ("Nova conexão");
	           
	           //Interpretando dados do servidor
	           //recebendo mensagem pela input stream criada, associada a conexão
	           socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
              //realizando a leitura da mensagem com readLine
	           String operacao= socketEntrada.readLine();
               String oper1=socketEntrada.readLine();
               String oper2=socketEntrada.readLine();
               
               //Chamando a calculadora
               //realizando os calculos com os dados recebidos anterioemnte e que foram guardadods nas variáveos operacao, oper1, oper2
               //resultados dos calculos sao armazenados nas variaveis result
               String result= ""+calc.soma(Double.parseDouble(oper1),Double.parseDouble(oper2));
               String result= ""+calc.subt(Double.parseDouble(oper1),Double.parseDouble(oper2));
               String result= ""+calc.mult(Double.parseDouble(oper1),Double.parseDouble(oper2));
               String result= ""+calc.divi(Double.parseDouble(oper1),Double.parseDouble(oper2));
               
               //Enviando dados para o servidor
               //out stream criada para o envio da mensagem ao cliente
               socketOutput= new DataOutputStream(connectionSocket.getOutputStream()); 
               //os dados são escritos com writeBytes
	           socketOutput.writeBytes(result+ '\n');
	           //print da mesnagem ao cliente
	           System.out.println (result);	
	           //libera e fecha a stream
	           socketOutput.flush();
	           socketOutput.close();

	                    
	      }
	      //se ouvir alum erro de entrada ou saida
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	}

}
