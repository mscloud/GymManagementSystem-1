package com.designhub.fitnessplus.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.designhub.fitnessplus.bean.ActivityBean;
import com.designhub.fitnessplus.dao.ActivityDAO;

public class ActivityEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String activityId = request.getParameter("id");
		
		ActivityBean activity = new ActivityDAO().getByPK(activityId);
		if(activity!=null){
				request.setAttribute("activity", activity);
				request.getRequestDispatcher("activityEdit.jsp").forward(request, response);
		}else{
				response.sendRedirect("ActivityListServlet");
		}

	}

}
