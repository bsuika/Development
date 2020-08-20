/**
 * 
 */
package com.spring.movie.service;

import java.util.List;

import com.spring.movie.dtos.TblMoviesInfo;

/**
 * @author suika
 *
 */
public interface MovieService {
	
	public TblMoviesInfo addMovieInfo(TblMoviesInfo tblMoviesInfo);
    
	public String deleteMovieInfo(int movieId);
	
	public List<TblMoviesInfo> retriveAllMovieInfo();
	
	public TblMoviesInfo retriveMovieInfo(int movieId);
}
