package com.filme.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.filme.DTO.PiorFilme;
import com.filme.entity.Filme;
import com.filme.repository.FilmeRepositoryCustom;

public class FilmeImpl implements FilmeRepositoryCustom {

	EntityManager em;
	
	@Override
	public List<Filme> FindMaiorIntervalo() {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Filme> cq = cb.createQuery(Filme.class);
	 
	    Root<Filme> book = cq.from(Filme.class);
	    List<Predicate> predicates = new ArrayList<>();
	    
	    predicates.add(cb.like(book.get("winner"), "yes"));
	    
	    cq.where(predicates.toArray(new Predicate[0]));
	 
	    return em.createQuery(cq).getResultList();
	}

	@Override
	public List<Filme> findWinners() {
		List<Filme> winners = findWinners();
		
		List<Filme> filmesReturn = new ArrayList<>();
		List<PiorFilme> piorFilme = new ArrayList<>();
		for (Filme filme : filmesReturn) {
			PiorFilme pior = null;
			for (PiorFilme pf : piorFilme) {
				for (String producr : filme.getProducers()) {
					if (pf.getProducer().equals(producr)) {
						pior = pf;
						break;
					}
				}
			}
			if (piorFilme..contains(filme.getProducers())) {
				
			}
		}
		
		return filmesReturn;
	}

}
