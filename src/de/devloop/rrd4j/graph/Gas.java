package de.devloop.rrd4j.graph;

import java.awt.Color;
import java.io.IOException;

import org.rrd4j.ConsolFun;
import org.rrd4j.graph.RrdGraph;
import org.rrd4j.graph.RrdGraphConstants;

import de.devloop.rrd4j.Const;
import de.devloop.rrd4j.Const.RRDFiles;

public class Gas extends AGraph {

	private final static int NORMALIZE_GAS = 40;

	public Gas() {
		super();
		rrdPath = Const.RRDPATH;
		outfile = RrdGraphConstants.IN_MEMORY_IMAGE;
	}

	@Override
	public RrdGraph graph() throws IOException {
		graphDef.setTitle("Gas");
		
		graphDef.datasource("gas", RRDFiles.C_BOILER_GAS_COUNT.getFilePath(rrdPath), "state", ConsolFun.AVERAGE);
		graphDef.datasource("gas_max", "gas," + NORMALIZE_GAS + ",*");
		graphDef.area("gas_max", Color.ORANGE, "Gas (*" + (Const.GAS_IMPULSE / NORMALIZE_GAS) + " m³)");
		
		addLine("weather_temp", RRDFiles.WEATHER_NOW_TEMP, Color.GREEN, "Temperatur (°C)");
		addLine("heating_forward", RRDFiles.C_BOILER_HEATING_FORWARD, Color.RED, "Vorlauf (°C)");
		addLine("heating_return", RRDFiles.C_BOILER_HEATING_RETURN, Color.BLUE, "Rücklauf (°C)");

		return generateGraph();
	}
}
