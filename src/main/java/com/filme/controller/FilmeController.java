package com.filme.controller;

import java.util.Optional;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filme.DTO.PiorFilme;
import com.filme.entity.Filme;
import com.filme.repository.FilmeRepository;

@RestController
@RequestMapping("/filme")
public class FilmeController {

	@Autowired
	private FilmeRepository filmeRepository;
	
	@GetMapping("/maiorintervalo")
    public PiorFilme Get() {
		
		for (Filme filme : filmeRepository.FindMaiorIntervalo()) {
			
		}

		Optional<Filme> optionalFilme = filmeRepository.findById(1l);
		if (optionalFilme.isPresent()) {
			Filme filme = optionalFilme.get();
			System.out.println(filme.getTitle());
		} else {
			System.out.println("Não salvou");
		}
        return null;
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