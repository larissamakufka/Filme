package com.filme.repository;

import java.util.List;

import com.filme.entity.Filme;

public interface FilmeRepositoryCustom {
	 List<Filme> findWinners();
	 List<Filme> FindMaiorIntervalo();
}
