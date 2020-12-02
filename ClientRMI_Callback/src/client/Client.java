package client;


import serveur.ServeurIntf;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements ClientListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String identifiant;
	
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	protected Client() throws RemoteException{
		
	}
	@Override
    public void newMessage(String message) throws RemoteException
    {
		System.out.println(message);
    }
	
	
	//faire un thread et listening thread
	public static void main(String args[]) throws Exception {
		 try{
			 ServeurIntf Serveur= (ServeurIntf) Naming.lookup("//localhost/RmiServer");
				System.out.println("Connexion réussis !\nTapez quit! pour quitter");
				
		Client ClientAffichage= new Client();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Entrez un identifiant\n");
		
		while (true) {
		      
	        String id =sc.nextLine();
	        
	        if (!id.isEmpty()) 
	        //cela permet d'éviter d'avoir une erreur de frappe si l'utilisateur appuye juste sur entrée
	        	{
	        	ClientAffichage.setIdentifiant(id);
	        
	        break;
	        }
	        else {
	        	System.out.println("Saisissez un identifiant valide");
	        }
	        }
		
		Serveur.addClientListener(ClientAffichage);
		String msg = "";
		while(!msg.equals("quit!")) {
			msg = sc.nextLine();
			if(!msg.equals("") && !msg.equals("quit!"))
				Serveur.sendMessage(msg, ClientAffichage.getIdentifiant());
		}
		sc.close();
		Serveur.removeClientListener(ClientAffichage);
		System.exit(0);
	        }
		 catch (RemoteException e) {
				System.out.println(e);
			}
		
    }
	
	
}
