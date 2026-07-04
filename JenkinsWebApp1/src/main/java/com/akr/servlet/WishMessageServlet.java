package com.akr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/wishbutton")
public class WishMessageServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get Printwriter object
		PrintWriter pw = res.getWriter();
		// set response content type
		res.setContentType("text/html");
		// write content 
		pw.println("<h1 style='color:red,text-align:center'>Good Morning Ankit</h1>");
		
		// home hyperlink 
		pw.println("<br> <a href='index.html'>home-page</a>");
		
		// close the stream
		pw.close();
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
