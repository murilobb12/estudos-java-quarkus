package com.letscode.ecommerce.endpoints;

import com.letscode.ecommerce.models.Produtos;
import com.letscode.ecommerce.restClient.FinanceiroRestClient;
import com.letscode.ecommerce.services.ProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class ProdutosEndpoints {

    @Autowired
    ProdutosService produtosService;


    @Operation(description = "Endpoint para acessar todos os produtos")
    @RequestMapping(path = "/produtos", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Produtos>> getAllProducts(){
        List<Produtos> produtosList = produtosService.listarProdutos();

        return ResponseEntity.ok(produtosList);
    }

    @Operation(description = "Endpoint para criar um produto")
    @RequestMapping(path = "/produtos", method = RequestMethod.POST)
    public ResponseEntity criarProduto(@RequestBody Produtos _produtos){
        boolean sucesso = produtosService.novoProduto(_produtos);

        if (sucesso){
            return new ResponseEntity("Produto criado com sucesso!", HttpStatus.CREATED);
        }else {
            return new ResponseEntity("Criação do produto não realizada", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(description = "Endpoint para alterar um produto")
    @RequestMapping(path = "/produtos", method = RequestMethod.PUT)
    public ResponseEntity atualizarProduto(@RequestBody Produtos produtos){
        boolean sucesso = produtosService.atualizarProduto(produtos);

        if(sucesso){
            return new ResponseEntity("Produto atualizado com sucesso!", HttpStatus.CREATED);
        }else {
            return new ResponseEntity("Atualização do produto não foi realizada!", HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(description = "Endpoint para deletar um produto através do ID")
    @RequestMapping(path = "/produtos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removerProduto(@PathVariable long id){
        boolean sucesso = produtosService.removerProduto(id);

        if (sucesso){
            return new ResponseEntity("Produto deletado com sucesso!", HttpStatus.OK);
        }else {
            return new ResponseEntity("Deleção do produto não foi realizada", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(description = "Endpoint para listar um produto usando Named Methods")
    @RequestMapping(path = "/produtos_named/{id}", method = RequestMethod.GET)
    public ResponseEntity listarProdutoNamed(@PathVariable long id){
        Produtos produtos = produtosService.listarProdutoNamed(id);

        return ResponseEntity.ok(produtos);
    }

    @Operation(description = "Endpoint para listar um produto usando JPQL")
    @RequestMapping(path = "/produtos_jpql/{id}", method = RequestMethod.GET)
    public ResponseEntity listarProdutoJPQL(@PathVariable long id){
        Produtos produtos = produtosService.listarProdutoJPQL(id);

        return ResponseEntity.ok(produtos);
    }

    @Operation(description = "Endpoint para listar um produto usando JPA")
    @RequestMapping(path = "/produtos_jpa/{id}", method = RequestMethod.GET)
    public ResponseEntity listarProdutoJPA(@PathVariable long id){
        Produtos produtos = produtosService.listarProdutoJPA(id);

        return ResponseEntity.ok(produtos);
    }


}
