package br.com.geladaonline.resteasy.client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Link;

import org.jboss.resteasy.client.jaxrs.ProxyBuilder;

import br.com.geladaonline.model.rest.Cervejas;

public class Cliente {

	public static void main(String[] args) {
		ProxyBuilder<CervejaService> proxy = ProxyBuilder.builder(CervejaService.class, 
				ClientBuilder.newClient().target("http://localhost:8080/rest/services"));
		
		CervejaService cervejaService = proxy.build();
		
		Cervejas cervejas = cervejaService.listarTodasAsCervejas(0);
		
		for (Link link : cervejas.getLinks()) {
			System.out.println(link);
		}
	}
}
