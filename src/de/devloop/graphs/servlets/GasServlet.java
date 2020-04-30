package de.devloop.graphs.servlets;

import javax.servlet.annotation.WebServlet;

import de.devloop.rrd4j.graph.AGraph;
import de.devloop.rrd4j.graph.Gas;

/**
 * Servlet implementation class GasServlet
 */
@WebServlet("/gas")
public class GasServlet extends AGraphServlet {
	private static final long serialVersionUID = 1L;
	
	public GasServlet() {
		super("gas.jsp");
	}
       
	@Override
	protected AGraph generateGraphObject() {
		return new Gas();
	}

}
