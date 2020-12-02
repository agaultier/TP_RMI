package serveur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Serveur extends UnicastRemoteObject implements ServeurIntf {

	static ArrayList<String> list = new ArrayList();

	private static final long serialVersionUID = 1L;

	public Serveur() throws RemoteException {
		super(0);
	}
	

	public synchronized void sendMessage(String message) throws RemoteException {
		list.add(message);
	}

	public ArrayList<String> getAllMessage() throws RemoteException {
		if (!list.isEmpty())
			return list;
		else
			return null;
	}
	
	public String getMessage(int position) throws RemoteException {
		if (!list.isEmpty())
			return list.get(position);
		else
			return null;
	}
	
	public int countMessage() throws RemoteException {
		if (!list.isEmpty())
			return list.size();
		
		else
			return 0;
	}

	public static void main(String args[]) throws Exception {

		try {
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
		}

		Serveur chatServeur = new Serveur();
		Naming.rebind("//localhost/RmiServer", chatServeur);
		System.out.println("Serveur lancé");
	}

}