package de.devloop.graphs.servlets;

import javax.servlet.annotation.WebServlet;

import de.devloop.rrd4j.graph.AGraph;
import de.devloop.rrd4j.graph.Outdoor;

/**
 * Servlet implementation class WeatherServlet
 */
@WebServlet("/outdoor")
public class OutdoorServlet extends AGraphServlet {
	private static final long serialVersionUID = 1L;

	public OutdoorServlet() {
		super("outdoor.jsp");
	}

	@Override
	protected AGraph generateGraphObject() {
		return new Outdoor();
	}
	
}
