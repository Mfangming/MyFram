package com.fm.entity;

import java.io.Serializable;

import android.content.Intent;

public class ProjectInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String projectname;
	
	private String projectdescribe;
	
	private Intent mintent;

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectdescribe() {
		return projectdescribe;
	}

	public void setProjectdescribe(String projectdescribe) {
		this.projectdescribe = projectdescribe;
	}

	public Intent getMintent() {
		return mintent;
	}

	public void setMintent(Intent mintent) {
		this.mintent = mintent;
	}

	public ProjectInfo(String projectname, Intent mintent) {
		super();
		this.projectname = projectname;
		this.mintent = mintent;
	}

	public ProjectInfo() {
		super();
	}
	

	
}
