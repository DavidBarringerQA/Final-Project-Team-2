package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class TrackService {

    private TrackRepository repo;
    private ModelMapper mapper;

    public TrackService(TrackRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private TrackDTO mapToDTO(Track track) {
        return this.mapper.map(track, TrackDTO.class);
    }

    public TrackDTO create(Track track) {
			try{
        Track created = this.repo.save(track);
        return this.mapToDTO(created);
			} catch (ConstraintViolationException e){
				return null;
			}
    }

    public List<TrackDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TrackDTO read(long id) {
        Track found = this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
        return this.mapToDTO(found);
    }

    public TrackDTO update(Track track, long id) {
        Track toUpdate = this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
        toUpdate.setName(track.getName());
        toUpdate.setAlbum(track.getAlbum());
        toUpdate.setDuration(track.getDuration());
        toUpdate.setLyrics(track.getLyrics());
        toUpdate.setPlaylist(track.getPlaylist());
				try{
					Track updated = this.repo.save(toUpdate);
					return this.mapToDTO(updated);
				} catch (TransactionSystemException e){
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
