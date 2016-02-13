package qualification_2016;

import org.javatuples.Pair;
//import org.javatuples.Triplet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;


// =====================================================================================================================
// MAIN
// =====================================================================================================================

public class Main {

    public static void main(String[] args) throws Exception {
        new Main();
    }

    public Main() throws Exception {
        solve();
    }

    private void solve() throws Exception {
        // BEGIN input
        System.out.println(">>> Input: ");
        Scanner sc = new Scanner(System.in);

        rows = sc.nextInt();
        cols = sc.nextInt();
        nrDrones = sc.nextInt();
        deadline = sc.nextInt();
        maxLoad = sc.nextInt();

        nrProductTypes = sc.nextInt();
        productsWeights = new int[nrProductTypes];
        for (int i = 0; i < nrProductTypes; i++) {
            productsWeights[i] = sc.nextInt();
        }

        nrWarehouses = sc.nextInt();
        warehouseLoc = new ArrayList<Pair<Integer, Integer>>(nrWarehouses);
        warehouseProd = new int[nrWarehouses][nrProductTypes];
        for (int i = 0; i < nrWarehouses; i++) {
            warehouseLoc.add(i, new Pair<Integer, Integer>(sc.nextInt(), sc.nextInt()));
            for (int j = 0; j < nrProductTypes; j++) {
                warehouseProd[i][j] = sc.nextInt();
            }
        }

        nrOrders = sc.nextInt();
        orders = new ArrayList<Order>();
        for (int i = 0; i < nrOrders; i++) {
            Pair<Integer, Integer> orderLoc = new Pair<Integer, Integer>(sc.nextInt(), sc.nextInt());
            int orderedItems = sc.nextInt();
            HashMap<Integer, Integer> tmpMap = new HashMap<Integer, Integer>();
            for (int j = 0; j < orderedItems; j++) {
                int prodType = sc.nextInt();
                if (tmpMap.containsKey(prodType)) {
                    tmpMap.put(prodType, tmpMap.get(prodType) + 1);
                }
                else {
                    tmpMap.put(prodType, 1);
                }
            }
            orders.add(new Order(i, orderLoc, tmpMap));
        }

        sc.close();
        // END input

        // Create drones
        drones = new ArrayList<Drone>(nrDrones);
        Pair<Integer, Integer> startLoc = warehouseLoc.get(0);
        for (int i = 0; i < nrDrones; i++) {
            drones.add(i, new Drone(i, startLoc));
        }

        out = new StringBuilder();
        // Get result
        long startTime = System.currentTimeMillis();
        getResult();
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        writer.write(nrCommands + "\n");
        writer.write(out.toString());
        writer.flush();
        writer.close();
    }


    // =================================================================================================================
    // VARIABLES
    // =================================================================================================================
    int nrCommands = 0;
    StringBuilder out;

    int rows;
    int cols;
    int nrDrones;
    int deadline;
    int maxLoad;

    int nrProductTypes;
    int[] productsWeights;

    int nrWarehouses;
    ArrayList<Pair<Integer, Integer>> warehouseLoc;
    int[][] warehouseProd;

    int nrOrders;
    ArrayList<Order> orders;

    ArrayList<Drone> drones;

    ArrayList<ArrayList<Order>> ordersForWarehouse = new ArrayList<ArrayList<Order>>();


    // =================================================================================================================
    // SOLUTION
    // =================================================================================================================

