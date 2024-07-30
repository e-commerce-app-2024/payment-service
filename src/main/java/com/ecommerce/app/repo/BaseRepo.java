package com.ecommerce.app.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepo<E> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {
}
