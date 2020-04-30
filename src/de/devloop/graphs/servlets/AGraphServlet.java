package de.devloop.graphs.servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rrd4j.graph.RrdGraph;

import de.devloop.rrd4j.Const;
import de.devloop.rrd4j.Const.TimeRange;
import de.devloop.rrd4j.Utils.ScreenRatio;
import de.devloop.rrd4j.graph.AGraph;

public abstract class AGraphServlet extends HttpServlet {

	private static final long serialVersionUID = 2257424367516157036L;

	private String jsp = null;
	
	public AGraphServlet(String jsp) {
		this.jsp = jsp;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("image") != null) {
			AGraph gd = generateGraphObject();
			gd.setTimeRange(getTimeRange(request));
			gd.setSize(getScreenRatio(request), getScreenScale(request));
			
			RrdGraph g = gd.graph();
			response.setContentType("image/png");
			
			OutputStream o = response.getOutputStream();
			o.write(g.getRrdGraphInfo().getBytes());
			o.close();
		} else {
			RequestDispatcher view = request.getRequestDispatcher(jsp);
			view.forward(request, response);
		}
	}
	
	protected abstract AGraph generateGraphObject();
	
	private float getScreenScale(HttpServletRequest request) {
		String screenScaleParameter = request.getParameter("scale");
		if (screenScaleParameter != null) {
			try {
				return Float.parseFloat(screenScaleParameter);
			} catch (NumberFormatException nfe) { }
		}
		
		return Const.DEFAULT_SCREEN_SCALE;
	}
	
	private ScreenRatio getScreenRatio(HttpServletRequest request) {
		String screenRatioParameter = request.getParameter("ratio");
		if (screenRatioParameter != null) {
			try {
				return ScreenRatio.valueOf(screenRatioParameter);
			} catch (IllegalArgumentException iae) { }
		}
		
		return ScreenRatio.SixteenNine;
	}
	
	private TimeRange getTimeRange(HttpServletRequest request) {
		String timeRangeParameter = request.getParameter("range");
		if (timeRangeParameter != null) {
			try {
				return TimeRange.valueOf(timeRangeParameter);
			} catch (IllegalArgumentException iae) { }
		}
		
		return TimeRange.Week;
	}
}
