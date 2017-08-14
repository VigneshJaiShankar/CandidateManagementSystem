package com.wipro.candidate.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.dao.CandidateDAO;
import com.wipro.candidate.util.DBUtil;
import com.wipro.candidate.util.WrongDataException;

public class CandidateMain {
	public String calgrade(int m1,int m2,int m3)
	{
		int sum=m1+m2+m3;
		if(sum>=240)
		{
			return "Distinction";
		}
		else if(sum>=180 && sum<240)
		{
			return "First Class";
		}
		else if(sum>150&&sum<180)
		{
			return "Second Class";
		}
		else if(sum>105 && sum<150)
		{
			return "Third Class";
		}
		else
			return "No Grade";
	}
	public String calResult(int m1,int m2,int m3)
	{
		if((m1+m2+m3)<105)
			return "FAIL";
		else
			return "Pass";
		
	}
	public String addCandidate(CandidateBean candBean)
	{
		if(candBean==null || candBean.getName().length()==0 || candBean.getName().length()<2 || candBean.getM1()<0 && candBean.getM1()>100 || 
		   candBean.getM2()<0 && candBean.getM2()>100 || candBean.getM3()<0 && candBean.getM3()>100)
		{
			
			
			try {
				
				 throw new WrongDataException("Data Incorrect");
			
			} catch (WrongDataException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return e.toString();
			}
		}
		else
		{
			return "Success";
		}
		
	}
	public ArrayList<CandidateBean> displayAll(String criteria)
	{
		//if(criteria.contains("PASS") || )
		
		ArrayList a=new CandidateDAO().getByResult(criteria);
		return a;
	}
	public static void main(String[] args) {
		Connection con=new DBUtil().getDBConn();
		System.out.println(con);
		CandidateBean be=new CandidateBean();
		be.setName("Vignesh");
		be.setM1(99);
		be.setM2(95);
		be.setM3(90);
		String str=new CandidateMain().addCandidate(be);
		if(str.equals("Success"))
		{
		    String grade=new CandidateMain().calgrade(be.getM1(), be.getM2(), be.getM3());
		    be.setGrade(grade);
		    String result=new CandidateMain().calResult(be.getM1(), be.getM2(),be.getM3());
		    be.setResult(result);
		    String id=new CandidateDAO().generateCandidateId(be.getName());
		    be.setId(id);
	        System.out.println(be.tostring());
	        String s=new CandidateDAO().addCandidate(be);
	        System.out.println(s);
	        ArrayList a=new CandidateMain().displayAll("ALL");
	        CandidateBean c=new CandidateBean();
	    //  a.add(c);
	       // System.out.println(a);
	        for(Object o:a)
	        {
	        	c=(CandidateBean)o;
	        	System.out.println(c.getId()+","+c.getName()+","+c.getM1()+","+c.getM2()+","+c.getM3()+","+c.getResult()+","+c.getGrade());
	        }
		}
		else
			System.out.println(str);
	}
}
