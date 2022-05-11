package com.excel.publicapi.entities;

import java.io.Serializable;

@SerializableType
public class NewItems implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5556389989307093011L;
	
	public String name;
	public Boolean isRequired;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsRequired() {
		return isRequired;
	}
	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}
//	@Override
//	public String toString() {
//		return "NewItems [name=" + name + ", isRequired=" + isRequired + "]";
//	}
	
	

}
