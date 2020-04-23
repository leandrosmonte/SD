import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CalculadoraClientHTTP {

	public static void main(String[] args) {
		
	String result="";
    try {

       URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
       HttpsURLConnection conn = (HttpsURLConnection) url.openConnection(); //abertura de conexão
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true) ;

        //ENVIO DOS PARAMETROS, após o estabelecimento da conexão
        //Stream criada para enviar valores de associada a conexao estabelecida "conn"
        OutputStream os = conn.getOutputStream();
        //buffer para realizar a escrita de valores, seguindo padroes UTF-8 e associada a stream criada
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        //escrita do buffer
        writer.write("oper1=15&oper2=15&operacao=3"); //1-somar 2-subtrair 3-dividir 4-multiplicar
        //libera o buffer
        writer.flush();
        writer.close();
      //fecha a stream
        os.close();
        
        //verificação da conexão
        int responseCode=conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {

            //RECBIMENTO DOS PARAMETROS

        	//buffer para resposta do servidor
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            
            //string para receber a mensagem do servidor
            String responseLine = null;
            //while para ler a string
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            //resultado da leitura da mensagem e print na tela
            result = response.toString();
            System.out.println("Resposta do Servidor PHP="+result);
        }
        
        //caso aconteca erro de entra ou saida
    } catch (IOException e) {
        e.printStackTrace();
    }
	}
}
