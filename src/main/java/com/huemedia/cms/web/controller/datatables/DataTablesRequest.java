package com.huemedia.cms.web.controller.datatables;

import java.io.Serializable;
import java.util.List;

public class DataTablesRequest implements Serializable {

    private String sEcho;

    private int numColumns;

    private String columns;

    private int iDisplayStart;

    private int iDisplayLength;

    private List<Integer> dataProp;

    private String searchQuery;

    private List<String> columnSearches;

    private boolean hasRegex;

    private List<Boolean> regexColumns;

    private List<Boolean> searchColumns;

    private int sortingCols;
    
    private int iSortingCols;
    
    private List<Integer> sortedColumns;

    private List<String> sortDirections;

    private List<Boolean> sortableColumns;
    
    private String sSortDir_0;
    
    private Integer iSortCol_0;
    
	public DataTablesRequest() {
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(final int numColumns) {
		this.numColumns = numColumns;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(final String columns) {
		this.columns = columns;
	}

	public List<Integer> getDataProp() {
		return dataProp;
	}

	public void setDataProp(final List<Integer> dataProp) {
		this.dataProp = dataProp;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(final String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public List<String> getColumnSearches() {
		return columnSearches;
	}

	public void setColumnSearches(final List<String> columnSearches) {
		this.columnSearches = columnSearches;
	}

	public boolean isHasRegex() {
		return hasRegex;
	}

	public void setHasRegex(final boolean hasRegex) {
		this.hasRegex = hasRegex;
	}

	public List<Boolean> getRegexColumns() {
		return regexColumns;
	}

	public void setRegexColumns(final List<Boolean> regexColumns) {
		this.regexColumns = regexColumns;
	}

	public List<Boolean> getSearchColumns() {
		return searchColumns;
	}

	public void setSearchColumns(final List<Boolean> searchColumns) {
		this.searchColumns = searchColumns;
	}

	public int getSortingCols() {
		return sortingCols;
	}

	public void setSortingCols(final int sortingCols) {
		this.sortingCols = sortingCols;
	}

	public List<Integer> getSortedColumns() {
		return sortedColumns;
	}

	public void setSortedColumns(final List<Integer> sortedColumns) {
		this.sortedColumns = sortedColumns;
	}

	public List<String> getSortDirections() {
		return sortDirections;
	}

	public void setSortDirections(final List<String> sortDirections) {
		this.sortDirections = sortDirections;
	}

	public List<Boolean> getSortableColumns() {
		return sortableColumns;
	}

	public void setSortableColumns(final List<Boolean> sortableColumns) {
		this.sortableColumns = sortableColumns;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(final String sEcho) {
		this.sEcho = sEcho;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(final int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(final int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(final int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public String getsSortDir_0() {
		return sSortDir_0;
	}

	public void setsSortDir_0(final String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}

	public Integer getiSortCol_0() {
		return iSortCol_0;
	}

	public void setiSortCol_0(final Integer iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}

}
