package com.designhub.fitnessplus.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;


import com.designhub.fitnessplus.bean.MemberProgressDetailsBean;
import com.designhub.fitnessplus.bean.ProgressBean;
import com.designhub.fitnessplus.util.*;

public class MemberProgressDetailsDAO {
	private static PreparedStatement pstmt=null;
	private static Connection conn=null;
	int rowsAffected=0;
	private boolean result=false;
	ResultSet rs=null;
	boolean chk=false;
	public boolean insert(MemberProgressDetailsBean progressbean) {
		conn=DBConnection.getConnection();
		try {
			String selectSQL="select * from memberprogress where memberId= ?";
			pstmt=conn.prepareStatement(selectSQL);
			System.out.println("memberIIIIIIId-->"+progressbean.getMemberId());
			pstmt.setString(1, progressbean.getMemberId());
			ResultSet rs=pstmt.executeQuery();
			int progressId=0;
			 while(rs.next()){
				 progressId=rs.getInt("progressId");
				 chk=true;
			 }
			if(!chk)
			{
				String insertSQL="insert into memberProgress(memberId) values ( ? )";
				pstmt=conn.prepareStatement(insertSQL);
				pstmt.setString(1, progressbean.getMemberId());
				rowsAffected=pstmt.executeUpdate();
				if(rowsAffected==0)
					return false;
				
				System.out.println("after MemberProgress");
				String insertProgressSQL="insert into memberprogressdetails(height,weight,bmi,biceps,hips,thigh,neck,forearms,chest,calves,bodyfat,waist, " +
						" progressDateTime,progressId) values (?,?,?,?,?,?,?,?,?,?,?,?,?,LAST_INSERT_ID())";
			//	System.out.println("progressId-->");
				pstmt=conn.prepareStatement(insertProgressSQL);
				pstmt.setString(1,progressbean.getHeight());
				pstmt.setString(2,progressbean.getWeight());
				pstmt.setString(3,progressbean.getBmi());
				pstmt.setString(4, progressbean.getBiceps());
				pstmt.setString(5,progressbean.getHips());
				pstmt.setString(6,progressbean.getThigh());
				pstmt.setString(7,progressbean.getNeck());
				pstmt.setString(8,progressbean.getForearms());
				pstmt.setString(9,progressbean.getChest());
				pstmt.setString(10,progressbean.getCalves());
				pstmt.setString(11,progressbean.getBodyfat());
				pstmt.setString(12,progressbean.getWaist());
				pstmt.setString(13,progressbean.getDatetime());
				rowsAffected=pstmt.executeUpdate();
				if(rowsAffected>0)
				{
					result=true;
				}
				else {
					String deleteSQL="delete from memberProgress where progressId=LAST_INSERT_ID()";//String DeleteSQL="ROLLBACK"; in TutoPoint learn SQL->transection
					 pstmt = conn.prepareStatement(deleteSQL);
					 rowsAffected=pstmt.executeUpdate();
					 if(rowsAffected>0)
					 {
						 System.out.println("Data is RollBacked");
					 }
					result=false;
				}
			}
			else
			{
				System.out.println("else");
				
				String insertProgressSQL="insert into memberprogressdetails(height,weight,bmi,biceps,hips,thigh,neck,forearms,chest,calves,bodyfat,waist, " +
					" progressDateTime,progressId) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				System.out.println("progressId-->");
				pstmt=conn.prepareStatement(insertProgressSQL);
				pstmt.setString(1,progressbean.getHeight());
				pstmt.setString(2,progressbean.getWeight());
				pstmt.setString(3,progressbean.getBmi());
				pstmt.setString(4, progressbean.getBiceps());
				pstmt.setString(5,progressbean.getHips());
				pstmt.setString(6,progressbean.getThigh());
				pstmt.setString(7,progressbean.getNeck());
				pstmt.setString(8,progressbean.getForearms());
				pstmt.setString(9,progressbean.getChest());
				pstmt.setString(10,progressbean.getCalves());
				pstmt.setString(11,progressbean.getBodyfat());
				pstmt.setString(12,progressbean.getWaist());
				pstmt.setString(13,progressbean.getDatetime());
				pstmt.setInt(14,progressId);
//			System.out.println(insertSQL);
//			System.out.println("Waist---->\t"+progressBean.getWaist());
			rowsAffected=pstmt.executeUpdate();
			if(rowsAffected>0)
			{
				result=true;
			}
			else
				result=false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
		
		public java.util.List<MemberProgressDetailsBean> list(String memberId){
			java.util.List<MemberProgressDetailsBean> progresslist=new ArrayList<MemberProgressDetailsBean>();
			
			System.out.println("MemberId-------1>"+memberId);
			String query="select * from memberprogressdetails,memberprogress where memberprogressdetails.progressId=memberprogress.progressId AND memberProgress.memberId=?";
			MemberProgressDetailsBean memberProgressDetailsBean;
			conn=DBConnection.getConnection();
			ResultSet rs=null;
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, memberId);
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					memberProgressDetailsBean=new MemberProgressDetailsBean();
					System.out.println("<----");
					
					memberProgressDetailsBean.setProgressDetailId(rs.getString("progressDetailId"));
					memberProgressDetailsBean.setHeight(rs.getString("height"));
					memberProgressDetailsBean.setWeight(rs.getString("weight"));
					memberProgressDetailsBean.setBmi(rs.getString("bmi"));
					memberProgressDetailsBean.setBiceps(rs.getString("biceps"));
					memberProgressDetailsBean.setHips(rs.getString("hips"));
					memberProgressDetailsBean.setThigh(rs.getString("thigh"));
					memberProgressDetailsBean.setNeck(rs.getString("neck"));
					memberProgressDetailsBean.setForearms(rs.getString("forearms"));
					memberProgressDetailsBean.setChest(rs.getString("chest"));
					memberProgressDetailsBean.setCalves(rs.getString("calves"));
					memberProgressDetailsBean.setBodyfat(rs.getString("bodyfat"));
					memberProgressDetailsBean.setWaist(rs.getString("waist"));
					memberProgressDetailsBean.setDatetime(rs.getString("progressDateTime"));
					progresslist.add(memberProgressDetailsBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return progresslist;
		}
	
/*public List<MemberProgressBean> list(){
		
		List<MemberProgressBean> listOfMemberProgress = new ArrayList<MemberProgressBean>();
		
		 conn=DBConnection.getConnection();	
			
		 if(conn!=null){
			 String selectSQL = "Select *" +
			 					" from member m,memberProgress ms" +
			 					" where m.memberId=ms.memberId";
			 
			 try {
				pstmt = conn.prepareStatement(selectSQL);
			
				rs = pstmt.executeQuery();

				MemberProgressBean memberProgressBean = null;
				while(rs.next()){
					memberProgressBean = new MemberProgressBean();
				
					memberProgressBean.setMemberId(rs.getString("memberId"));
					//memberSchedule.setMemberScheduleId(rs.getString("memberScheduleId"));
					memberProgressBean.setMemberFirstName(rs.getString("memberFirstName"));
					memberProgressBean.setMemberLastName(rs.getString("memberLastName"));
					memberProgressBean.setProgressId(rs.getString("progressId"));
					
									
					listOfMemberProgress.add(memberProgressBean);
				}
				
				
			 } catch (SQLException e) {
				e.printStackTrace();
			}
			 
			 
		 }
		//System.out.println("Size : "+listOfMemberProgress.size());
		return listOfMemberProgress;
	}*/
		public MemberProgressDetailsBean getByPK(String progressDetailid){
			
			MemberProgressDetailsBean progressBean=new MemberProgressDetailsBean();
			String selectSQL="select * from memberprogressdetails,memberProgress where progressDetailId = ? and memberProgress.progressId=memberProgressdetails.progressId";
			conn=DBConnection.getConnection();
			ResultSet rs=null;
			try {
				pstmt=conn.prepareStatement(selectSQL);
				pstmt.setString(1, progressDetailid);
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					progressBean.setMemberId(rs.getString("memberId"));
					progressBean.setProgressDetailId(rs.getString("progressDetailId"));
					progressBean.setHeight(rs.getString("height"));
					progressBean.setWeight(rs.getString("weight"));
					progressBean.setBmi(rs.getString("bmi"));
					progressBean.setBiceps(rs.getString("biceps"));
					progressBean.setHips(rs.getString("hips"));
					progressBean.setThigh(rs.getString("thigh"));
					progressBean.setNeck(rs.getString("neck"));
					progressBean.setForearms(rs.getString("forearms"));
					progressBean.setChest(rs.getString("chest"));
					progressBean.setCalves(rs.getString("calves"));
					progressBean.setBodyfat(rs.getString("bodyfat"));
					progressBean.setWaist(rs.getString("waist"));
					progressBean.setDatetime(rs.getString("progressDateTime"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return progressBean;
		}
		
		public boolean update(ProgressBean progressBean)
		{
			conn=DBConnection.getConnection();
			try {
				String updateSQL="update memberprogressdetails SET height=?,weight=?,bmi=?,hips=?,thigh=?,neck=?,forearms=?,chest=?,calves=?,bodyfat=?,waist=?,biceps=? where progressDetailid=?";
				pstmt=conn.prepareStatement(updateSQL);
				pstmt.setString(1,progressBean.getHeight());
				pstmt.setString(2,progressBean.getWeight());
				pstmt.setString(3,progressBean.getBmi());
				pstmt.setString(4,progressBean.getHips());
				pstmt.setString(5,progressBean.getThigh());
				pstmt.setString(6,progressBean.getNeck());
				pstmt.setString(7,progressBean.getForearms());
				pstmt.setString(8,progressBean.getChest());
				pstmt.setString(9,progressBean.getCalves());
				pstmt.setString(10,progressBean.getBodyfat());
				pstmt.setString(11,progressBean.getWaist());
				pstmt.setString(12,progressBean.getBiceps());
				pstmt.setString(13,progressBean.getProgressDetailId());
				rowsAffected=pstmt.executeUpdate();
				if(rowsAffected>0)
				{
					result=true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		public boolean delete(String progressDetailsId) {

			conn=DBConnection.getConnection();
			String deleteSQL="delete from memberprogressdetails where progressDetailsID = "+progressDetailsId;
			try {
				pstmt=conn.prepareStatement(deleteSQL);
				rowsAffected=pstmt.executeUpdate();
				if(rowsAffected>0)
				{
					result=true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}

		public boolean deleteByProgressId(String progressId) {

			conn=DBConnection.getConnection();
			String deleteSQL="delete from memberprogressdetails where progressID = "+progressId;
			try {
				pstmt=conn.prepareStatement(deleteSQL);
				rowsAffected=pstmt.executeUpdate();
				if(rowsAffected>0)
				{
					result=true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		public int getMemberId(String progressDetailId) {
			int memberId=0;
			String selectSQL="select memberId from memberprogress,memberprogressDetails where progressDetailId=? and memberprogress.ProgressId=memberprogressDetails.progressId";
			conn=DBConnection.getConnection();
			try {
				pstmt = conn.prepareStatement(selectSQL);
				pstmt.setString(1, progressDetailId);
				
				rs = pstmt.executeQuery();
					
				
				while(rs.next()){
					memberId=rs.getInt("memberID");
				}
				System.out.println("MEMBERID--->"+memberId);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return memberId;
		}
		
		public static List allProgress()
		{
java.util.List<MemberProgressDetailsBean> progresslist=new ArrayList<MemberProgressDetailsBean>();
			
			String query="select * from memberprogressdetails";
			MemberProgressDetailsBean memberProgressDetailsBean;
			conn=DBConnection.getConnection();
			ResultSet rs=null;
			try {
				pstmt=conn.prepareStatement(query);
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					memberProgressDetailsBean=new MemberProgressDetailsBean();
					System.out.println("<----");
					
					memberProgressDetailsBean.setProgressDetailId(rs.getString("progressDetailId"));
					memberProgressDetailsBean.setHeight(rs.getString("height"));
					memberProgressDetailsBean.setWeight(rs.getString("weight"));
					memberProgressDetailsBean.setBmi(rs.getString("bmi"));
					memberProgressDetailsBean.setBiceps(rs.getString("biceps"));
					memberProgressDetailsBean.setHips(rs.getString("hips"));
					memberProgressDetailsBean.setThigh(rs.getString("thigh"));
					memberProgressDetailsBean.setNeck(rs.getString("neck"));
					memberProgressDetailsBean.setForearms(rs.getString("forearms"));
					memberProgressDetailsBean.setChest(rs.getString("chest"));
					memberProgressDetailsBean.setCalves(rs.getString("calves"));
					memberProgressDetailsBean.setBodyfat(rs.getString("bodyfat"));
					memberProgressDetailsBean.setWaist(rs.getString("waist"));
					memberProgressDetailsBean.setDatetime(rs.getString("progressDateTime"));
					progresslist.add(memberProgressDetailsBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return progresslist;
		}
}
