package client;


import serveur.ServeurIntf;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
	static ServeurIntf Serveur;
	
	String identifiant;
	
	
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public Client() throws MalformedURLException, RemoteException, NotBoundException {
		Serveur = (ServeurIntf)Naming.lookup("//localhost/RmiServer");
		
	}
	//faire un thread et listening thread
	public static void main(String args[]) throws Exception {
		
    	Client chatClient=new Client();
    	
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisissez votre identifiant");
        while (true) {
      
        String id =sc.nextLine();
        if (id!=null) {
        	chatClient.setIdentifiant(id);
        
        break;
        }
        else {
        	System.out.println("Saisissez un identifiant valide");
        }
        }
        ClientThread CThread = new ClientThread(Serveur, chatClient);
        CThread.start();

        System.out.println("Merci d'envoyer quit! pour quitter");
        System.out.println("Vous pouvez maintenant tchatter");
        chatClient.Serveur.sendMessage(chatClient.getIdentifiant() +" est connecté");
        while (true) {
        	
        String msg =sc.nextLine();
        if(msg.equals("quit!")) {
            chatClient.Serveur.sendMessage(chatClient.getIdentifiant() +" s'est déconnecté");
                	break;
        }else 
        	chatClient.Serveur.sendMessage(chatClient.getIdentifiant() + " : " +msg);
        }

        sc.close();
		System.exit(0);
    }
}
