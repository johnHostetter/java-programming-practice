import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {

        Graph backbone = new Graph();
        Graph phoneSystem = new Graph();

        Scanner scanner = new Scanner(System.in);
        // get the first number, k, which represents the number of rope links in the proposed backbone
        int k = Integer.parseInt(scanner.nextLine());
        // get the second number, n, which represents the number of string links in the phone system (i.e. non-backbone links)
        int n = Integer.parseInt(scanner.nextLine());

        // check the k lines of the rope links
        for (int i = 0; i < k; ++i) {
            String[] tokens = scanner.nextLine().split(" ");
            backbone.addEdge(tokens[0], tokens[1]);
        }

        // check the n lines of the string links
        for (int i = 0; i < n; ++i) {
            String[] tokens = scanner.nextLine().split(" ");
            phoneSystem.addEdge(tokens[0], tokens[1]);
        }

        if (backbone.checkIfBackboneValid() && phoneSystem.checkIfPhoneSystemValid(backbone)) {
            System.out.println("valid");
        } else {
            System.out.println("invalid");
        }
    }

    public static class Graph {
        ArrayList<Edge> edges = new ArrayList<>();

        public Graph() {

        }

        public void addEdge(String start, String destination) {
            Edge edge = new Edge(start,destination);

            edges.add(new Edge(start, destination));
        }

        public boolean checkIfPhoneSystemValid(Graph backbone) {
            return checkIfPhoneSystemValidHelper(backbone, edges);
        }

        private boolean checkIfPhoneSystemValidHelper(Graph backbone, ArrayList<Edge> phones) {
            boolean phoneSystemValid = true;
            for (Edge phone : phones) {
                boolean phoneValid = false;
                for (Edge backboneEdge : backbone.edges) {
                    if (backboneEdge.equals(phone)) {
                        phoneValid = true;
                    }

                    Edge flippedEdge = phone.flipEdge();

                    if (backboneEdge.equals(flippedEdge)) {
                        phoneValid = true;
                    }
                }

                if (!phoneValid) {
                    phoneSystemValid = false;
                }
            }

            return phoneSystemValid;
        }

        public boolean checkIfBackboneValid() {
            ArrayList<Edge> remainingEdges = new ArrayList<>();
            remainingEdges.addAll(this.edges);
            return checkIfBackboneValidHelper(new ArrayList<>(), remainingEdges);
        }

        private boolean checkIfBackboneValidHelper(ArrayList<Edge> validEdges, ArrayList<Edge> remainingEdges) {
            // base case
            if (remainingEdges.isEmpty()) {
                return true;
            } else if (validEdges.isEmpty()) {
                boolean bool = false;
                int i = 0;

                while (!bool && i < remainingEdges.size()) {
                    Edge edge = remainingEdges.get(i);
                    validEdges.add(edge);
                    remainingEdges.remove(edge);
                    bool = checkIfBackboneValidHelper(validEdges, remainingEdges);
                    if (!bool) {
                        remainingEdges.add(edge);
                        validEdges.remove(edge);
                    } else {
                        return true;
                    }
                    ++i;
                }

                return false;
            } else {
                // get the next edge
                for (Edge edge : remainingEdges) {
                    if (edge.getDestination().equals(validEdges.get(0).getStart())) {
                        // add the edge to the beginning
                        validEdges.add(0, edge);
                        remainingEdges.remove(edge);
                        return checkIfBackboneValidHelper(validEdges, remainingEdges);
                    } else if (edge.getStart().equals(validEdges.get(validEdges.size() - 1).getDestination())) {
                        // add the edge to the end
                        validEdges.add(edge);
                        remainingEdges.remove(edge);
                        return checkIfBackboneValidHelper(validEdges, remainingEdges);
                    } else if (edge.getStart().equals(validEdges.get(0).getStart())) {
                        // flip the edge, and add the edge to the beginning
                        Edge flippedEdge = new Edge(edge.getDestination(), edge.getStart());
                        validEdges.add(0, flippedEdge);
                        remainingEdges.remove(edge);
                        return checkIfBackboneValidHelper(validEdges, remainingEdges);
                    } else if (edge.getDestination().equals(validEdges.get(validEdges.size() - 1).getDestination())) {
                        // flip the edge, and add the edge to the end
                        Edge flippedEdge = new Edge(edge.getDestination(), edge.getStart());
                        validEdges.add(flippedEdge);
                        remainingEdges.remove(edge);
                        return checkIfBackboneValidHelper(validEdges, remainingEdges);
                    }
                }

                return false;
            }
        }

        public static class Edge {
            String start;
            String destination;

            public Edge(String start, String destination) {
                this.start = start;
                this.destination = destination;
            }

            public Edge flipEdge() {
                String temp = this.start;
                this.start = this.destination;
                this.destination = temp;
                return this;
            }

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }

            public String getDestination() {
                return destination;
            }

            public void setDestination(String destination) {
                this.destination = destination;
            }

            @Override
            public boolean equals(Object o) {
                if (o.getClass().equals(this.getClass())) {
                    Edge edge = (Edge) o;
                    return this.start.equals(edge.start) || this.destination.equals(edge.destination);
                }

                return false;
            }
        }
    }
}
