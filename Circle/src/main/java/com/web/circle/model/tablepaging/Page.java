package com.web.circle.model.tablepaging;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Page<T> {

	public Page(List<T> data) {
        this.data = data;
    }

    private List<T> data;
    private int recordsFiltered;
    private int recordsTotal;
    private int draw;
	
}
