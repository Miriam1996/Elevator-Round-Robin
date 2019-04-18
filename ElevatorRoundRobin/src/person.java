import java.util.Random;

public class person {
	public static Random rng = new Random();
	public int floor;
	public int requestedFloor;
	public boolean inElevator;
	public int dir;
	public static int i = 1;
	public int id;
	public double starttime;

	public person() {
		floor = rng.nextInt(10) + 1; // floors 1-10
		inElevator = false;
		requestedFloor = rng.nextInt(10) + 1; // destination floor 1-10
		dir = (requestedFloor - floor);
		while (floor == requestedFloor) {// finds new floor if current floor and destination are the same
			requestedFloor = rng.nextInt(10) + 1;
		}
		if (dir > 0)
			dir = 1;
		else
			dir = -1;
		id = i;
		i++;

	}

}
