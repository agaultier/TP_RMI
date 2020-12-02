package client;

import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.ServeurIntf;

public class ClientThread extends Thread {
	ServeurIntf _serveur;
	Client _client;
	ArrayList<String> l;
	int count= 0;
	int debut = 0;
	public ClientThread(ServeurIntf serveur, Client chatClient) {
		_serveur = serveur;
		_client = chatClient;
	}

	public void run() {
		
		while (true) {
			try {
			
			if (_serveur.countMessage()>count) {//On a accès à tout les messages envoyés avant
					
				for (int i = count; i < _serveur.countMessage(); i++)
					if (!_serveur.getMessage(i).substring(0, _client.getIdentifiant().length()).contains(_client.getIdentifiant()))
					{//permet d'afficher les messages à tout le monde sauf à l'envoyeur, même si il y a l'identifiant dans le contenu du message
					System.out.println(_serveur.getMessage(i));
					}
			
			count= _serveur.countMessage();;
			}
				
			
		} catch (RemoteException e) {
			System.out.println(e);
		}
	}
}
}

