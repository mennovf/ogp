package jumpingalien.part2.internal;

import java.util.Optional;

import jumpingalien.common.game.WorldInfoProvider;
import jumpingalien.part2.internal.tmxfile.data.ImageTile.TileType;

public interface Part2WorldInfoProvider extends WorldInfoProvider {

	Optional<int[]> getVisibleWindow();

	int getTileLength();

	Optional<int[][]> getTilesIn(int left, int bottom, int right, int top);

	Optional<TileType> getGeologicalFeature(int bottomLeftX, int bottomLeftY);

	Optional<int[]> getBottomLeftPixelOfTile(int tileX, int tileY);

	Optional<Boolean> isGameOver();

	Optional<Boolean> didPlayerWin();

}
