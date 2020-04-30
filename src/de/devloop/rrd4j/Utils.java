package de.devloop.rrd4j;

public final class Utils {
	private Utils() { }

	public enum ScreenRatio {
		SixteenNine(16, 9),
		FourThree(4, 3);

		private int width;
		private int height;
		
		ScreenRatio(int width, int height) {
			this.width = width;
			this.height = height;
		}

		private int getSize(int size, float scale) {
			return Math.round(size * scale);
		}

		public int getWidth(float scale) {
			return getSize(width, scale);
		}

		public int getHeight(float scale) {
			return getSize(height, scale);
		}
	}
}