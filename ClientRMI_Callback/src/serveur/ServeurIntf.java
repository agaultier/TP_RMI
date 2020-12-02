package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import client.ClientListener;

public interface ServeurIntf extends Remote{

	void addClientListener(ClientListener ClientListener) throws RemoteException;

    void removeClientListener(ClientListener ClientListener) throws RemoteException;

    public void sendMessage(String msg, String id) throws RemoteException;
    
}