import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class controller {
	public int eDirection; // 0 for stalled, -1 for down 1 for up
	public int floor; // 0-9 for 10 floors
	// tells you which floors are requested in the elevator
	public boolean[] floorRequested;
	public static double time = 0;
	public Vector<person> people_in_elevator;
	public Vector<person> waiting = new Vector<person>(100);
	public int currentCapacity;
	public int currentDest;
	public static int count = 0;
	public int id;

	public controller() {
		count++;
		eDirection = 0; // -1 for down, 0 for not moving, 1 for going up
		floor = 1; // 1-10 for 10 floors
		// vector of people in elevator;
		people_in_elevator = new Vector<person>(10, 2); // adds extra element if overflow
		floorRequested = new boolean[11];
		id = count;
		// no floor have been requested yet
		for (int i = 1; i < 11; i++)
			floorRequested[i] = false;
		currentDest = 1;
	}

	public int getDirection() { // returns elevator direction
		return eDirection;
	}

	// person enters the elevator
	public void Enter(person P) {
		System.out.println("person " + P.id + " enters the elevator " + id + " at floor " + floor
				+ " and request floor " + P.requestedFloor);
		P.inElevator = true;
		people_in_elevator.add(P);
		floorRequested[P.requestedFloor] = true; // sets floor requested
		currentCapacity++;
		time = time + 0.1;

	}

	// person exits the elevator
	public void Exit(person P) {
		System.out.println("person " + P.id + " exits the elevator " + id + " at floor " + floor);
		P.inElevator = false;
		people_in_elevator.remove(P);
		floorRequested[P.requestedFloor] = false;
		currentCapacity--;
		time = time + 0.1;
	}



	public boolean isRequest() {
		for (int i = 0; i <= floor; i++) {
			if (floorRequested[i] == false)
				return false;

		}
		return true;
	}

	public static boolean checkInterarrival(double timeToNextPerson) {
		if ((time - timeToNextPerson) <= 0.0) {
			return true;
		} else {
			return false;
		}
	}

	public void tryToEnter(person P) { // attempt to enter
		// same direction as elevator and same floor
		if (floor == P.floor) { // if on the same floor
			Enter(P); // enters the elevator
		} else {
			floorRequested[P.requestedFloor] = true;
			System.out.println("person " + P.id + " is waiting for elevator " + id + " at floor " + P.floor);
			waiting.add(P); // add to wait
		}
	}

	// have people exit on their floor
	public void checkElevatorExit() {
		if (people_in_elevator.size() == 0)
			return;
		for (int x = 0; x < people_in_elevator.size(); x++) {
			if (people_in_elevator.elementAt(x).requestedFloor == floor) {
				Exit(people_in_elevator.elementAt(x));
			}
		}
	}

	// check for waiting people
	public void checkFloorEnter() {
		for (int x = 0; x < waiting.size(); x++) {
			if (waiting.elementAt(x).floor == getFloor()) {
				Enter(waiting.elementAt(x));
				waiting.removeElementAt(x);
			}

		}
	}

	public static void elevatorWaiting(double timeWaiting) {
		while (timeWaiting > 0.0) {
			System.out.println("Waiting " + timeWaiting + " time to next person");
			timeWaiting = timeWaiting - 0.1;
		}
	}

	public int getFloor() {
		return floor;
	}

	public void incrementFloor() {
		
		if (floor >= 10) {
			System.out.println("There are only 10 floors");
		} else {
			System.out.println("The elevator " + id + " is going up");
			floor++;
			time = time + 0.2; // time to move between floors
			eDirection = 1;
		}
	}

	public void decrementFloor() {
		
		if (floor <= 1) {
			System.out.println("Cant go below floor 1");
		} else {
			System.out.println("The elevator " + id + " is going down");
			floor--;
			time = time + 0.2; // time to move between floors
			eDirection = -1;
		}
		
	}

	// moves the elevator
	public void move() {
		if(floor==1)
			eDirection=1;
		else if(floor==10)
			eDirection=-1;
		if(eDirection==1)
				incrementFloor();
		else if(eDirection==-1)
			decrementFloor();
	}

	// End
}
