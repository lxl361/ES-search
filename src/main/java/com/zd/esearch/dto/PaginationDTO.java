package com.zd.esearch.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2021/12/30 0030 17:51
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages=new ArrayList<>();//页码集合

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage=0;
        if (totalCount % size==0){
            totalPage=totalCount /size;
        }else {
            totalPage=totalCount /size +1;
        }

        pages.add(page);
        for (int i = 1; i <=3 ; i++) {
            if (page-i>0){
                pages.add(0,page-i);
            }

            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }

        //是否展示上一页
        if (page==1){
            showPrevious=false;
        }else {
            showPrevious=true;
        }

        //是否展示下一页
        if (page==totalPage){
            showNext=false;
        }else {
            showNext=true;
        }

        //判断集合是否包含第一页
        if (pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }

        //判断集合是否包含最后一页
        if (pages.contains(totalPage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }

    }
}
