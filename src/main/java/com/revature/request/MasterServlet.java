package com.revature.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controller.HomeControllerAlpha;

/**
 * The MasterServlet class is the only existing Servlet within the
 * application, it acts as a Front Controller, that receives every single 
 * request and gives away every response using a RequestHelper to find out
 * which Controller needs to be called when a specific URI is received.
 * 
 * @author Revature LLC
 */
public class MasterServlet extends HttpServlet {

	private static final long serialVersionUID = 1159764852861289598L;
    private static Logger logger = Logger.getLogger(MasterServlet.class);


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Object data = RequestHelper.getRequestHelper().process(request);

		
		if(data instanceof String) {
			String URI = (String) data;
			logger.trace("Inside Master servlet.....  \t" + URI);
			request.getRequestDispatcher(URI).forward(request, response);
		} 
		else {
			// We send data to the client if we receive any kind of object that is not a String.
			response.getWriter().write(
					new ObjectMapper().writeValueAsString(data));
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// We can do this because we check if something is get or post inside the Controllers.
		doGet(request, response);
	}	
}
