package de.devloop.rrd4j.graph;

import java.awt.Color;
import java.io.IOException;

import org.rrd4j.ConsolFun;
import org.rrd4j.graph.RrdGraph;
import org.rrd4j.graph.RrdGraphDef;

import de.devloop.rrd4j.Const.Orientation;
import de.devloop.rrd4j.Const.RRDFiles;
import de.devloop.rrd4j.Const.TimeRange;
import de.devloop.rrd4j.Utils.ScreenRatio;

public abstract class AGraph {
	protected RrdGraphDef graphDef;

	protected String rrdPath;
	protected String outfile;
	
	public AGraph() {
		graphDef = new RrdGraphDef();
	}
	
	public abstract RrdGraph graph() throws IOException;
	
	public void setSize(ScreenRatio ratio, float scale) {
		graphDef.setWidth(ratio.getWidth(scale));
		graphDef.setHeight(ratio.getHeight(scale));
	}
	
	public void setTimeRange(TimeRange range) {
		graphDef.setStartTime(range.getSecondsInv());
	}
	
	public RrdGraph generateGraph() throws IOException {
		graphDef.setFilename(outfile);
		graphDef.setImageFormat("png");
		return new RrdGraph(graphDef);
	}
	
	public void addOrientationBackground(int normalizeBase) {
		graphDef.datasource("winddir_north1", Orientation.North0.getEndDegress(4) + "," + normalizeBase + ",/");
		graphDef.datasource("winddir_north2", Orientation.North360.getDegrees() + "," + normalizeBase + ",/");
		graphDef.datasource("winddir_east", Orientation.East.getEndDegress(4) + "," + normalizeBase + ",/");
		graphDef.datasource("winddir_south", Orientation.South.getEndDegress(4) + "," + normalizeBase + ",/");
		graphDef.datasource("winddir_west", Orientation.West.getEndDegress(4) + "," + normalizeBase + ",/");
		graphDef.area("winddir_north2", Color.GRAY);
		graphDef.area("winddir_west", Color.LIGHT_GRAY);
		graphDef.area("winddir_south", Color.GRAY);
		graphDef.area("winddir_east", Color.LIGHT_GRAY);
		graphDef.area("winddir_north1", Color.GRAY);
	}
	
	public void addArea(String dsName, RRDFiles rrdFile, Color color, String description) {
		graphDef.datasource(dsName, rrdFile.getFilePath(rrdPath), "state", ConsolFun.AVERAGE);
		graphDef.area(dsName, color, description);
	}
	
	public void addLine(String dsName, RRDFiles rrdFile, Color color, String description) {
		graphDef.datasource(dsName, rrdFile.getFilePath(rrdPath), "state", ConsolFun.AVERAGE);
		graphDef.line(dsName, color, description, 2);
	}
}
