package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import com.qa.choonz.exception.AlbumNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class AlbumService {

    private AlbumRepository repo;
    private ModelMapper mapper;

    public AlbumService(AlbumRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private AlbumDTO mapToDTO(Album album) {
        return this.mapper.map(album, AlbumDTO.class);
    }

    public AlbumDTO create(Album album) {
			try{
        Album created = this.repo.save(album);
        return this.mapToDTO(created);
			} catch (ConstraintViolationException e){
				return null;
			}
    }

    public List<AlbumDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public AlbumDTO read(long id) {
        Album found = this.repo.findById(id).orElseThrow(AlbumNotFoundException::new);
        return this.mapToDTO(found);
    }

    public AlbumDTO update(Album album, long id) {
        Album toUpdate = this.repo.findById(id).orElseThrow(AlbumNotFoundException::new);
        toUpdate.setName(album.getName());
        // toUpdate.setTracks(album.getTracks());
        toUpdate.setArtist(album.getArtist());
				toUpdate.setGenre(album.getGenre());
        toUpdate.setCover(album.getCover());
				try{
					Album updated = this.repo.save(toUpdate);
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
