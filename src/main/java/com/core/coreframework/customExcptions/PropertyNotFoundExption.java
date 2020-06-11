package com.core.coreframework.customExcptions;

public class PropertyNotFoundExption extends RuntimeException {
	String prop;
	public PropertyNotFoundExption(String propName){
		this.prop=propName;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "propery not found "+prop;
	}
}
