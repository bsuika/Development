package com.spring.movie.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.spring.movie.dtos.TblMoviesInfo;
import com.spring.movie.guibean.MoviesGuiBean;
import com.spring.movie.repository.MovieRepository;
import com.spring.movie.restcontroller.helper.MovieRestControllerHelper;
import com.spring.movie.service.MovieService;

/**
 * @author suika
 *
 */
@RestController
@RequestMapping("/api")
public class MovieRestController {
	
		
	@Autowired
	MovieService movieService;
	
	/**
	 * This API is used for creating new movie records. This function is used for
	 * saving a movie details into a database.
	 * @param title
	 * @param projectId
	 * @param productId
	 * @param userId
	 * @return
	 * @throws ParseException
	 */	
	@RequestMapping(value = "/add_movie_info", method = RequestMethod.POST)
	public ResponseEntity<?> addMovieInfo(@RequestParam(value = "title") String title,
			@RequestParam(value = "category") String category,
			@RequestParam(value = "rating") String rating) throws ParseException {	
	
		double str1 = Double.valueOf(rating);
		Map<String, Object> jsonData = new HashMap<String, Object>();		
		MovieRestControllerHelper movieRestControllerHelper = new MovieRestControllerHelper(); 		
		TblMoviesInfo moviesRecords = movieRestControllerHelper.saveMoviesData(title,category,str1,movieService);		
		if(moviesRecords!=null)
		{
		jsonData.put("status", "1");
		jsonData.put("message", "Records Save Successfully!");
		jsonData.put("data", moviesRecords);		
		}
		else
		{
			jsonData.put("status", "0");
			jsonData.put("message", "Something went wrong!");		
		}
		return new ResponseEntity<>(jsonData, HttpStatus.OK);
	}
	
	
	
	/**
	 * This web service is use to give a list of all movies data
	 * @author suika
	 * @return
	 */
	@RequestMapping(value = "/get_movies_list", method = RequestMethod.GET)
	public ResponseEntity<?> getMovieList() {
		Map<String, Object> jsonData = new HashMap<String, Object>();
		MovieRestControllerHelper movieRestControllerHelper = new MovieRestControllerHelper(); 
		List<MoviesGuiBean> moviesGuiBean = movieRestControllerHelper.listofMovies(movieService);
		if (moviesGuiBean.size() >= 0) {
			jsonData.put("status", "1");
			jsonData.put("message", "success");
			jsonData.put("data", moviesGuiBean);
		} else {
			jsonData.put("status", "0");
			jsonData.put("message", "Something is wrong!!");
		}
		return new ResponseEntity<>(jsonData, HttpStatus.OK);

	}


	/**
	 * @author suika
	 * @param movieId
	 * @param title
	 * @param category 
	 * @param rating 
	 * @return
	 */	
	@RequestMapping(value = "/update_movie", method = RequestMethod.POST)
	public ResponseEntity<?> updateMovie(@RequestParam(value = "movieId") Integer movieId,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "category") String category,
			@RequestParam(value = "rating") double rating) throws ParseException {
		
		Map<String, Object> jsonData = new HashMap<String, Object>();	
		try {
		TblMoviesInfo tblMoviesInfo = movieService.retriveMovieInfo(movieId);
		MovieRestControllerHelper movieRestControllerHelper = new MovieRestControllerHelper(); 
         if(tblMoviesInfo!=null) {        	 
		TblMoviesInfo movieInfo = movieRestControllerHelper.updateOrganizationInfo(tblMoviesInfo,title,category,rating,movieService);        
		if (movieInfo != null) {		
			jsonData.put("status", "1");
			jsonData.put("message", "Updated Successfully");
		    jsonData.put("data", movieInfo);
		} else {
			jsonData.put("status", "0");
			jsonData.put("message", "Something is wrong!");
		}
        }else {
        	jsonData.put("status", "0");
			jsonData.put("message", "Invalid MovieId!");
        }
		}
		catch(Exception e)
		{
			jsonData.put("status", "0");
			jsonData.put("message", "Invalid MovieId!");
		}
		return new ResponseEntity<>(jsonData, HttpStatus.OK);

	}
	
	
	
	
	
	/**
	 * This web service is use to delete a movie data
	 * @author suika
	 * @param movieId
	 * @throws ParseException
	 */
	@GetMapping("/delete_movie/{movieId}")
	public ResponseEntity<?> deleteMovie(@PathVariable(value = "movieId") int movieId) throws ParseException {	
		Map<String, Object> jsonData = new HashMap<String, Object>();
		try {		      	 
			 String deleteMovieInfo = movieService.deleteMovieInfo(movieId); 
			 if(deleteMovieInfo!=null) {
			 jsonData.put("status", "1");
			 jsonData.put("message", "Data deleted Successfully!");
			 }else {
				 jsonData.put("status", "0");
				 jsonData.put("message", "Something went wrong!"); 
			 }
		}
		catch(Exception e)
		{
			jsonData.put("status", "0");
			jsonData.put("message", "Invalid MovieId!");
		}
		return new ResponseEntity<>(jsonData, HttpStatus.OK);
	}
	

	
	/**
	 * This web service is use to fetch data by movieId
	 * @author suika
	 * @return
	 */
	@GetMapping("/get_movie_by_Id/{movieId}")
	public ResponseEntity<?> getMovietListById(@PathVariable(value = "movieId") int movieId) {
		Map<String, Object> jsonData = new HashMap<String, Object>();
		try
		{
		MovieRestControllerHelper movieRestControllerHelper = new MovieRestControllerHelper(); 
		MoviesGuiBean tblMoviesInfo = movieRestControllerHelper.listofMovieById(movieService,movieId);
		
		if (tblMoviesInfo.getId() != null) {
			jsonData.put("status", "1");
			jsonData.put("message", "success");
			jsonData.put("data", tblMoviesInfo);
		} else {
			jsonData.put("status", "0");
			jsonData.put("message", "Invalid MovieId!");
		}
		}catch(Exception e)
		{
			jsonData.put("status", "0");
			jsonData.put("message", "Invalid MovieId!");
		}
		return new ResponseEntity<>(jsonData, HttpStatus.OK);

	}



}
