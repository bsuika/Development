/**
 * 
 */
package com.spring.movie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.movie.dtos.TblMoviesInfo;
import com.spring.movie.repository.MovieRepository;
import com.spring.movie.service.MovieService;

/**
 * @author suika
 *
 */

@Service
public class MovieServiceImpl implements MovieService {
	

	@Autowired
	MovieRepository movieRepository;
	
	
	/**
	 * @author suika
	 * @implNote For adding movie details
	 */
	
	@Override
	public TblMoviesInfo addMovieInfo(TblMoviesInfo tblMoviesInfo) {		
		return movieRepository.save(tblMoviesInfo);
	}

	

	/**
	 * @author suika
	 * @implNote For removing movie info by id
	 */
	@Override
	public String deleteMovieInfo(int movieId) {		
		 movieRepository.deleteById(movieId);
		return "success";
	}
	

	/**
	 * @author suika
	 * @implNote For fetching all movies list  
	 */
	@Override
	public List<TblMoviesInfo> retriveAllMovieInfo() {		
		return movieRepository.findAll();
	}


/**
 * @author suika
 * @implNote For fetching details by id 
 */
	@Override
	public TblMoviesInfo retriveMovieInfo(int movieId) {		
		return movieRepository.findById(movieId).get();
	}

}
