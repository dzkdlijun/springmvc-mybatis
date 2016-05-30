package com.leeo.springdata.base;

import com.leeo.springdata.domain.BaseDomain;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

/**
 * Created by lijun on 2016/5/30.
 */
public class PageDomain<T extends BaseDomain> {
    private int pageNo = 1;
    private int pageSize = 10;
    private int totalPage = 1;
    private long totalCount = 0;
    private List<T> result = Collections.EMPTY_LIST;

    public PageDomain() {
    }

    public PageDomain(Page<T> page) {
        this.pageNo = page.getNumber() + 1;//zero-based page index
        this.pageSize = page.getSize();
        this.totalCount = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        this.result = page.getContent();
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