    private void getResult() throws Exception {
        // Distribute drones over warehouses
        Integer[] sortedWarehouses = sortedWarehouses();

        // globally sort orders: sum distance warehouses
        Integer[] sortedOrders = sortedOrders();

        // Filter orders per warehouse
        fillOrdersForWarehouse();

        // Simulate for turns
        PriorityQueue<Pair<Integer, Drone>> queue = new PriorityQueue<Pair<Integer, Drone>>();
        for (Drone drone : drones) {
            queue.add(new Pair<Integer, Drone>(0, drone));
        }

        while(!queue.isEmpty() && queue.peek().getValue0() < deadline) {
            Pair<Integer, Drone> currentPair = queue.poll();
            Drone drone = currentPair.getValue1();

            if (drone.orders.isEmpty()) {
                // go to warehouse or load if in warehouse
                if (drone.goingToWarehouse) {
                    // DRONE AT WAREHOUSE
                    drone.goingToWarehouse = false;
                    // load
                    drone.orders = findOrdersInWarehouse(drone);
                    if (!drone.orders.isEmpty())
                        queue.add(new Pair<Integer, Drone>(currentPair.getValue0() + getDistance(drone.orders.peek().location, drone.location) + 1, drone));
                }
                else {
                    // DRONE GOING TO WAREHOUSE
                    drone.carries = new HashMap<Order, HashMap<Integer, Integer>>();

                    drone.goingToWarehouse = true;
                    // find best warehouse that has an order
                    ArrayList<Pair<Integer, Integer>> warehouseDist = new ArrayList<Pair<Integer, Integer>>();
                    for (int i = 0; i < nrWarehouses; i++) {
                        warehouseDist.add(new Pair<Integer, Integer>(getDistance(drone.location, warehouseLoc.get(i)), i));
                    }
                    Collections.sort(warehouseDist);

                    int warehouseId = -1;
                    for (Pair<Integer, Integer> warehouse : warehouseDist) {
                        // if order available at warehouse
                        if (!ordersForWarehouse.get(warehouse.getValue1()).isEmpty()) {
                            warehouseId = warehouse.getValue1();
                        }
                    }
                    Pair<Integer, Integer> loc = warehouseLoc.get(warehouseId);

                    queue.add(new Pair<Integer, Drone>(
                            currentPair.getValue0() + getDistance(loc, drone.location) + 1, drone));
                }
            }
            else {
                // DRONE GOING TO ORDER
                // go to location of next order
                Order order = drone.orders.poll();
                Pair<Integer, Integer> loc = order.location;

                for (int key : drone.carries.get(order).keySet()) {
                    nrCommands += 1;
                    out.append(drone.id + " D " + order.id + " " + key + " " + drone.carries.get(order).get(key) + "\n");
                }

                queue.add(new Pair<Integer, Drone>(
                        currentPair.getValue0() + getDistance(loc, drone.location) + 1, drone));
            }

        }
    }

    private Queue<Order> findOrdersInWarehouse(Drone drone) throws Exception {
        // find warehouse
        int warehouseId = -1;
        for (int i = 0; i < nrWarehouses; i++) {
            if (warehouseLoc.get(i).equals(drone.location)) {
                warehouseId = i;
                break;
            }
        }

        Queue<Order> orders = new LinkedList<Order>();

        int capacity = maxLoad;
        outer : for (Order order : ordersForWarehouse.get(warehouseId)) {
            for (int key : new ArrayList<Integer>(order.orderedProducts.keySet())) {
                if (!order.warehousesUsed.containsKey(warehouseId))
                    continue ;
                if (!order.warehousesUsed.get(warehouseId).containsKey(key))
                    continue ;
                int amount = order.warehousesUsed.get(warehouseId).get(key);
                int weight = amount * productsWeights[key];
                if (weight > capacity) {
                    break outer;
                }
                capacity -= weight;
                if (!drone.carries.containsKey(order)) {
                    drone.carries.put(order, new HashMap<Integer, Integer>());
                }
                if (drone.carries.get(order).containsKey(key)) {
                    HashMap<Integer, Integer> map = drone.carries.get(order);
                    map.put(key, map.get(key) + amount);

                }
                else {
                    drone.carries.get(order).put(key, amount);
                }
                order.orderedProducts.put(key, order.orderedProducts.get(key) - amount);
                if (order.orderedProducts.get(key) <= 0){
                    order.orderedProducts.remove(key);
                }
                if (!orders.contains(order)) {
                    orders.add(order);
                }

                warehouseProd[warehouseId][key] -= amount;
                nrCommands += 1;
                out.append(drone.id + " L " + warehouseId + " " + key + " " + amount + "\n");
            }
        }
        fillOrdersForWarehouse();


        return orders;
    }

    public void fillOrdersForWarehouse(){
        for(int warehouseID = 0; warehouseID < nrWarehouses; warehouseID++){
            ArrayList<Order> relevantOrders = new ArrayList<Order>();
            for(Order order : orders){
                order.calculateBestWarehouses();
                if(order.warehousesUsed.containsKey(warehouseID)){
                    relevantOrders.add(order);
                }
            }
            Collections.sort(relevantOrders);
            ordersForWarehouse.add(relevantOrders);
        }
    }

