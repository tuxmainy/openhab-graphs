package de.devloop.rrd4j;

import java.io.File;

public final class Const {

	private Const() { }

	public final static String RRDPATH = "/rrd";
	public final static double GAS_IMPULSE = 0.01;
	public final static float DEFAULT_SCREEN_SCALE = 90;
	
	public enum TimeRange {
		Hour,
		Hour_12,
		Day,
		Day_3,
		Week,
		Week_2,
		Month,
		Quarter,
		Year,
		Decade;

		public int getSeconds() {
			switch (this) {
			case Hour:
				return 60 * 60;
			case Hour_12:
				return Hour.getSeconds() * 12;
			case Day:
				return Hour.getSeconds() * 24;
			case Day_3:
				return Day.getSeconds() * 3;
			case Week:
				return Day.getSeconds() * 7;
			case Week_2:
				return Week.getSeconds() * 2;
			case Month:
				return Day.getSeconds() * 30;
			case Quarter:
				return Year.getSeconds() / 4;
			case Year:
				return Day.getSeconds() * 365;
			case Decade:
				return Year.getSeconds() * 10;
			default:
				throw new IllegalArgumentException();
			}
		}

		public int getSecondsInv() {
			return -getSeconds();
		}
	}

	public enum Orientation {
		North0(0),
		East(90),
		South(180),
		West(270),
		North360(360);

		private int degrees;
		
		Orientation(int degrees) {
			this.degrees = degrees;
		}

		private int getPlusMinus(int fractions) {
			return 360 / fractions / 2;
		}

		public int getStartDegress(int fractions) {
			return degrees - getPlusMinus(fractions);
		}

		public int getEndDegress(int fractions) {
			return degrees + getPlusMinus(fractions);
		}

		public int getDegrees() {
			return degrees;
		}
	}

	public final static int AIR_PRESSURE_BASELINE = 1013;

	public enum RRDFiles {
		C_BOILER_GAS_COUNT("C_Boiler_Gas_Count.rrd"),
		C_BOILER_HEATING_BURNER("C_Boiler_Heating_Burner.rrd"),
		C_BOILER_HEATING_EXHAUST("C_Boiler_Heating_Exhaust.rrd"),
		C_BOILER_HEATING_FORWARD("C_Boiler_Heating_Forward.rrd"),
		C_BOILER_HEATING_RETURN("C_Boiler_Heating_Return.rrd"),
		C_BOILER_WATER_CIRCULATION("C_Boiler_Water_Circulation.rrd"),
		C_BOILER_WATER_FORWARD("C_Boiler_Water_Forward.rrd"),
		C_BOILER_WATER_IN("C_Boiler_Water_In.rrd"),
		C_BOILER_WATER_RETURN("C_Boiler_Water_Return.rrd"),
		UV_NOW_VALUE("UV_Now_Value.rrd"),
		WEATHER_12H_TEMP("Weather_12h_Temp.rrd"),
		WEATHER_NOW_PRESSURE("Weather_Now_Pressure.rrd"),
		WEATHER_NOW_TEMP("Weather_Now_Temp.rrd"),
		WEATHER_NOW_WIND("Weather_Now_Wind.rrd"),
		WEATHER_NOW_WINDDIRECTION("Weather_Now_WindDirection.rrd"),
		C_HUMIDITY_RAIN("C_Humidity_Rain.rrd"),
		C_TEMP_OUTDOOR("C_Temp_Outdoor.rrd"),
		C_TEMP_GREENHOUSE("C_Temp_GreenHouse.rrd");

		private String filename;
		
		RRDFiles(String filename) {
			this.filename = filename;
		}

		public String getFilePath(String directory) {
			return directory + File.separator + filename;
		}
	}


}
