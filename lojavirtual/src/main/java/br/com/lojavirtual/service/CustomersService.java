package br.com.lojavirtual.service;

import br.com.lojavirtual.exceptions.ErroException;
import br.com.lojavirtual.model.Customers;
import br.com.lojavirtual.repository.CustormersRepository;
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
public class CustomersService {

    @Autowired
    private CustormersRepository custormersRepository;

    /**
     * @param id
     * @return
     */
    public Customers findById(Long id) {

        Optional<Customers> obj = custormersRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " +id, "Type: " + Customers.class.getName()));

    }

    /**
     * @param body
     * @return
     */
    public Customers save(Customers body) {

        if(body.getIdCostumer() != null && custormersRepository.findById(body.getIdCostumer()) == null ) {
            throw new ErroException("Attempt to update a non-existing record");

        }

        validateObligatoryFields(body);
        return custormersRepository.save(body);
    }


    /**
     * @param id
     */
    public void delete(Long id) {

        try {
            custormersRepository.deleteById(id);
        }
        catch (final EmptyResultDataAccessException e) {
            throw new ErroException("Attempted to remove a non-existing record");
        }
    }

    /**
     * @return
     */
    public List<Customers> findAll() {

        return custormersRepository.findAll();
    }


    /**
     * @param customerName
     * @param customerEmail
     * @param customerCpf
     * @return
     */
    public List<Customers> findByFilter(String customerName, String customerEmail, String customerCpf){

        final List <Specification<Customers>> specifications = new ArrayList<>();

        if(StringUtils.hasText(customerName)) {
            specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("customerName")), customerName));
        }

        if(StringUtils.hasText(customerEmail)) {
            specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("customerEmail")), customerEmail));
        }

        if(StringUtils.hasText(customerCpf)) {
            specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("customerCpf")), customerCpf));
        }

        Specification<Customers> completeQuery = null;

        for(final Specification<Customers> specification : specifications) {

            if(completeQuery == null) {
                completeQuery = specification;

            } else {
                completeQuery = Specification.where(completeQuery).and(specification);

            }
        }
        return custormersRepository.findAll(completeQuery);

    }


    /**
     * @param customer
     */
    public void validateObligatoryFields(Customers customer) {

        if (StringUtils.isEmpty(customer.getCustomerName())) {
            throw new ErroException("the customer name field is empty");
        }

        if (StringUtils.isEmpty(customer.getCustomerCpf())) {
            throw new ErroException("the customer cpf field is empty");
        }

        if (StringUtils.isEmpty(customer.getCustomerEmail())) {
            throw new ErroException("the customer email field is empty");
        }

        if (StringUtils.isEmpty(customer.getCustomerAge())) {
            throw new ErroException("the customer age field is empty");
        }

        if (StringUtils.isEmpty(customer.getCustomerPhone())) {
            throw new ErroException("the customer phone field is empty");
        }
    }

}
