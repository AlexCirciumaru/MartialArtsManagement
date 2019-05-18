package ro.licenta.controller.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;

import ro.licenta.dto.SearchDTO;

public class PaginationUtils {

	private static final int DEFAULT_PER_PAGE = 10;

	public static PageRequest getPageRequest(SearchDTO searchDTO) {
		PageRequest pageRequest = null;
		if (searchDTO != null) {
			int pageNumber = searchDTO.getPageNumber();
			int perPage = searchDTO.getPerPage();
			String sortBy = searchDTO.getSortBy();
			String sortDirection = searchDTO.getSortDirection();
			Sort sort = null;

			if (pageNumber <= 0) {
				pageNumber = 0;
			} else {
				pageNumber = pageNumber - 1;
			}

			if (perPage < 1) {
				perPage = DEFAULT_PER_PAGE;
			}
			if (!StringUtils.hasText(sortBy)) {
				sortBy = "id";
			}
			if ("ASC".equalsIgnoreCase(sortDirection)) {
				sort = new Sort(Direction.ASC, sortBy);
			} else if ("DESC".equalsIgnoreCase(sortDirection)) {
				sort = new Sort(Direction.DESC, sortBy);
			} else {
				sort = new Sort(Direction.DESC, sortBy);
			}

			pageRequest = PageRequest.of(pageNumber, perPage, sort);
		} else {
			pageRequest = PageRequest.of(0, DEFAULT_PER_PAGE);
		}

		return pageRequest;
	}
}
