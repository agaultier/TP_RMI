package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientListener extends Remote
{
    void newMessage(String msg) throws RemoteException;
    public String getIdentifiant() throws RemoteException;
}