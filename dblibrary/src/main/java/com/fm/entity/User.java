package mian.com.fm.entity;

import java.io.Serializable;

import mian.com.fm.dbmanager.AnnotationColumns;
import com.fm.dbmanager.AnnotationTables;

@AnnotationTables
public class User implements Serializable {
	@AnnotationColumns
	private String name;
	@AnnotationColumns
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@AnnotationColumns(isPrimaryKey=true)
	private Long _id;

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}
	
	public User() {
		super();
	}

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
}
