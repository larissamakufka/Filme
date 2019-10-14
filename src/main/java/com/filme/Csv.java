package com.filme;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.filme.entity.Filme;
import com.filme.repository.FilmeRepository;

@Component
public class Csv {

	@Autowired
	private FilmeRepository filmeRepository;

	public Csv() {
	}

	public void run() {

		String arquivoCSV = getClass().getClassLoader().getResource("movielist.csv").getFile();
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ";";
		try {
			FileReader fr = new FileReader(arquivoCSV);
			br = new BufferedReader(fr);
			br.readLine();
			while ((linha = br.readLine()) != null) {

				String[] filmeVector = new String[4];
				filmeVector = linha.split(csvDivisor);

				String winner = "no";
				if (filmeVector.length == 5) {
					winner = filmeVector[4];
				}

				Filme filme = new Filme();
				filme.setYear(Integer.parseInt(filmeVector[0]));
				filme.setTitle(filmeVector[1]);
				filme.setStudios(filmeVector[2]);
				
				String[] producersVector = filmeVector[3].split(",");				
				for (String p : producersVector) {
					filme.addProducers(p);
				}
								
				filme.setWinner(winner == "yes");
				filmeRepository.save(filme);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
