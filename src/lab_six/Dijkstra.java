package lab_six;


import java.util.Arrays;

public class Dijkstra {
    private final double[][] graph;
    private final int N;

    public Dijkstra(double[][] graph) throws Exception {
        if (graph == null || graph.length <= 0 || graph[0].length != graph.length) {
            throw new Exception("Null distance array");
        }
        N = graph.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j < N; j++)
                if (graph[i][j] != graph[j][i] || graph[i][j] < 0)
                    throw new Exception("Invalid Graph");
        this.graph = graph;
    }

    public String[] shortest(int start) throws Exception {
        if (0 > start || start >= N) {
            throw new Exception("Invalid vertex");
        }
        boolean[] visit = new boolean[N];//是否遍历过
        visit[start] = true;
        double[] minDis = new double[N];//到各点距离
        String[] path = new String[N];//路径
        for (int i = 0; i < N; i++) {
            path[i] = String.format("%d-->%d",start,i);
        }
        path[start]=""+start;
        double[][] distance = new double[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(graph[i],0,distance[i],0,N);
        }
        double max = Double.MAX_VALUE;

        // todo: complete code here
        //初始化，所有值标为max，所有都未遍历过
        for (int i = 0; i < N; i++) {
            visit[i] = false;
            minDis[i] = max;
        }
        minDis[start] = 0;

        //循环，从第一个开始，之后看值最小的
        int current = start;
        int nextIndex = 0;

        while (!(visit[0] && visit[1] && visit[2] && visit[3] && visit[4] && visit[5] && visit[6] && visit[7])) {
            visit[current] = true;//标记当前为已遍历
            nextIndex=0;
            System.out.println("current:"+current+"  "+Arrays.toString(visit));
            for (int i = 0; i < N; i++) {
                if (distance[current][i] != 0 && current != i) {//检测所有能通向的其他节点
                    if (distance[current][i] + minDis[current] < minDis[i]) {//如果从当前过去比之前记录更近，则更新
                        minDis[i] = distance[current][i] + minDis[current];
                        path[i]=path[current]+String.format("-->%d",i);
                    }
                    System.out.println("from " + current + " to " + i + ",dis:" + (distance[current][i] + minDis[current]));
                    if (!visit[i]) {//下次考察未遍历结点中距离最短的那个
                        if (nextIndex==0) nextIndex=i;
                        else nextIndex = minDis[i] < minDis[nextIndex] ? i : nextIndex;
                        System.out.println("considering:"+i+" nextIndex:"+nextIndex);
                    }
                }
            }
            current = nextIndex;
        }
        path[start]=String.format("%d-->%d",start,start);

        String[] allShortest = new String[N];
        for (int i = 0; i < N; i++) {
            allShortest[i] = String.format("distance from %3d to %3d: %7.2f, path: %s",start,i,minDis[i],path[i]);
        }
        return allShortest;
    }

    public String shortest(int start,int end) throws Exception {
        String[] allShortest = shortest(start);
        if (0 > end || end >= N) {
            throw new Exception("Invalid vertex");
        }
        return allShortest[end];
    }

}
