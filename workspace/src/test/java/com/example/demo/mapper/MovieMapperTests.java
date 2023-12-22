package com.example.demo.mapper;



import com.example.demo.domain.dto.MovieDTO;
import com.example.demo.domain.paging.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class MovieMapperTests {
	
	@Autowired
	private MovieMapper movieMapper;

	//조회테스트
	@Test
	public void selectTest() {
		MovieDTO movie = movieMapper.select(2);
		System.out.println("조회 정보 : " + movie);
	}
	
	//영화등록테스트
	@Test
	public void insertTest() {
		
		MovieDTO movie = new MovieDTO();
		
		movie.setMovieName("테스트네임2");
		movie.setMovieTime("테스트시간2");
		movie.setMovieInfo("테스트인포2");
		movie.setMovieType("테스트액션2");
		movie.setMovieDic("테스트감독2");
		movie.setMovieAct("테스트배우2");
		movie.setMovieRelease("테스트개봉날짜2");
		
		movieMapper.insert(movie);
	}

//	@Test
//	public void selectAllTest(){
//
//		Criteria criteria = new Criteria();
//		List<MovieDTO> list = movieMapper.selectAll(criteria);
//
//		log.info(list.toString());
//	}

//	//영화페이징테스트
//	@Test
//	public void moviePagingTest(){
//
//		Criteria criteria = new Criteria();
//		criteria.setSkip(1);
//		criteria.setAmount(10);
//
//		List<MovieDTO> movies = movieMapper.moviePaging();
//		Assertions.assertNotNull(movies);
//		Assertions.assertEquals(10, movies.size());
//
//		for(MovieDTO movie : movies) {
//            Assertions.assertNotNull(movie.getMovieName());
//            Assertions.assertNotNull(movie.getMovieTime());
//		}
//
//		System.out.println(movies);
//
//
//
//	}
}
