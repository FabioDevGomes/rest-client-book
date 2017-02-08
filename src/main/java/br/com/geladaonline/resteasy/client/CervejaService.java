package br.com.geladaonline.resteasy.client;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.geladaonline.model.Cerveja;
import br.com.geladaonline.model.rest.Cervejas;

@Path("/cervejas")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public interface CervejaService {

	@GET
	@Path("{nome}")
	public Cerveja encontarCerveja(@PathParam("nome") String nome);

	@GET
	public Cervejas listarTodasAsCervejas(@QueryParam("pagina") int pagina);

	@POST
	public Response criarCerveja(Cerveja cerveja);
	
	@PUT
	@Path("{nome}")
	public void atualizarCerveja(@PathParam("nome") String nome, Cerveja cerveja);

	@DELETE
	@Path("{nome}")
	public void apagarCerveja(@PathParam("nome") String nome);
	
	@GET
	@Path("{nome}")
	@Produces("image/*")
	public Response recuperarImagem(@PathParam("nome") String nomeCerveja);
	
	@POST
	@Path("{nome}")
	@Consumes("image/*")
	public Response criarImagem(@PathParam("nome") String nomeImagemm, 
			@Context HttpServletRequest req, byte[] dados);
}
