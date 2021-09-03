package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import com.qa.choonz.exception.GenreNotFoundException;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.GenreDTO;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class GenreService {

    private GenreRepository repo;
    private ModelMapper mapper;

    public GenreService(GenreRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private GenreDTO mapToDTO(Genre genre) {
        return this.mapper.map(genre, GenreDTO.class);
    }

    public GenreDTO create(Genre genre) {
			try{
        Genre created = this.repo.save(genre);
        return this.mapToDTO(created);
			} catch(ConstraintViolationException e){
				return null;
			}
    }

    public List<GenreDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public GenreDTO read(long id) {
        Genre found = this.repo.findById(id).orElseThrow(GenreNotFoundException::new);
        return this.mapToDTO(found);
    }

    public GenreDTO update(Genre genre, long id) {
        Genre toUpdate = this.repo.findById(id).orElseThrow(GenreNotFoundException::new);
				toUpdate.setName(genre.getName());
				toUpdate.setDescription(genre.getDescription());
				try{
					Genre updated = this.repo.save(toUpdate);
					return this.mapToDTO(updated);
				} catch(TransactionSystemException e){
					return null;
				}
    }

    public Boolean delete(long id) {
			try{
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
			} catch(EmptyResultDataAccessException e){
				return null;
			}
    }

}
