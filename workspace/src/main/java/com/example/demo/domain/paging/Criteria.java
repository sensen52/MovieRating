package com.example.demo.domain.paging;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Criteria {

    // 현재페이지
    private int pageNum;

    // 한페이지 당 보여줄 갯수
    private int amount;

    // 스킵 할 게시물 수(몇번째 게시물 부터 나올 것인가)
    private int skip;

    
    // 기본생성자
    public Criteria() {
        this(1,10);
    }

    //매개변수로 받을 페이지정보들
    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.skip =(pageNum-1) * amount;
    }

    public void setPageNum(int pageNum){
        this.skip = (pageNum-1) * this.amount;
        this.pageNum = pageNum;
    }

    public void setAmount(int amount){
        this.skip = (pageNum-1) * amount;
        this.amount = amount;
    }
}
