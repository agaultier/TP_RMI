package serveur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import client.ClientListener;


public class Serveur extends UnicastRemoteObject implements ServeurIntf {

	
	private static final long serialVersionUID = 1L;
	
	
	private List<ClientListener> listeners = new ArrayList<>();

	
	protected Serveur() throws RemoteException {
		
	}
	
	
	 @Override
	    public void sendMessage(String msg, String id) throws RemoteException{
		 msg = id+" : "+msg;
	    	notifyClientListeners(msg,id);
	    	/*on ajoute donc l'identifiant au message, on envoit ensuite le r�sultat au notify et l'identifiant aussi permettant
	    	donc d'envoyer le message � tout le monde sauf � la personne qui a envoy� le message*/
		}
	
	
	private void notifyClientListeners(String msg, String id) throws RemoteException
    {
        for (ClientListener lListener : listeners)
        { 	if(!lListener.getIdentifiant().equals(id)) 
        //Si l'identifiant du Client n'est pas le m�me que celui de l'envoyeur alors on execute newMessage
        {
            try
            {
            	lListener.newMessage(msg);
            }
            catch (RemoteException e)
            {		
            	this.removeClientListener(lListener);
			
            }
            
        }
        }
    }

    @Override
    public synchronized void addClientListener(ClientListener clientListener) throws RemoteException
    {
        listeners.add(clientListener);
        notifyClientListeners(clientListener.getIdentifiant()+" s'est connect�",clientListener.getIdentifiant());
        //On ajoute un Client � la liste des clients et on averti tout le monde qu'il s'est connect�
    }

    @Override
    public void removeClientListener(ClientListener clientListener) throws RemoteException
    {
        listeners.remove(clientListener);
        notifyClientListeners(clientListener.getIdentifiant()+" s'est d�connect�",clientListener.getIdentifiant());
      //On enl�ve un Client � la liste des clients et on averti tout le monde qu'il s'est d�connect�
    }
   
   

	public static void main(String args[])  {

		try {
			LocateRegistry.createRegistry(1099);
			
		Serveur chatServeur = new Serveur();
		Naming.rebind("//localhost/RmiServer", chatServeur);
		System.out.println("Serveur d�marr� !");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}