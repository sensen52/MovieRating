package com.example.demo.domain.paging;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class PageMakerDTO {

    // 시작페이지
    private int startPage;
    // 마지막페이지
    private int endPage;
    // 이전페이지, 다음페이지 존재유무
    private boolean prev, next;
    // 전체 게시물 수
    private int totalPages;
    // 현재페이지,페이당 게시물 표시 수 정보(정의한 Criteria;)
    private Criteria criteria;


    public PageMakerDTO(Criteria criteria,int totalPages){
        this.totalPages = totalPages;
        this.criteria = criteria;
        this.endPage =(int)(Math.ceil(criteria.getPageNum()/10.0) *10);
        this.startPage = this.endPage -9;
        int realEnd =(int)Math.ceil(totalPages *1.0 /criteria.getAmount());

        if(realEnd < this.endPage){
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;



    }
}
