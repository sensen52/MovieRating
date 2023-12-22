package com.example.demo.mapper;


import com.example.demo.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

	//회원조회(단건)
	public UserDTO select(int userId);

	@Select("select * from tbl_user where userid=#{userid}")
	public UserDTO selectOne(@Param("userid") String userId);

	//아이디중복조회
	public int idCheckByUserId(String userId);

	//회원가입
	public void userJoin(UserDTO userDTO);

	//회원정보 수정
	public void update(UserDTO userDTO);

	//회원탈퇴
	public void delete(String userId);
	
	
	


}
