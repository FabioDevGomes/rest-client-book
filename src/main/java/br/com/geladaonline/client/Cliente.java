package br.com.geladaonline.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;

import br.com.geladaonline.model.Cerveja;
import br.com.geladaonline.model.rest.Cervejas;

public class Cliente {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		
		Cervejas cervejas = client.target(Constantes.HOST).path("cervejas").request("application/xml")
		.get(Cervejas.class);
		
		System.out.println(cervejas);
		System.out.println(cervejas.getLinks().size());
		
		List<Cerveja> cervejasList = new ArrayList<>();
		
		for (Link link : cervejas.getLinks()) {
			String cerveja = ClientBuilder.newClient().invocation(link)
					.accept(MediaType.APPLICATION_XML)
					.get(String.class); //define o tipo de retorno da requisição != MIME Type
			
//			cervejasList.add(cerveja);
			
			System.out.println(cerveja);
		}
		
	}
	
}
