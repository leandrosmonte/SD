import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculadora extends Remote{

	public int soma(int a, int b) throws RemoteException;
	public int subt(int a, int b) throws RemoteException;
	public int mult(int a, int b) throws RemoteException;
	public int divi(int a, int b) throws RemoteException;
}
