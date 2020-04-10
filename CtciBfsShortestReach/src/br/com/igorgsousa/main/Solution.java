package br.com.igorgsousa.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * {@link https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach/problem}
 * */

 class Graph {
	 
    private Set<Node> nodes = new HashSet<>();
     
    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }
    
    public void addAllNode(Collection<Node> nodes) {
        nodes.addAll(nodes);
    }


	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}
}

class Node {
	
	private int data;
	
	private List<Node> shortestPath = new LinkedList<>();	
	
	private Map<Node, Integer> adjacentNodes = new HashMap<>();

	private Integer distance = Integer.MAX_VALUE;
	
	public Node(int data) {
		super();
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
	
	public void addDestination(Node destination, int distance) {
	        adjacentNodes.put(destination, distance);
	}
	
	public List<Node> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(List<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}

	public Map<Node, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj == null) {
			return false;
		}
		
		return this.getData() == ((Node) obj).getData();
	}
	
	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}
}

public class Solution {
	
	private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
	    Node lowestDistanceNode = null;
	    int lowestDistance = Integer.MAX_VALUE;
	    for (Node node: unsettledNodes) {
	        int nodeDistance = node.getDistance();
	        if (nodeDistance < lowestDistance) {
	            lowestDistance = nodeDistance;
	            lowestDistanceNode = node;
	        }
	    }
	    return lowestDistanceNode;
	}
	
	private static void calculateMinimumDistance(Node evaluationNode,
			  Integer edgeWeigh, Node sourceNode) {
	    Integer sourceDistance = sourceNode.getDistance();
	    if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
	        evaluationNode.setDistance(sourceDistance + edgeWeigh);
	        LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
	        shortestPath.add(sourceNode);
	        evaluationNode.setShortestPath(shortestPath);
	    }
	}
	
	public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
	    source.setDistance(0);
	 
	    Set<Node> settledNodes = new HashSet<>();
	    Set<Node> unsettledNodes = new HashSet<>();
	 
	    unsettledNodes.add(source);
	 
	    while (unsettledNodes.size() != 0) {
	        Node currentNode = getLowestDistanceNode(unsettledNodes);
	        unsettledNodes.remove(currentNode);
	        for (Entry< Node, Integer> adjacencyPair: 
	          currentNode.getAdjacentNodes().entrySet()) {
	            Node adjacentNode = adjacencyPair.getKey();
	            Integer edgeWeight = adjacencyPair.getValue();
	            if (!settledNodes.contains(adjacentNode)) {
	                calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
	                unsettledNodes.add(adjacentNode);
	            }
	        }
	        settledNodes.add(currentNode);
	    }
	    return graph;
	}
	
	
	public static void doJob(Scanner scanner) {
		
		String nodeAndEdgesStr = scanner.nextLine();
		String[] nodeAndEdges =  nodeAndEdgesStr.split(" ");
		int nodeNumber = new Integer(nodeAndEdges[0]);
		int edgeNumber = new Integer(nodeAndEdges[1]);
		
		Map<Integer, Node> nodes = new LinkedHashMap<Integer, Node>();

		for(int i = 0; i < nodeNumber ; i++) {
			nodes.put(i+1, new Node(i+1));
		}

		for(int i = 0; i < edgeNumber; i++) {
			String edgesStr = scanner.nextLine();

			String[] edges = edgesStr.split(" ");
						
			Node nodeOne = nodes.get( new Integer(edges[0]));
			Node nodeTwo = nodes.get( new Integer(edges[1]));
			
			nodeOne.addDestination(nodeTwo,6);
			nodeTwo.addDestination(nodeOne, 6);
		}
		
		Integer startNodeId = new Integer( scanner.nextLine().trim());
		
		String result = "";
		Node startNode = nodes.get(startNodeId);
		
		Graph graph = new Graph();
		for(Entry<Integer, Node> entry : nodes.entrySet()) {
			graph.addNode(entry.getValue());
		}
		
		graph = calculateShortestPathFromSource(graph, startNode);
		
		for(Entry<Integer, Node> node : nodes.entrySet()) {
			if(!node.getValue().equals(startNode)) {
				if(node.getValue().getDistance().equals(Integer.MAX_VALUE)) {
					result += "-1 ";
				}else {
					result += (node.getValue().getDistance())+" ";
				}
			}
		}
		
		result = result.substring(0,result.lastIndexOf(" "));
		
		System.out.println(result);
		
	}

    public static void main(String[] args) throws FileNotFoundException {
    	
//    	Scanner scanner = new Scanner(System.in);
    	Scanner scanner = new Scanner(new File("resources/input08.txt"));
    	
    	int jobsNumber = new Integer( scanner.nextLine());
    	
    	for( int i = 0; i < jobsNumber ; i++ ) {
    		doJob(scanner);
    	}
    	
    
    }
}