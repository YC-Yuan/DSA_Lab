package lab_six;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void testShortest() throws Exception {
        Dijkstra dijkstra = new Dijkstra(new double[][]{
                new double[]{1000, 4, 7, 3, 1000, 1000, 1000, 1000},
                new double[]{4, 1000, 3, 1000, 2, 1000, 1000, 1000},
                new double[]{7, 3, 1000, 1000, 1000, 2, 1000, 1000},
                new double[]{3, 1000, 1000, 1000, 3, 1000, 1000, 1000},
                new double[]{1000, 2, 1000, 3, 1000, 2, 1000, 1000},
                new double[]{1000, 1000, 2, 1000, 2, 1000, 1000, 1000},
                new double[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000},
                new double[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000},
        });
        String s = dijkstra.shortest(3, 5);
        String expected = "distance from   3 to   5:    5.00, path: 3-->4-->5";
        assertEquals(expected, s);
//        System.out.println(s);
    }

    @Test
    void testAllShortest() throws Exception {
        Dijkstra dijkstra = new Dijkstra(new double[][]{
                new double[]{1000, 4, 7, 3, 1000, 1000, 1000, 1000},
                new double[]{4, 1000, 3, 1000, 2, 1000, 1000, 1000},
                new double[]{7, 3, 1000, 1000, 1000, 2, 1000, 1000},
                new double[]{3, 1000, 1000, 1000, 3, 1000, 1000, 1000},
                new double[]{1000, 2, 1000, 3, 1000, 2, 1000, 1000},
                new double[]{1000, 1000, 2, 1000, 2, 1000, 1000, 1000},
                new double[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000},
                new double[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000},
        });
        String[] expected = new String[]{
                "distance from   3 to   0:    3.00, path: 3-->0",
                "distance from   3 to   1:    5.00, path: 3-->4-->1",
                "distance from   3 to   2:    7.00, path: 3-->4-->5-->2",
                "distance from   3 to   3:    0.00, path: 3-->3",
                "distance from   3 to   4:    3.00, path: 3-->4",
                "distance from   3 to   5:    5.00, path: 3-->4-->5",
                "distance from   3 to   6: 1000.00, path: 3-->6",
                "distance from   3 to   7: 1000.00, path: 3-->7",
        };
        String[] allS = dijkstra.shortest(3);
        assertArrayEquals(expected, allS);
    }
}