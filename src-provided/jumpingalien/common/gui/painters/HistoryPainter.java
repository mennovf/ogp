package jumpingalien.common.gui.painters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import jumpingalien.common.gui.AlienGameScreen;

public class HistoryPainter extends AbstractAlienPainter<AlienGameScreen<?, ?>> {

	private static final Color HISTORY_DOTS_COLOR = Color.GRAY;

	private static final int MAX_HISTORY_SIZE = 20;

	private final LinkedList<int[]> history = new LinkedList<int[]>();

	public HistoryPainter(AlienGameScreen<?, ?> screen) {
		super(screen);
	}

	@Override
	public void paintInWorld(Graphics2D g) {
		g.setColor(HISTORY_DOTS_COLOR);
		for (int[] xyH : history) {
			g.fillRect(xyH[0] - 1, xyH[1] - 1, 2, 2);
		}
		getGame().getAlienInfoProvider().getAlienXY()
				.ifPresent(xy -> updateHistory(xy));
	}

	private void updateHistory(int[] xy) {
		history.add(xy);
		while (history.size() > MAX_HISTORY_SIZE) {
			history.poll();
		}
	}

}
