package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import com.qa.choonz.exception.PlaylistNotFoundException;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class PlaylistService {

    private PlaylistRepository repo;
    private ModelMapper mapper;

    public PlaylistService(PlaylistRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private PlaylistDTO mapToDTO(Playlist playlist) {
        return this.mapper.map(playlist, PlaylistDTO.class);
    }

    public PlaylistDTO create(Playlist playlist) {
			try{
        Playlist created = this.repo.save(playlist);
        return this.mapToDTO(created);
			} catch(ConstraintViolationException e){
				return null;
			}
    }

    public List<PlaylistDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public PlaylistDTO read(long id) {
        Playlist found = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        return this.mapToDTO(found);
    }

    public PlaylistDTO update(Playlist playlist, long id) {
        Playlist toUpdate = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        toUpdate.setName(playlist.getName());
        toUpdate.setDescription(playlist.getDescription());
        toUpdate.setArtwork(playlist.getArtwork());
        // toUpdate.setTracks(playlist.getTracks());
				try{
					Playlist updated = this.repo.save(toUpdate);
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
