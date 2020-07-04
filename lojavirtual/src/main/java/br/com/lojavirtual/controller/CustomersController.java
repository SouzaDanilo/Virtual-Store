package br.com.lojavirtual.controller;

import br.com.lojavirtual.exceptions.ErroException;
import br.com.lojavirtual.model.Customers;
import br.com.lojavirtual.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("virtualstore/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    /**
     * method responsible for registering the customer
     * @param body
     * @return Customers
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customers> signUpCustomer(@RequestBody @Valid Customers body) {

        if(body.getIdCostumer() != null) {
            throw new ErroException("Inconsistent request");

        }

        final Customers response = customersService.save(body);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{idCostumer}").buildAndExpand(body.getIdCostumer()).toUri();
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return new ResponseEntity<>(response,responseHeaders, HttpStatus.OK);
    }


    /**
     * method responsible for updating the customer
     * @param idCostumer
     * @return Customers
     */
    @RequestMapping(value = "/{idCostumer}", method = RequestMethod.PUT)
    public ResponseEntity<Customers> updateCustomer(@RequestBody Customers body, @PathVariable Long idCostumer) {

        if(body.getIdCostumer() == null || !idCostumer.equals(body.getIdCostumer())) {
            throw new ErroException("Inconsistent request");

        }

        body.setIdCostumer(idCostumer);
        final Customers response = customersService.save(body);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * method responsible for deleting the customer
     * @param idCostumer
     * @return
     */
    @RequestMapping(value = "/{idCostumer}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long idCostumer) {

        customersService.delete(idCostumer);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * method responsible for recovering the customer by IdCustomer
     * @param idCostumer
     * @return Customer
     */
    @RequestMapping(value = "/{idCostumer}", method = RequestMethod.GET)
    public ResponseEntity<Customers> findCustomer(@PathVariable Long idCostumer) {

        final Customers response = customersService.findById(idCostumer);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    /**
     * method responsible for listing the customer
     * @param customerName
     * @param customerEmail
     * @param customerCpf
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customers>> listCustomer(@RequestParam (value = "customerName", required = false) String customerName,
                                                        @RequestParam (value = "customerEmail", required = false) String customerEmail,
                                                        @RequestParam (value = "customerCpf", required = false) String customerCpf) {

        List<Customers> listResponse = null;

        if(StringUtils.hasText(customerName) || StringUtils.hasText(customerEmail) || StringUtils.hasText(customerCpf)) {
            listResponse = customersService.findByFilter(customerName, customerEmail, customerCpf);

        } else {
            listResponse = customersService.findAll();

        }

        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }

}
