package br.com.lojavirtual.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lojavirtual.model.Products;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>{

    List<Products> findAll(Specification<Products> completeQuery);

}
