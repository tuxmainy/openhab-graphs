package de.devloop.rrd4j.graph;

import java.awt.Color;
import java.io.IOException;

import org.rrd4j.ConsolFun;
import org.rrd4j.graph.RrdGraph;
import org.rrd4j.graph.RrdGraphConstants;

import de.devloop.rrd4j.Const;
import de.devloop.rrd4j.Const.RRDFiles;

public class Outdoor extends AGraph {

	private final static int NORMALIZE_WIND_DIR = 10;
	private final static int NORMALIZE_HUMIDITY_RAIN = 20;

	public Outdoor() {
		super();
		rrdPath = Const.RRDPATH;
		outfile = RrdGraphConstants.IN_MEMORY_IMAGE;
	}

	@Override
	public RrdGraph graph() throws IOException {
		graphDef.setTitle("Outdoor");
		
		addOrientationBackground(NORMALIZE_WIND_DIR);
		addArea("weather_wind", RRDFiles.WEATHER_NOW_WIND, Color.YELLOW, "Windstärke (km/h)");
		addLine("weather_temp", RRDFiles.WEATHER_NOW_TEMP, Color.GREEN, "Temperatur (Wetterdienst) (°C)");

		graphDef.datasource("humidity_rain", RRDFiles.C_HUMIDITY_RAIN.getFilePath(rrdPath), "state", ConsolFun.AVERAGE);
		graphDef.datasource("humidity_rain_min", "humidity_rain," + NORMALIZE_HUMIDITY_RAIN + ",/");
		graphDef.line("humidity_rain_min", Color.CYAN, "Regensensor (*" + NORMALIZE_HUMIDITY_RAIN + ")", 2);
		
		graphDef.datasource("weather_winddir", RRDFiles.WEATHER_NOW_WINDDIRECTION.getFilePath(rrdPath), "state", ConsolFun.AVERAGE);
		graphDef.datasource("winddir_min", "weather_winddir," + NORMALIZE_WIND_DIR + ",/");
		graphDef.line("winddir_min", Color.BLACK, "Windrichtung (*" + NORMALIZE_WIND_DIR + " °)", 2);

//		gDef.datasource("water_circulation", RRDFiles.C_BOILER_WATER_CIRCULATION.getFilePath(rrdPath), "state", ConsolFun.AVERAGE);
//		gDef.datasource("water_circulation_inv", "water_circulation,-1,*");
//		gDef.datasource("water_forward", RRDFiles.C_BOILER_WATER_FORWARD.getFilePath(rrdPath), "state", ConsolFun.AVERAGE);
//		gDef.datasource("water_forward_inv", "water_forward,-1,*,20,+");
//		gDef.datasource("water_return", RRDFiles.C_BOILER_WATER_RETURN.getFilePath(rrdPath), "state", ConsolFun.AVERAGE);
//		gDef.datasource("water_return_inv", "water_return,-1,*,20,+");
//		gDef.line("water_forward_inv", Color.MAGENTA, "Vorlauf Wasser", 2);
//		gDef.line("water_return_inv", Color.CYAN, "Rücklauf Wasser", 2);
//		gDef.line("water_circulation_inv", Color.PINK, 2);

		graphDef.datasource("weather_pressure", RRDFiles.WEATHER_NOW_PRESSURE.getFilePath(rrdPath), "state", ConsolFun.AVERAGE);
		graphDef.datasource("weather_pressure_min", "weather_pressure," + Const.AIR_PRESSURE_BASELINE + ",-");
		graphDef.line("weather_pressure_min", Color.DARK_GRAY, "Luftdruck (+" + Const.AIR_PRESSURE_BASELINE + " hPa)", 2);

		addLine("outdoor_temp", RRDFiles.C_TEMP_OUTDOOR, Color.RED, "Temperatur (°C)");
		addLine("greenhouse_temp", RRDFiles.C_TEMP_GREENHOUSE, Color.BLUE, "Gewächshaus (°C)");

		return generateGraph();
	}
}
