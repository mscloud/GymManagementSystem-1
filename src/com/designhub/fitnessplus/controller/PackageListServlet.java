package com.designhub.fitnessplus.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.designhub.fitnessplus.bean.PackageBean;
import com.designhub.fitnessplus.dao.PackageDAO;

public class PackageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<PackageBean> listOfPackage = new PackageDAO().list();
		
		if(listOfPackage!=null)
		{
			request.setAttribute("listOfPackage",listOfPackage);
			request.getRequestDispatcher("packageList.jsp").forward(request, response);
		}
		
		
	}

}
