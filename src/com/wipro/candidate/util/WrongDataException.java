package com.wipro.candidate.util;

public class WrongDataException extends Exception{
	String str="";
	public WrongDataException(String s)
	{
		str=s;
	}
	public String toString()
	{
		return str;
	}

}
