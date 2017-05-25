package com.designhub.fitnessplus.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.designhub.fitnessplus.bean.MemberProgressBean;
import com.designhub.fitnessplus.dao.ProgressDAO;


public class ProgressDetailListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MemberProgressBean> memberProgresslist =new ProgressDAO().list();
		if (memberProgresslist!= null) {

			request.setAttribute("memberProgresslist", memberProgresslist);
			request.getRequestDispatcher("memberProgressList.jsp").include(request,
					response);
	}
	}
	

}
