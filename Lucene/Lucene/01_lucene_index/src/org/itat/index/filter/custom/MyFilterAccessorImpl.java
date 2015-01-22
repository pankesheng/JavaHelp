package org.itat.index.filter.custom;


public class MyFilterAccessorImpl implements MyFilterAccessor{

	@Override
	public String[] getValues() {
		return new String[]{"4","8","5"};
	}

	@Override
	public String getField() {
		return "id";
	}

	@Override
	public boolean isSet() {
		return true;
	}

}
