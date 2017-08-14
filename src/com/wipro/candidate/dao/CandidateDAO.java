package com.wipro.candidate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.util.DBUtil;

public class CandidateDAO {
	public String addCandidate(CandidateBean CandidateBean)
	{
		String str="FAIL";
		if(CandidateBean!=null)
		{
			new DBUtil();
			Connection con=new DBUtil().getDBConn();
			if(con!=null)
			{
				String sql="insert into CANDIDATE_TBL values(?,?,?,?,?,?,?)";
				try {
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setString(1, CandidateBean.getId());
					ps.setString(2, CandidateBean.getName());
					ps.setInt(3, CandidateBean.getM1());
					ps.setInt(4, CandidateBean.getM2());
					ps.setInt(5, CandidateBean.getM3());
					ps.setString(6, CandidateBean.getResult());
					ps.setString(7, CandidateBean.getGrade());
				int z=ps.executeUpdate();
				if(z>0)
				{
					str="Sucess";
					return str;
				}
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					return str;
				}
			}
		}
		return str;
		
	}
	public ArrayList<CandidateBean> getByResult(String criteria)
	{
		String sql="";
		ArrayList<CandidateBean> a=new ArrayList<CandidateBean>();
		if(criteria.contains("PASS"))
		{
			sql="select * from CANDIDATE_TBL where result=\"PASS\"";
		}
		if(criteria.contains("FAIL"))
		{
			sql="select * from CANDIDATE_TBL where result=\"FAIL\"";
		}
		if(criteria.contains("ALL"))
		{
			sql="select * from CANDIDATE_TBL";
		}
		new DBUtil();
		Connection con=DBUtil.getDBConn();
		if(con!=null)
		{
			try {
				PreparedStatement psat=con.prepareStatement(sql);
				ResultSet rs=psat.executeQuery();
				while(rs.next())
				{
					CandidateBean can=new CandidateBean();
					can.setId(rs.getString(1));
					can.setName(rs.getString(2));
					can.setM1(rs.getInt(3));
					can.setM2(rs.getInt(4));
					can.setM3(rs.getInt(5));
					can.setResult(rs.getString(6));
					can.setGrade(rs.getString(7));
					a.add(can);
				}
				if(a.isEmpty())
					return null;
				else
					return a;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}
	public String generateCandidateId (String name)
	{
		String candid=null;
		if(name!=null)
		{
			String s=name.substring(0,2);
			Connection con=new DBUtil().getDBConn();
			if(con!=null)
			{
				String qry="Select candid_seq.nextval from dual";
				
				try {
					PreparedStatement ps=con.prepareStatement(qry);
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						candid=s+rs.getString(1);
					}
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		return candid;
	}
}

