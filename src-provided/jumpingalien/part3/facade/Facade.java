package jumpingalien.part3.facade;

import java.util.Optional;

import jumpingalien.model.Constants;
import jumpingalien.model.Utilities;
import jumpingalien.model.Vector;
import jumpingalien.model.gameobject.Buzam;
import jumpingalien.model.gameobject.Plant;
import jumpingalien.model.gameobject.School;
import jumpingalien.model.gameobject.Shark;
import jumpingalien.model.gameobject.Slime;
import jumpingalien.model.program.LanguageProgram;
import jumpingalien.model.program.ProgramFactory;
import jumpingalien.model.program.statement.Statement;
import jumpingalien.model.program.expression.Expression;
import jumpingalien.model.world.World;
import jumpingalien.part3.programs.ParseOutcome;
import jumpingalien.part3.programs.ProgramParser;
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
			Sprite[] sprites, LanguageProgram program) {
		return new Buzam(Utilities.pixelsVectorToMeters(new Vector<>(pixelLeftX, pixelBottomY)), sprites,
				Constants.mazubInitialHorizontalSpeed, Constants.mazubMaxHorizontalSpeed,
				Constants.mazubBeginDirection, program);
	}

	@Override
	public Plant createPlantWithProgram(int x, int y, Sprite[] sprites,
			LanguageProgram program) {
		return new Plant(new Vector<>(Utilities.pixelsToMeters(x), Utilities.pixelsToMeters(y)),
				sprites, program);
	}

	@Override
	public Shark createSharkWithProgram(int x, int y, Sprite[] sprites,
			LanguageProgram program) {
		return new Shark(new Vector<>(Utilities.pixelsToMeters(x), Utilities.pixelsToMeters(y)),
				sprites, program);
	}

	@Override
	public Slime createSlimeWithProgram(int x, int y, Sprite[] sprites,
			School school, LanguageProgram program) {
		return new Slime(new Vector<>(Utilities.pixelsToMeters(x), Utilities.pixelsToMeters(y)),
				sprites, school, program);
	}

	@Override
	public ParseOutcome<?> parse(String text) {
		
		ProgramFactory factory = new ProgramFactory();
		ProgramParser<Expression<?>, Statement, Object, LanguageProgram> parser = new ProgramParser<>(factory);
		
		Optional<LanguageProgram> parseResult = parser.parseString(text);
		
		return parseResult.isPresent() ? ParseOutcome.success(parseResult.get()) : ParseOutcome.failure(parser.getErrors());
	}

	@Override
	public boolean isWellFormed(LanguageProgram program) {
		return program.isWellFormed();
	}

	@Override
	public void addBuzam(World world, Buzam buzam) {
		world.addGameObject(buzam);
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
		//FIXME: Ugly hack to make buzam dissapear when he dies.
		if (!alien.isAlive()) {
			return null;
		}
		return alien.getCurrentSprite();
	}

	@Override
	public int getNbHitPoints(Buzam alien) {
		return alien.getHealth();
	}

}
