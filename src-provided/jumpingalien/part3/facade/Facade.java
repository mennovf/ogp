package jumpingalien.part3.facade;

import jumpingalien.model.Constants;
import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;
import jumpingalien.model.gameobject.Buzam;
import jumpingalien.model.gameobject.Plant;
import jumpingalien.model.gameobject.School;
import jumpingalien.model.gameobject.Shark;
import jumpingalien.model.gameobject.Slime;
import jumpingalien.model.program.Program;
import jumpingalien.model.world.World;
import jumpingalien.part3.programs.ParseOutcome;
import jumpingalien.util.Sprite;

public class Facade extends jumpingalien.part2.facade.Facade implements IFacadePart3 {

	@Override
	public Buzam createBuzam(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		return new Buzam(Utilities.pixelsVectorToMeters(new Vector<>(pixelLeftX, pixelBottomY)), sprites,
				Constants.mazubInitialHorizontalSpeed, Constants.mazubMaxHorizontalSpeed,
				Constants.mazubBeginDirection);
	}

	@Override
	public Buzam createBuzamWithProgram(int pixelLeftX, int pixelBottomY,
			Sprite[] sprites, Program program) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plant createPlantWithProgram(int x, int y, Sprite[] sprites,
			Program program) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shark createSharkWithProgram(int x, int y, Sprite[] sprites,
			Program program) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Slime createSlimeWithProgram(int x, int y, Sprite[] sprites,
			School school, Program program) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParseOutcome<?> parse(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWellFormed(Program program) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addBuzam(World world, Buzam buzam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getLocation(Buzam alien) {
		return Utilities.intVectorToArray(alien.getPositionInPixels());
	}

	@Override
	public double[] getVelocity(Buzam alien) {
		return Utilities.doubleVectorToArray(alien.getSpeed());
	}

	@Override
	public double[] getAcceleration(Buzam alien) {
		return Utilities.doubleVectorToArray(alien.getAcceleration());
	}

	@Override
	public int[] getSize(Buzam alien) {
		return Utilities.intVectorToArray(alien.getSizeInPixels());
	}

	@Override
	public Sprite getCurrentSprite(Buzam alien) {
		return alien.getCurrentSprite();
	}

	@Override
	public int getNbHitPoints(Buzam alien) {
		return alien.getHealth();
	}

}
