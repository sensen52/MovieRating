package com.example.demo.service;

import com.example.demo.domain.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper usermapper;

    //회원가입
    public void userJoin(UserDTO userDTO){
        usermapper.userJoin(userDTO);
    }

    public UserDTO getUserInfoByEmail(String userId) {

        return usermapper.selectOne(userId);

    }

    // 회원 조회
    public UserDTO getUser(String userId) {return usermapper.selectOne(userId);}

    //아이디중복조회
    public boolean isUserIdExist(String userId) {
        int count = usermapper.idCheckByUserId(userId);
        return count > 0;
    }

    public UserDTO userSearch(String userId) {
        log.info("UserService's user search at userid: " + userId);
        return usermapper.selectOne(userId);
    }

    // 회원정보수정
    public void modify(UserDTO userDTO) {usermapper.update(userDTO);}

    // 회원 삭제
    public void delete(String userId) {usermapper.delete(userId);}
}