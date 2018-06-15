package kr.maxerve.admin.framework;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class CustomPaginationRenderer extends AbstractPaginationRenderer {
	public CustomPaginationRenderer() {
		firstPageLabel = "<li onClick=\"{0}({1});\"><a href=\"javascript:void(0)\"><img src=\"/img/ico_prev01.png\" alt=\"첫페이지\" /></a></li>";
		previousPageLabel = "<li onclick=\"{0}({1});\"><a href=\"javascript:void(0)\"><img src=\"/img/ico_prev02.png\" alt=\"이전페이지\" /></a></li>";
		currentPageLabel = "<li class=\"on\"><a href=\"javascript:void(0)\">{0}</a></li>";
		otherPageLabel = "<li onclick=\"{0}({1});\"><a href=\"javascript:void(0)\">{2}</a></li>";
		nextPageLabel = "<li onclick=\"{0}({1});\"><a href=\"javascript:void(0)\"><img src=\"/img/ico_next01.png\" alt=\"다음페이지\" /></a></li>";
		lastPageLabel = "<li class=\"end\" onclick=\"{0}({1});\"><a href=\"javascript:void(0)\"><img src=\"/img/ico_next02.png\" alt=\"끝페이지\" /></a></li>";
	}
}
