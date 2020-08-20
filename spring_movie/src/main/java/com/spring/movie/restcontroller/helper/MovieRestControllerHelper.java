/**
 * 
 */
package com.spring.movie.restcontroller.helper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.spring.movie.dtos.TblMoviesInfo;
import com.spring.movie.guibean.MoviesGuiBean;
import com.spring.movie.repository.MovieRepository;
import com.spring.movie.service.MovieService;

/**
 * @author suika
 *
 */
public class MovieRestControllerHelper {
	

	/**
	 * This function is use to save movie data
	 * @author suika
	 * @param movieService 
	 * @return saved data object
	 */
	public TblMoviesInfo saveMoviesData(String title, String category, double rating, MovieService movieService) {
	   
		TblMoviesInfo tblMoviesInfo = null;
		try {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		TblMoviesInfo movieInfo = new TblMoviesInfo();
		movieInfo.setTitle(title);
		movieInfo.setCategory(category);
		movieInfo.setRating(rating);
		movieInfo.setAddedDate(ts);
		movieInfo.setStatus(true);
		tblMoviesInfo =  movieService.addMovieInfo(movieInfo);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return tblMoviesInfo;
	
	}
	
	/**
	 * This function is use to fetch movies List
	 * @author suika
	 * @return list of movies 
	 */
	public List<MoviesGuiBean> listofMovies(MovieService movieService) {
		List<TblMoviesInfo> tblMoviesInfo = movieService.retriveAllMovieInfo();
		List<MoviesGuiBean> setMoviesGuiBean = new ArrayList<>();
		try {
		if(tblMoviesInfo.size() > 0) {
		for (int i = 0; i < tblMoviesInfo.size(); i++) {
			MoviesGuiBean movieGuiBean = new MoviesGuiBean();
			movieGuiBean.setId(tblMoviesInfo.get(i).getId());
			movieGuiBean.setTitle(tblMoviesInfo.get(i).getTitle());
			movieGuiBean.setCategory(tblMoviesInfo.get(i).getCategory());
			movieGuiBean.setRating(tblMoviesInfo.get(i).getRating());
			setMoviesGuiBean.add(movieGuiBean);
		}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return setMoviesGuiBean;
	}


	/**
	 * This function is use to update movie data
	 * @author suika
	 * @return updated data object
	 */
	public TblMoviesInfo updateOrganizationInfo(TblMoviesInfo tblMoviesInfo, String title, String category,double rating, MovieService movieService) {					
		
		TblMoviesInfo tblMoviesInfoData = null;
			try {
			tblMoviesInfo.setCategory(category);
			tblMoviesInfo.setTitle(title);
			tblMoviesInfo.setRating(rating);
			tblMoviesInfoData = movieService.addMovieInfo(tblMoviesInfo);	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return tblMoviesInfoData;
	}
	
	
	/**
	 * This function is use to fetch movies List
	 * @author suika
	 * @param movieId 
	 * @return list of movies 
	 */
	public MoviesGuiBean listofMovieById(MovieService movieService, int movieId) {
		
		MoviesGuiBean movieGuiBean = new MoviesGuiBean();
		try {
		TblMoviesInfo tblMoviesInfo = movieService.retriveMovieInfo(movieId);
		if(tblMoviesInfo != null) {
			movieGuiBean.setId(tblMoviesInfo.getId());
			movieGuiBean.setTitle(tblMoviesInfo.getTitle());
			movieGuiBean.setCategory(tblMoviesInfo.getCategory());
			movieGuiBean.setRating(tblMoviesInfo.getRating());
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return movieGuiBean;
	}


}
