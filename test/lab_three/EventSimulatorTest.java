package lab_three;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventSimulatorTest {

    @Test
    void simulate() throws lab_three.HeapOverflowException, lab_three.HeapEmptyException {
        List<Cow> arrivalLine = new ArrayList<>();
        arrivalLine.add(new Cow(0, 8));
        arrivalLine.add(new Cow(1, 5));
        arrivalLine.add(new Cow(2, 3));
        arrivalLine.add(new Cow(4, 1));
        arrivalLine.add(new Cow(4, 5));
        arrivalLine.add(new Cow(4, 4));
        arrivalLine.add(new Cow(5, 4));
        arrivalLine.add(new Cow(6, 1));
        arrivalLine.add(new Cow(7, 3));
        EventSimulator es = new EventSimulator(3, arrivalLine);
        String res = es.simulate();
        assertEquals(
                "Time:        0, arrival:        0, stay:        8, leave:        8\n" +
                        "Time:        0, arrival:        1, stay:        5, leave:        6\n" +
                        "Time:        0, arrival:        2, stay:        3, leave:        5\n" +
                        "Time:        5, arrival:        4, stay:        1, leave:        6\n" +
                        "Time:        6, arrival:        4, stay:        5, leave:       11\n" +
                        "Time:        6, arrival:        4, stay:        4, leave:       10\n" +
                        "Time:        8, arrival:        5, stay:        4, leave:       12\n" +
                        "Time:       10, arrival:        6, stay:        1, leave:       11\n" +
                        "Time:       11, arrival:        7, stay:        3, leave:       14\n", res);
    }

    @Test
    void simulate2() throws IOException, ClassNotFoundException {
        ObjectInputStream nOis = new ObjectInputStream(new FileInputStream("res" + File.separator + "res.obj"));
        Object nObj = nOis.readObject();
        String expected = (String) nObj;ObjectInputStream ois = new ObjectInputStream(new FileInputStream("res" + File.separator + "list.obj"));
        Object obj = ois.readObject();
        ois.close();
        List<Cow> nArrival = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                nArrival.add((Cow) o);
            }
        }
        String simulation = assertTimeout(Duration.ofSeconds(20), () -> {


            EventSimulator es = new EventSimulator(3000, nArrival);
            return es.simulate();
        }, "Using too much time to supply");
        assertEquals(expected, simulation);
    }
}