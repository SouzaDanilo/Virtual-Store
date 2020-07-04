package br.com.lojavirtual.service;

import br.com.lojavirtual.exceptions.ErroException;
import br.com.lojavirtual.model.Products;
import br.com.lojavirtual.repository.ProductsRepository;
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
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;


    /**
     * @param id
     * @return
     */
    public Products findById(Long id) {

        Optional<Products> obj = productsRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " +id, "Tipo: " + Products.class.getName()));

    }

    /**
     * @param body
     * @return
     */
    public Products save(Products body) {

        if(body.getIdProduct() != null && productsRepository.findById(body.getIdProduct()) == null ) {
            throw new ErroException("Attempted to update a non-existing record");

        }

        validateObligatoryFields(body);

        return productsRepository.save(body);
    }


    /**
     * @param id
     */
    public void delete(Long id) {

        try {
            productsRepository.deleteById(id);
        }
        catch (final EmptyResultDataAccessException e) {
            throw new ErroException("Attempted to remove a non-existing record");
        }

    }

    /**
     * @return
     */
    public List<Products> findAll() {

        return productsRepository.findAll();
    }


    /**
     * @param productNumber
     * @param productBrand
     * @param productName
     * @return
     */
    public List<Products> findByFilter(String productNumber, String productBrand, String productName){

        final List <Specification<Products>> specifications = new ArrayList<>();

        if(StringUtils.hasText(productNumber)) {
            specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("productNumber")), productNumber));
        }

        if(StringUtils.hasText(productBrand)) {
            specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("productBrand")), productBrand));
        }

        if(StringUtils.hasText(productName)) {
            specifications.add((root, query, cb) -> cb.equal(cb.upper(root.get("productName")), productName));
        }

        Specification<Products> completeQuery = null;

        for(final Specification<Products> specification : specifications) {

            if(completeQuery == null) {
                completeQuery = specification;

            } else {
                completeQuery = Specification.where(completeQuery).and(specification);

            }
        }
        return productsRepository.findAll(completeQuery);

    }


    /**
     * @param product
     */
    public void validateObligatoryFields(Products product) {

        if (StringUtils.isEmpty(product.getProductNumber())) {
            throw new ErroException("the Product number field is empty");
        }

        if (StringUtils.isEmpty(product.getProductBrand())) {
            throw new ErroException("the Product brand field is empty");
        }

        if (StringUtils.isEmpty(product.getProductName())) {
            throw new ErroException("the Product name field is empty");
        }

        if (StringUtils.isEmpty(product.getUnitaryValue())) {
            throw new ErroException("the unitary value field is empty");
        }
    }

}
