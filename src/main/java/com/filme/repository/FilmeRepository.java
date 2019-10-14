package com.filme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filme.entity.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long>, FilmeRepositoryCustom {

}
