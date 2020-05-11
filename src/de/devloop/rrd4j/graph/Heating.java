package de.devloop.rrd4j.graph;

import java.awt.Color;
import java.io.IOException;

import org.rrd4j.graph.RrdGraph;
import org.rrd4j.graph.RrdGraphConstants;

import de.devloop.rrd4j.Const;
import de.devloop.rrd4j.Const.RRDFiles;

public class Heating extends AGraph {

	public Heating() {
		super();
		rrdPath = Const.RRDPATH;
		outfile = RrdGraphConstants.IN_MEMORY_IMAGE;
	}
	
	@Override
	public RrdGraph graph() throws IOException {
		graphDef.setTitle("Heating");
		
		addArea("gas", RRDFiles.C_BOILER_GAS_COUNT, Color.ORANGE, "Gas (*" + Const.GAS_IMPULSE + " m³)");
		
		addLine("weather_temp", RRDFiles.WEATHER_NOW_TEMP, Color.BLACK, "Temperatur (°C)");
		
		addLine("heating_forward", RRDFiles.C_BOILER_HEATING_FORWARD, Color.RED, "Heizung Vorlauf (°C)");
		addLine("heating_return", RRDFiles.C_BOILER_HEATING_RETURN, Color.BLUE, "Heizung Rücklauf (°C)");
		addLine("water_forward", RRDFiles.C_BOILER_WATER_FORWARD, Color.MAGENTA, "Wasser Vorlauf (°C)");
		addLine("water_return", RRDFiles.C_BOILER_WATER_RETURN, Color.CYAN, "Wasser Rücklauf (°C)");
		addLine("water_in", RRDFiles.C_BOILER_WATER_IN, Color.GREEN, "Frischwasser (°C)");
		addLine("water_circulation", RRDFiles.C_BOILER_WATER_CIRCULATION, Color.GRAY, "Wasser Zirkulation (°C)");

		return generateGraph();
	}

}
