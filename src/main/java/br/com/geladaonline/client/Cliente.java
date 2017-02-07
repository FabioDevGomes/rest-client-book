package br.com.geladaonline.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.geladaonline.model.Cerveja;
import br.com.geladaonline.model.rest.Cervejas;

public class Cliente {

	public static void main(String[] args) {
		Cerveja cervejaInserir = new Cerveja("teste 55", "teste 2", "teste 2", Cerveja.Tipo.INDIAN_PALE_ALE);
		Response response = criarCerveja(cervejaInserir);
		
		if(response.getStatus() == Response.Status.CREATED.getStatusCode()){
			Link link = Link.fromUri(response.getLocation()).build();
			String cervejaCriada = ClientBuilder.newClient().invocation(link)
			.accept(MediaType.APPLICATION_XML)
			.get(String.class);
			
			System.out.println(cervejaCriada);
		}
		
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
	
	private static Response criarCerveja(Cerveja cerveja){
		Response response = ClientBuilder.newClient()
				.target(Constantes.HOST)
				.path("cervejas")
				.request("application/xml")
				.post(Entity.xml(cerveja));

		return response;
	}
	
}