    private Integer[] sortedOrders() { // TODO
        Integer[] orderIds = new Integer[nrOrders];
        for (int i = 0; i < nrOrders; i++) {
            orderIds[i] = i;
        }
        Arrays.sort(orderIds);
        return orderIds;
    }

    private Integer[] sortedWarehouses() {
        Integer[] warehouseIds = new Integer[nrWarehouses];
        for (int i = 0; i < nrWarehouses; i++) {
            warehouseIds[i] = i;
        }
        Arrays.sort(warehouseIds, warehouseComparator());
        return warehouseIds;
    }

    private int getDistance(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
        return (int) Math.ceil(Math.hypot(p1.getValue0() - p2.getValue0(), p1.getValue1() - p2.getValue1()));
    }

    private Comparator<Integer> warehouseComparator() {
        return new Comparator<Integer>() {
            @Override
            public int compare(Integer id1, Integer id2) {
                int sum1 = 0;
                int sum2 = 0;
                for (int i = 0; i < nrProductTypes; i++) {
                    sum1 += warehouseProd[id1][i];
                    sum2 += warehouseProd[id2][i];
                }
                return Integer.compare(sum1, sum2);
            }
        };
    }

    private class Drone implements Comparable<Drone>{

        public Pair<Integer, Integer> location;
        public int id;
        public HashMap<Order, HashMap<Integer, Integer>> carries = new HashMap<Order, HashMap<Integer, Integer>>(); // prodtype -> nr of items
        public Queue<Order> orders = new LinkedList<Order>();
        public boolean goingToWarehouse = true;

        public Drone(int id, Pair<Integer, Integer> loc) {
            this.id = id;
            this.location = loc;
        }

        @Override
        public int compareTo(Drone o) {
            return 0;
        }

//        public int getLoad() {
//            int load = 0;
//            for (int i : carries.keySet()) {
//                load += carries.get(i) * productsWeights[i];
//            }
//            return load;
//        }

    }

    public class Order implements Comparable<Order> {
        public int id;
        public Pair<Integer, Integer> location;
        public HashMap<Integer, Integer> orderedProducts;
        public HashMap<Integer, HashMap<Integer, Integer>> warehousesUsed;

        public Order(int id, Pair<Integer, Integer> location, HashMap<Integer, Integer> orderedProducts) {
            this.id = id;
            this.location = location;
            this.orderedProducts = orderedProducts;
        }

        public void calculateBestWarehouses(){
            //resultaat komt in deze variabele
            warehousesUsed = new HashMap<Integer, HashMap<Integer, Integer>>();

            //sort warehouses from distance to this order
            ArrayList<Pair<Integer, Integer>> warehouseDist = new ArrayList<Pair<Integer, Integer>>();
            for (int i = 0; i < nrWarehouses; i++) {
                warehouseDist.add(new Pair<Integer, Integer>(getDistance(location, warehouseLoc.get(i)), i));
            }
            Collections.sort(warehouseDist);
            HashMap<Integer, Integer> productscopy = (HashMap<Integer, Integer>) orderedProducts.clone();
            for (Pair<Integer, Integer> warehouse : warehouseDist) {
                int warehouseID = warehouse.getValue1();
                for (Integer key : productscopy.keySet()) { //voor elk product-type in order
                    int amountInWarehouse = warehouseProd[warehouseID][key]; // hoeveelheid in warehouse
                    if (productscopy.get(key) > 0 && amountInWarehouse > 0) {

                        int amountTaken;
                        if (amountInWarehouse > productscopy.get(key)) {
                            amountTaken = productscopy.get(key);
                            productscopy.put(key, 0);
                        }
                        else{
                            productscopy.put(key, productscopy.get(key)-amountInWarehouse);
                            amountTaken = amountInWarehouse;
                        }

                        if(!warehousesUsed.containsKey(warehouseID)){
                            warehousesUsed.put(warehouseID, new HashMap<Integer, Integer>());
                        }
                        warehousesUsed.get(warehouseID).put(key, amountTaken);

                    }
                }
            }

        }

        @Override
        public int compareTo(Order o) {
            int weightThis = 0;
            for(Integer warehouse : warehousesUsed.keySet()){
                weightThis+=getDistance(location, warehouseLoc.get(warehouse));
            }

            int weightO = 0;
            for(Integer warehouse : o.warehousesUsed.keySet()){
                weightO+=getDistance(o.location, warehouseLoc.get(warehouse));
            }

            return weightThis-weightO;
        }
    }
}