package org.acme.quarkus.sample;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.quarkus.dto.ProdutoDTO;	

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {
	
	@GET
	public List<Produto> findAll(){
		return Produto.listAll();
	}
	
	@POST
	@Transactional
	public void create(ProdutoDTO dto) {
		Produto p = new Produto();
		p.name = dto.name;
		p.value= dto.value;
		p.persist(p);
	}
	
	@PUT
	@Path("{id}")
	@Transactional
	public void update(@PathParam("id") Long id, ProdutoDTO dto) {
		Optional<Produto> produtoOp = Produto.findByIdOptional(id);
		
		if(produtoOp.isPresent()) {
			
			Produto p = produtoOp.get();
			p.name = dto.name;
			p.value= dto.value;
			p.persist(p);
		}else {
			throw new NotFoundException();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Transactional
	public void update(@PathParam("id") Long id) {
		Optional<Produto> produtoOp = Produto.findByIdOptional(id);
		produtoOp.ifPresentOrElse(Produto::delete, ()->{
			throw new NotFoundException();
		});
	}

}
