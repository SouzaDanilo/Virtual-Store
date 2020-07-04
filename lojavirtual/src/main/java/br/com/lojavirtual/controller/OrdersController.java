package br.com.lojavirtual.controller;

import br.com.lojavirtual.exceptions.ErroException;
import br.com.lojavirtual.model.Orders;
import br.com.lojavirtual.service.OrdersService;
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
@RequestMapping("virtualstore/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * @param body
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Orders> singUpOrders(@RequestBody @Valid Orders body) {

        if(body.getIdOrder() != null) {
            throw new ErroException("Inconsistent request");

        }

        final Orders response = ordersService.save(body);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{idOrder}").buildAndExpand(body.getIdOrder()).toUri();
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return new ResponseEntity<>(response,responseHeaders, HttpStatus.OK);
    }

    /**
     * @param body
     * @param idOrder
     * @return
     */
    @RequestMapping(value = "/{idOrder}", method = RequestMethod.PUT)
    public ResponseEntity<Orders> updateOrders(@RequestBody Orders body, @PathVariable Long idOrder) {

        if(body.getIdOrder() == null || !idOrder.equals(body.getIdOrder())) {
            throw new ErroException("Inconsistent request");
        }

        body.setIdOrder(idOrder);
        final Orders response = ordersService.save(body);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * @param idOrder
     * @return
     */
    @RequestMapping(value = "/{idOrder}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOrders(@PathVariable Long idOrder) {

        ordersService.delete(idOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * @param idOrder
     * @return
     */
    @RequestMapping(value = "/{idOrder}", method = RequestMethod.GET)
    public ResponseEntity<Orders> findOrders(@PathVariable Long idOrder) {

        final Orders response = ordersService.findById(idOrder);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    /**
     * @param requestNumber
     * @param entryDate
     * @param customerCpf
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> listOrders(@RequestParam (value = "requestNumber", required = false) String requestNumber,
                                                   @RequestParam (value = "entryDate", required = false) String entryDate,
                                                   @RequestParam (value = "customerCpf", required = false) String customerCpf) {

        List<Orders> listResponse = null;

        if(StringUtils.hasText(requestNumber) || StringUtils.hasText(entryDate) || StringUtils.hasText(customerCpf)) {
            listResponse = ordersService.findByFilter(requestNumber, entryDate, customerCpf);

        } else {
            listResponse = ordersService.findAll();
        }

        return new ResponseEntity<>(listResponse, HttpStatus.OK);
    }
}
