package com.filme.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filme.DTO.PiorFilme;
import com.filme.entity.Filme;
import com.filme.repository.FilmeRepository;

@RestController
@RequestMapping("/filme")
public class FilmeController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@GetMapping("/maiorintervalo")
    public List<PiorFilme> Get() {
		List<Filme> winners = findWinners();		
		List<PiorFilme> Filmes = new ArrayList<>();
		List<PiorFilme> toAdd = new ArrayList<>();

		int maiorTempo = 0;
		for (Filme filmeWinners : winners) {
			boolean encontrouFilme = false;
			for (String nomeProducerWinner : filmeWinners.getProducers()) {
				for (PiorFilme pf : Filmes) {
					if (nomeProducerWinner.equals(pf.getProducer())) {
						if (filmeWinners.getYear() > pf.getFollowingWin()) {
							pf.setFollowingWin(filmeWinners.getYear());
						} else if (filmeWinners.getYear() < pf.getPreviousWin()) {
							pf.setPreviousWin(filmeWinners.getYear());
						}
						if (maiorTempo < (pf.getFollowingWin() - pf.getPreviousWin())) {
							maiorTempo = pf.getFollowingWin() - pf.getPreviousWin();
						}
						pf.setInterval(pf.getFollowingWin() - pf.getPreviousWin());
					} else {
						PiorFilme pior = new PiorFilme();
						pior.setProducer(nomeProducerWinner);
						pior.setInterval(0);
						pior.setPreviousWin(filmeWinners.getYear());
						pior.setFollowingWin(filmeWinners.getYear());
						toAdd.add(pior);
					}
					encontrouFilme = true;
				}
			}
			
			if (!encontrouFilme) {
				for (String nomeProducerWinner : filmeWinners.getProducers()) {
					PiorFilme pior = new PiorFilme();
					pior.setProducer(nomeProducerWinner);
					pior.setInterval(0);
					pior.setPreviousWin(filmeWinners.getYear());
					pior.setFollowingWin(filmeWinners.getYear());
					Filmes.add(pior);
				}
			} else {
				for (PiorFilme piorFilme : toAdd) {
					Filmes.add(piorFilme);
				}
			}
		}
		
		int teste = 0 ;
		int teste2 = teste;
		
		return (List<PiorFilme>) new ResponseEntity<PiorFilme>(HttpStatus.OK); 
    }

	public List<Filme> findWinners() {
	    CriteriaBuilder cb = em.getCriteriaBuilder();
	    CriteriaQuery<Filme> cq = cb.createQuery(Filme.class);
	 
	    Root<Filme> book = cq.from(Filme.class);
	    List<Predicate> predicates = new ArrayList<>();
	    
	    predicates.add(cb.isTrue(book.get("winner")));
	    
	    cq.where(predicates.toArray(new Predicate[0]));
	 
	    return em.createQuery(cq).getResultList();
	}

	
    /*@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
    public Filme GetById(@PathVariable(value = "id") long id)
    {
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if(pessoa.isPresent())
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    /*@RequestMapping(value = "/pessoa", method =  RequestMethod.POST)
    public Pessoa Post(@Valid @RequestBody Pessoa pessoa)
    {
        return _pessoaRepository.save(pessoa);
    }*/

    /*@RequestMapping(value = "/pessoa/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Pessoa> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Pessoa newPessoa)
    {
        Optional<Pessoa> oldPessoa = _pessoaRepository.findById(id);
        if(oldPessoa.isPresent()){
            Pessoa pessoa = oldPessoa.get();
            pessoa.setNome(newPessoa.getNome());
            _pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    /*@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if(pessoa.isPresent()){
            _pessoaRepository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
}