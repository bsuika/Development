/**
 * 
 */
package com.spring.movie;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.util.Collections.singletonList;
import com.spring.movie.dtos.TblMoviesInfo;
import com.spring.movie.restcontroller.MovieRestController;
import com.spring.movie.service.MovieService;

/**
 * @author suika
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = MovieRestController.class)
@WithMockUser
public class MovieControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MovieService movieService;
	
	
	/**
	 * @author suika
	 * @implNote Api test case for fetching movie details by id 
	 */
	@Test
	public void retriveMovieInfo() throws Exception {
		
		TblMoviesInfo tb = new TblMoviesInfo();
		tb.setId(8);
		tb.setCategory("hollywood");
		tb.setTitle("English");
		tb.setRating(1);
		Mockito.when(
				movieService.retriveMovieInfo(Mockito.anyInt())).thenReturn(tb);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/get_movie_by_Id/8").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("op "+result.getResponse().getContentAsString());
		String expected = "{\"data\":{\"id\":8,\"category\":\"hollywood\",\"title\":\"English\",\"rating\":1.0},\"message\":\"success\",\"status\":\"1\"}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), true);
	}
	
	
	/**
	 * @author suika
	 * @implNote Api test case for adding movie details
	 */
	String movieJson = "{\"data\":{\"id\":8,\"category\":\"hollywood\",\"title\":\"English\",\"rating\":1.0,\"status\":true,\"addedDate\":\"19-08-2020 17:08:37\"},\"message\":\"success\",\"status\":\"1\"}";
	
	@Test
	public void addMovieInfo() throws Exception {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		TblMoviesInfo tb = new TblMoviesInfo();		
		Mockito.when(
				movieService.addMovieInfo(Mockito.any(TblMoviesInfo.class))).thenReturn(tb);		
				RequestBuilder requestBuilder = MockMvcRequestBuilders
						.post("/api/add_movie_info/")
						.param("title", "dfjf")
						.param("category", "dfndf")
						.param("rating","1.0")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED);				
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				int status = result.getResponse().getStatus();
				 assertEquals(200, status);
	}
	
	/**
	 * @author suika
	 * @implNote Api test case for fetching movies details
	 */
	@Test
	public void retriveAllMovieInfo() throws Exception {
		
		TblMoviesInfo tb = new TblMoviesInfo();
		tb.setId(8);
		tb.setCategory("hollywood");
		tb.setTitle("English");
		tb.setRating(1);
		tb.setStatus(true);
		List<TblMoviesInfo> moviesInfo = singletonList(tb);		
		Mockito.when(
				movieService.retriveAllMovieInfo()).thenReturn(moviesInfo);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/get_movies_list").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("op "+result.getResponse().getContentAsString());
		String expected = "{\"data\":[{\"id\":8,\"category\":\"hollywood\",\"title\":\"English\",\"rating\":1.0}],\"message\":\"success\",\"status\":\"1\"}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), true);
	}
	
	
	
	/**
	 * @author suika
	 * @implNote Api test case for removing movie details by id 
	 */
	@Test
	public void deleteProduct() throws Exception {		
		Mockito.when(
				movieService.deleteMovieInfo(Mockito.anyInt())).thenReturn("sucess");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/delete_movie/11").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	   int status = result.getResponse().getStatus();
	   assertEquals(200, status);
	}
	
	
	/**
	 * @author suika
	 * @implNote Api test case for updating movie details by id 
	 */
	@Test
	public void updateMovieInfo() throws Exception {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		TblMoviesInfo tb = new TblMoviesInfo();
		tb.setId(6);
		tb.setTitle("Raat");
		tb.setCategory("Hollywood");		
		tb.setRating(1.0);
		tb.setStatus(true);	
		tb.setAddedDate(ts);
		Mockito.when(
				movieService.addMovieInfo(Mockito.any(TblMoviesInfo.class))).thenReturn(tb);
				RequestBuilder requestBuilder = MockMvcRequestBuilders
						.post("/api/add_movie_info/")
						.param("movieId", "6")
						.param("title", "Virana")
						.param("category", "Horror")
						.param("rating","4.0")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED);				
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				int status = result.getResponse().getStatus();
				 assertEquals(200, status);
	}
	
}
