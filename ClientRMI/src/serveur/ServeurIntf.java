package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServeurIntf extends Remote{

	public void sendMessage(String message) throws RemoteException;
	public ArrayList<String> getAllMessage()throws RemoteException;
	public String getMessage(int position) throws RemoteException;
	public int countMessage() throws RemoteException;
}