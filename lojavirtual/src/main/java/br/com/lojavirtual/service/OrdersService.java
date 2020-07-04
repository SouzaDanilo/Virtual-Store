package br.com.lojavirtual.service;

import br.com.lojavirtual.exceptions.ErroException;
import br.com.lojavirtual.model.Orders;
import br.com.lojavirtual.repository.OrdersRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    /**
     * @param id
     * @return
     */
    public Orders findById(Long id) {

        Optional<Orders> obj = ordersRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " +id, "Tipo: " + Orders.class.getName()));

    }

    /**
     * @param body
     * @return
     */
    public Orders save(Orders body) {

        if(body.getIdOrder() != null && ordersRepository.findById(body.getIdOrder()) == null ) {
            throw new ErroException("Attempted to update a non-existing record");

        }

        validateObligatoryFields(body);

        return ordersRepository.save(body);
    }


    /**
     * @param id
     */
    public void delete(Long id) {

        try {
            ordersRepository.deleteById(id);
        }
        catch (final EmptyResultDataAccessException e) {
            throw new ErroException("Attempted to remove a non-existing record");
        }

    }

    /**
     * @return
     */
    public List<Orders> findAll() {

        return ordersRepository.findAll();
    }


    /**
     * @param requestNumber
     * @param entryDate
     * @param customerCpf
     * @return
     */
    public List<Orders> findByFilter(String requestNumber, String entryDate, String customerCpf){

        final List <Specification<Orders>> specifications = new ArrayList<>();

        if(StringUtils.hasText(requestNumber)) {
            specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("requestNumber")), requestNumber));
        }

        if(StringUtils.hasText(entryDate)) {
            specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("entryDate")), entryDate));
        }

        if(StringUtils.hasText(customerCpf)) {
            specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("customerCpf")), customerCpf));
        }

        Specification<Orders> completeQuery = null;

        for(final Specification<Orders> specification : specifications) {

            if(completeQuery == null) {
                completeQuery = specification;

            } else {

                completeQuery = Specification.where(completeQuery).and(specification);
            }
        }
        return ordersRepository.findAll(completeQuery);

    }


    /**
     * @param order
     */
    public void validateObligatoryFields(Orders order) {

        if (StringUtils.isEmpty(order.getRequestNumber())) {
            throw new ErroException("the request number field is empty");
        }

        if (StringUtils.isEmpty(order.getBillingName())) {
            throw new ErroException("the billing name field is empty");
        }

        if (StringUtils.isEmpty(order.getDeliveryTime())) {
            throw new ErroException("the delivery time field is empty");
        }

        if (StringUtils.isEmpty(order.getFreightValue())) {
            throw new ErroException("the freight value field is empty");
        }

        if (StringUtils.isEmpty(order.getOrderValue())) {
            throw new ErroException("the order value field is empty");
        }

        if (StringUtils.isEmpty(order.getPaymentConfirmationDate())) {
            throw new ErroException("the payment confirmation date field is empty");
        }

    }

}
