package edu.ucsd.liveflow.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ucsd.liveflow.FlowEngineFacade;
import edu.ucsd.saint.commons.xml.Wrapper;
import edu.ucsd.saint.commons.xml.XmlUtils;

public class QueryNumReadyNodes extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1459032105895722229L;
	private final Logger logger = LoggerFactory.getLogger(QueryNumReadyNodes.class);

	/**
	 * 
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		logger.debug(String.format("Query the number of ready nodes"));
		Wrapper d = new Wrapper(XmlUtils.createXML());
		int num = FlowEngineFacade.getFacade().getNumReadyNodes();
		try{
			res.setContentType("text/xml");
			XmlUtils.printXML(
				d.E("result", d.E("numReadyNodes", d.T(num + ""))),
				res.getOutputStream());
		}
		catch(Exception e){
			logger.error(String.format( 
				"Failed to query the number of workflows"), e);
		}

	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		doGet(req, res);
	}
}
