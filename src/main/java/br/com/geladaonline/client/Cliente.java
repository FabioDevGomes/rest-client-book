package br.com.geladaonline.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import br.com.geladaonline.model.rest.Cervejas;

public class Cliente {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		
		Cervejas cervejas = client.target(Constantes.HOST).path("cervejas").request("application/xml")
		.get(Cervejas.class);
		
		System.out.println(cervejas);
		System.out.println(cervejas.getLinks().size());
		
	}
	
}
