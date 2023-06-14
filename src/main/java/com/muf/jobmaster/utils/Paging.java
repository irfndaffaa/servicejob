package com.muf.jobmaster.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Getter
@Setter
@ToString
public class Paging {

    public static final int DEFAULT_LIMIT = 10;
    public static final int DEFAULT_PAGE = 1;

    private int page;
    private int limit;

    @JsonProperty(access = READ_ONLY) private int totalpage;
    @JsonProperty(access = READ_ONLY) private long totalrecord;
    @JsonProperty(access = WRITE_ONLY) private Sort sort;

    public Paging() {
        this.page = DEFAULT_PAGE;
        this.limit = DEFAULT_LIMIT;
        this.sort = Sort.unsorted();
    }

    public Paging(int page, int limit, int totalpage, long totalrecord) {
        this.page = page;
        this.limit = limit;
        this.totalpage = totalpage;
        this.totalrecord = totalrecord;
    }

    public Paging(Page page) {
        this(page.getNumber() + 1, page.getSize(), page.getTotalPages(), page.getTotalElements());
    }

    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(page - 1, limit, sort);
    }
}
