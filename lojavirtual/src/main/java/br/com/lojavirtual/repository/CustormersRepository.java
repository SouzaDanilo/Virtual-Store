package br.com.lojavirtual.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lojavirtual.model.Customers;

import java.util.List;

@Repository
public interface CustormersRepository extends JpaRepository<Customers, Long> {

    List<Customers> findAll(Specification<Customers> completeQuery);

}
