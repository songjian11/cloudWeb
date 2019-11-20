package com.bbg.purchaseplan.common;

import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PageLayuiInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
	private int page = 0;

	private int pageSize = 0;

	private long total = 0;

	private List<T> result = null;

	public static PageLayuiInfo setPage(List data, Page page){
		PageLayuiInfo pageLayuiInfo = new PageLayuiInfo();
		pageLayuiInfo.setResult(data);
		pageLayuiInfo.setPage(page.getPageNum());
		pageLayuiInfo.setPageSize(page.getPageSize());
		pageLayuiInfo.setTotal(page.getTotal());
		return pageLayuiInfo;
	};
}
