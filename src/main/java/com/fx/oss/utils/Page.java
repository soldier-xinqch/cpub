package com.fx.oss.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数据记录泛型分页封装类。
 *
 * @author Weiyong Huang
 * @version 1.0 2013/06/27
 */
public class Page<T> {

    protected final static int DEFAULT_SHOW_PAGE_NUMBER_COUNT = 9;
    private int startIndex;
    private int pageSize = 10;
    private int totalCount;
    @SuppressWarnings("unchecked")
    private Collection<T> results;
    private int[] showPageNumbers;

    @SuppressWarnings("unchecked")
    public Page() {
        this.startIndex = -1;
        this.totalCount = 0;
        this.results = new ArrayList();
    }

    @SuppressWarnings("unchecked")
    public Page(int startIndex, int pageSize, int totalCount, Collection<T> results) {
        this.startIndex = startIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.results = results;
    }

    public int[] getShowPageNumbers() {
        return getShowPageNumbers(DEFAULT_SHOW_PAGE_NUMBER_COUNT);
    }

    public int[] getShowPageNumbers(int showCount) {
        int currentPage = startIndex / pageSize;
        if (showPageNumbers == null) {
            int totalPageCount = getTotalPageCount();;
            showPageNumbers = new int[totalPageCount > showCount ? showCount : totalPageCount];
            if (totalPageCount > showCount) {
                int firstShowPage = currentPage - showCount / 2;
                int endShowPage = currentPage + showCount / 2;
                if (firstShowPage > 0 && endShowPage < totalPageCount) {
                    for (int i = 0, max = showPageNumbers.length; i < max; i++) {
                        showPageNumbers[i] = firstShowPage + i;
                    }
                } else if (firstShowPage > 0) {
                    for (int i = 0, max = showPageNumbers.length; i < max; i++) {
                        showPageNumbers[i] = totalPageCount - showCount + i;
                    }
                } else {
                    for (int i = 0, max = showPageNumbers.length; i < max; i++) {
                        showPageNumbers[i] = i;
                    }
                }
            } else {
                for (int i = 0, max = showPageNumbers.length; i < max; i++) {
                    showPageNumbers[i] = i;
                }
            }
        }
        return showPageNumbers;
    }

    public int getTotalPageCount() {
        return (totalCount % pageSize == 0) ? totalCount / pageSize : totalCount / pageSize + 1;
    }

    public int getNextIndex() {
        return startIndex + pageSize;
    }

    public int getPreviousIndex() {
        int previousIndex = startIndex - pageSize;
        return previousIndex >= 0 ? previousIndex : 0;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Collection<T> getResults() {
        return results;
    }

    public List<T> getList() {
        return results == null ? null : results instanceof List ? (List<T>) results : new ArrayList<T>(results);
    }

    public Set<T> getSet() {
        return results == null ? null : results instanceof Set ? (Set<T>) results : new HashSet<T>(results);
    }

    @SuppressWarnings("unchecked")
    public List<T> getSortList() {
        List list = getList();
        if (list != null) {
            Collections.sort(list);
        }
        return list;
    }

    public void setResults(Collection<T> results) {
        this.results = results;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        return totalCount > 0 ? (totalCount - 1) / pageSize + 1 : 0;
    }

    public int getPage() {
        return startIndex >= 0 ? startIndex / pageSize + 1 : 0;
    }
}
