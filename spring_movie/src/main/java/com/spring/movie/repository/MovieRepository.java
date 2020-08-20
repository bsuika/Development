/**
 * 
 */
package com.spring.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.movie.dtos.TblMoviesInfo;

/**
 * @author suika
 *
 */
public interface MovieRepository extends JpaRepository<TblMoviesInfo, Integer> {

}
