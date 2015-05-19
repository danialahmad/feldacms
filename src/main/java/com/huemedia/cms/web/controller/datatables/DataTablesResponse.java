package com.huemedia.cms.web.controller.datatables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataTablesResponse <T> implements Serializable {

    public int iTotalRecords;

    public int iTotalDisplayRecords;

    public String sEcho;

    public String sColumns;

    public List<T> aaData = new ArrayList<T>();

    public DataTablesResponse() {
    }

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(final int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(final int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(final String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(final String sColumns) {
		this.sColumns = sColumns;
	}

	public List<T> getAaData() {
		return aaData;
	}

	public void setAaData(final List<T> aaData) {
		this.aaData = aaData;
	}
}
