import java.util.Random;
import java.util.Vector;

public class main {

	public static void generateElevatorEvent(controller elevator) {
		person p = new person();
		p.starttime = controller.time;
		elevator.tryToEnter(p);

	}

	public static void main(String[] args) {
		controller elevator = new controller();
		int j = 0;
		while (controller.time < 1000||person.i<100) { // While simulation still has time left
			j++;
			if (j % 5 == 0)

				generateElevatorEvent(elevator);

			elevator.checkElevatorExit();
			elevator.checkFloorEnter();
			elevator.move();
			controller.time += 1;
		}
	}
}
