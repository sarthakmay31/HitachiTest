package com.framework.configruler.exceptions;

public class PropertyFileNotFoundException extends RuntimeException{

	private static final long serialVersionUID=1L;

	public PropertyFileNotFoundException()
	{
		super();
	}

	public PropertyFileNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3)
	{
		super(arg0,arg1,arg2,arg3);
	}
	public PropertyFileNotFoundException(String arg0, Throwable arg1)
	{
		super(arg0,arg1);
	}
	public PropertyFileNotFoundException(String arg0)
	{
		super(arg0);
	}
	public PropertyFileNotFoundException(Throwable arg1)
	{
		super(arg1);
	}


}
