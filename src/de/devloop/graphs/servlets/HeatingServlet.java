package de.devloop.graphs.servlets;

import javax.servlet.annotation.WebServlet;

import de.devloop.rrd4j.graph.AGraph;
import de.devloop.rrd4j.graph.Heating;

@WebServlet("/heating")
public class HeatingServlet extends AGraphServlet {
	private static final long serialVersionUID = 1L;

	public HeatingServlet() {
		super("heating.jsp");
	}
	
	@Override
	protected AGraph generateGraphObject() {
		return new Heating();
	}

}
