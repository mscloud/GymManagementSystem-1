package com.designhub.fitnessplus.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.designhub.fitnessplus.dao.FeedbackSubjectDAO;


@SuppressWarnings("serial")
public class FeedbackSubjectDeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String feedbackSubjectId=(request.getParameter("feedbackSubjectId"));
		if(new FeedbackSubjectDAO().delete(feedbackSubjectId))
		{
			PrintWriter out = response.getWriter();
			out.write("<font color=RED size=6><center>FeedbackSubject Deleted.....</center></font>");
			request.getRequestDispatcher("feedbackSubjectListServlet").forward(request, response);
		}
		else{
			PrintWriter out = response.getWriter();
			out.write("<font color=RED size=6><center>Something Went Wrong.....</center></font>");
			request.getRequestDispatcher("feedbackList.jsp").include(request,response);
		}
	}

}
