package br.com.lojavirtual.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lojavirtual.exceptions.ErroException;
import br.com.lojavirtual.model.Products;
import br.com.lojavirtual.service.ProductsService;

import javax.validation.Valid;

@RestController
@RequestMapping("virtualstore/products")
public class ProductsController {
	
	@Autowired
    private ProductsService productsService;

    /**
     * @param body
     * @return
     */
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Products> singUpProduct(@RequestBody @Valid Products body) {
    	
    	if(body.getIdProduct() != null) {
    		throw new ErroException("Inconsistent request");
    		
    	}
    	    	
    	final Products response = productsService.save(body);
    	
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{idProduct}").buildAndExpand(body.getIdProduct()).toUri();
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        
        return new ResponseEntity<>(response,responseHeaders,HttpStatus.OK);    
    }


    /**
     * @param idProduct
     * @return
     */
    @RequestMapping(value = "/{idProduct}", method = RequestMethod.PUT)
    public ResponseEntity<Products> updateProduct(@RequestBody Products body, @PathVariable Long idProduct) {

        if(body.getIdProduct() == null || !idProduct.equals(body.getIdProduct())) {
            throw new ErroException("Inconsistent request");

        }

        body.setIdProduct(idProduct);
        final Products response = productsService.save(body);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param idProduct
     * @return
     */
    @RequestMapping(value = "/{idProduct}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long idProduct) {

        productsService.delete(idProduct);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * @param idProduct
     * @return
     */
    @RequestMapping(value = "/{idProduct}", method = RequestMethod.GET)
    public ResponseEntity<Products> findProduct(@PathVariable Long idProduct) {

        final Products response = productsService.findById(idProduct);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    /**
     * @param productNumber
     * @param productBrand
     * @param productName
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Products>> listProduct(@RequestParam (value = "productNumber", required = false) String productNumber,
                                                                     @RequestParam (value = "productBrand", required = false) String productBrand,
                                                                     @RequestParam (value = "productName", required = false) String productName) {

        List<Products> listResponse = null;

        if(StringUtils.hasText(productNumber) || StringUtils.hasText(productBrand) || StringUtils.hasText(productName)) {

            listResponse = productsService.findByFilter(productNumber, productBrand, productName);
        } else {

            listResponse = productsService.findAll();
        }

        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }

}
