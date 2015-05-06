package jumpingalien.part3.internal;

import jumpingalien.part2.internal.Part2Options;

public class Part3Options extends Part2Options {
	
	private long randomSeed = 123;
	
	public long getRandomSeed() {
		return randomSeed;
	}
	
	public void setRandomSeed(long randomSeed) {
		this.randomSeed = randomSeed;
	}

	public static Part3Options parse(String[] args) {
		Part3Options options = new Part3Options();

		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			switch (arg) {
			case "-seed":
				options.setRandomSeed(Long.parseLong(args[++i]));
				break;
			case "-targetfps":
				options.setTargetFPS(Integer.parseInt(args[++i]));
				break;
			case "-timescale":
				options.setTimescale(Double.parseDouble(args[++i]));
				break;
			case "-debuginfo":
				options.setDebugShowInfo(Boolean.parseBoolean(args[++i]));
				break;
			case "-debugaxes":
				options.setDebugShowAxes(Boolean.parseBoolean(args[++i]));
				break;
			case "-debuglocation":
				options.setDebugShowObjectLocationAndSize(Boolean
						.parseBoolean(args[++i]));
				break;
			case "-debugpixels":
				options.setDebugShowPixels(Boolean.parseBoolean(args[++i]));
				break;
			case "-debughistory":
				options.setDebugShowHistory(Boolean.parseBoolean(args[++i]));
				break;
			case "-showtiletypes":
				options.setDebugShowTileTypes(Boolean.parseBoolean(args[++i]));
				break;
			}
		}

		return options;
	}
}
