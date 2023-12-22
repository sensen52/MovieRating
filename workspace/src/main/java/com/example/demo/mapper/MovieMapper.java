package com.example.demo.mapper;


import com.example.demo.domain.dto.MovieDTO;
import com.example.demo.domain.dto.Search;
import com.example.demo.domain.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MovieMapper {
	
	//영화 검색(단건)
	public MovieDTO select(int movieId);

	//영화 검색(전체 리스트)
	public List<MovieDTO> selectAll(Criteria criteria);

	// 영화총갯수
	public int getTotal();
	
	//영화 추가
	public void insert(MovieDTO movieDTO);
	
	//영화 수정
	public void update(MovieDTO movieDTO);
	
	//영화 삭제
	public void delete(int movieId);

	//페이징처리
	public List<MovieDTO> moviePaging(Criteria criteria);

	//영화장르별리스트조회
	public List<MovieDTO> getListByGenre(@Param("cri")Criteria criteria, @Param("search") Search search,@Param("movietype") String movietype);

	//영화목록조회(검색추가)(영화평점순)
	public List<MovieDTO> getList(@Param("cri")Criteria criteria, @Param("search") Search search);

	//영화목록조회(검색추가)(가나다순)
	public List<MovieDTO> getList2(@Param("cri")Criteria criteria, @Param("search") Search search);

	//영화목록조회(검색추가)(신작순)
	public List<MovieDTO> getList3(@Param("cri")Criteria criteria, @Param("search") Search search);

	//목록페이징(검색추가)
	public List<MovieDTO> getListPaging(@Param("cri")Criteria criteria, @Param("search") Search search);


	public int getTotalByGenre(@Param("search") Search search, @Param("movietype") String movietype);
	//영화 검색 갯수
	public int searchCountAll(@Param("search")Search search);

}
