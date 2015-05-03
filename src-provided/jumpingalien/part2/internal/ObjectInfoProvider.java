package jumpingalien.part2.internal;

import java.util.Collection;
import java.util.Optional;

import jumpingalien.common.sprites.ImageSprite;
import jumpingalien.model.gameobject.Gore;
import jumpingalien.model.gameobject.Plant;
import jumpingalien.model.gameobject.School;
import jumpingalien.model.gameobject.Shark;
import jumpingalien.model.gameobject.Slime;

public interface ObjectInfoProvider {

	public Collection<Slime> getSlimes();

	public Collection<Shark> getSharks();

	public Collection<Plant> getPlants();

	public Optional<int[]> getLocation(Plant plant);

	public Optional<int[]> getLocation(Shark shark);

	public Optional<int[]> getLocation(Slime slime);
	
	//TODO: We added this!!!
	public Optional<int[]> getLocation(Gore blood);

	public Optional<ImageSprite> getCurrentSprite(Plant plant);

	public Optional<ImageSprite> getCurrentSprite(Shark shark);

	public Optional<ImageSprite> getCurrentSprite(Slime slime);
	
	//TODO: We added this!!!
	public Optional<ImageSprite> getCurrentSprite(Gore blood);

	public Optional<School> getSchool(Slime slime);

	//TODO: We added this!!!
	public Collection<Gore> getBlood();

}
