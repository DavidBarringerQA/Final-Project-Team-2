package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import com.qa.choonz.exception.ArtistNotFoundException;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class ArtistService {

    private ArtistRepository repo;
    private ModelMapper mapper;

    public ArtistService(ArtistRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private ArtistDTO mapToDTO(Artist artist) {
        return this.mapper.map(artist, ArtistDTO.class);
    }

    public ArtistDTO create(Artist artist) {
			try{
        Artist created = this.repo.save(artist);
        return this.mapToDTO(created);
			} catch(ConstraintViolationException e){
				return null;
			}
    }

    public List<ArtistDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ArtistDTO read(long id) {
        Artist found = this.repo.findById(id).orElseThrow(ArtistNotFoundException::new);
        return this.mapToDTO(found);
    }

    public ArtistDTO update(Artist artist, long id) {
        Artist toUpdate = this.repo.findById(id).orElseThrow(ArtistNotFoundException::new);
        toUpdate.setName(artist.getName());
        // toUpdate.setAlbums(artist.getAlbums());
				try{
					Artist updated = this.repo.save(toUpdate);
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
