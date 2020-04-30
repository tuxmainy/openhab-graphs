package de.devloop.graphs.servlets;

import javax.servlet.annotation.WebServlet;

import de.devloop.rrd4j.graph.AGraph;
import de.devloop.rrd4j.graph.Temperature;

/**
 * Servlet implementation class WeatherServlet
 */
@WebServlet("/weather")
public class WeatherServlet extends AGraphServlet {
	private static final long serialVersionUID = 1L;

	public WeatherServlet() {
		super("weather.jsp");
	}

	@Override
	protected AGraph generateGraphObject() {
		return new Temperature();
	}
	
}
