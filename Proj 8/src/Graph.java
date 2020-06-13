import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph<V> {
	 private HashMap<V, HashMap<V, Integer>> graph; 
	
	public Graph(){
		graph = new HashMap<V, HashMap<V, Integer>>();
	}
	
	public boolean addNewVertex(V vertex) {
		if(vertex == null)
			throw new IllegalArgumentException("no bueno");
		
		boolean result = false;
		if(graph.containsKey(vertex)) {
			result = false;
		} else {
			graph.put(vertex, new HashMap<V, Integer>());
			result = true;
		}
		return result;
	}
	
	public boolean isVertex(V vertex) {
		if(vertex == null)
			throw new IllegalArgumentException("no bueno");
		return graph.containsKey(vertex)	;
	}
	
	public Collection<V> getVertices() {
		 List<V> empty = new ArrayList<V>();
		if(graph.isEmpty()) return empty;
		return graph.keySet();
	}
	
	public boolean removeVertexAndEdges(V vertexName) {
		if(vertexName == null)
			throw new IllegalArgumentException("no bueno");
		
		boolean result = false;
		if(graph.containsKey(vertexName)) {
			graph.keySet().remove(vertexName);
			for(V vertex : graph.keySet()) {
				if(graph.get(vertex).containsKey(vertexName)) {
					graph.get(vertex).remove(vertexName);
				}
			}
			result = true;
		} else result = false;
		return result;
	}
	
	public boolean addNewEdge(V sourceVertex, V destVertex, int weight) {
		if(sourceVertex == null || destVertex == null)
			throw new IllegalArgumentException("no bueno");
		
		boolean result = false;
		if((sourceVertex == destVertex && weight != 0) || weight < 0) {
			result = false;
		} else {
			if(graph.containsKey(sourceVertex)) {
				graph.get(sourceVertex).put(destVertex, weight);
				if(!graph.containsKey(destVertex))
					this.addNewVertex(destVertex);
				result = true;
			} else {
				HashMap<V, Integer> dest = new HashMap<V, Integer>();
				dest.put(destVertex, weight);
				graph.put(sourceVertex, dest);
				if(!graph.containsKey(destVertex))
					this.addNewVertex(destVertex);
				result = true;
			}
		}
		return result;
	}
	
	public int weightOfEdge(V sourceVertex, V destVertex) {
		if(sourceVertex == null || destVertex == null)
			throw new IllegalArgumentException("no bueno");
		
		int result = 0;
		if(graph.containsKey(sourceVertex)) {
			if(graph.get(sourceVertex).containsKey(destVertex)) {
				result = graph.get(sourceVertex).get(destVertex);
			} else result = -1;
		} else result = -1;
		return result;
	}
	
	public boolean removeEdge(V sourceVertex, V destVertex) {
		if(sourceVertex == null || destVertex == null)
			throw new IllegalArgumentException("no bueno");
		
		boolean result = false;
		if(graph.containsKey(sourceVertex)) {
			if(graph.get(sourceVertex).containsKey(destVertex)) {
				graph.get(sourceVertex).remove(destVertex);
				result = true;
			} else result = false;
		} else result = false;
		return result;
	}
	
	public Collection<V> neighborsOfVertex(V sourceVertex) {
		if (sourceVertex == null)
			throw new IllegalArgumentException("no bueno");

		Set<V> result = new HashSet<V>();
		if (graph.containsKey(sourceVertex)) {
			for (V vertex : graph.get(sourceVertex).keySet()) {
				result.add(vertex);
			}
		}
		return result;
	}
	
	public int costOfPath(List<V> possiblePath) {
		if(possiblePath == null)
			throw new IllegalArgumentException("no bueno");
		
		int result = 0, correct = 0, flag = 0;
		for(int i = 0; i < possiblePath.size(); i ++) {			
			V currVal = possiblePath.get(i);
			if(graph.containsKey(currVal)) {
				if(i != possiblePath.size() - 1 
						&& graph.get(currVal).isEmpty()) correct = 0;
				for(V val : graph.get(currVal).keySet()) {
					if(i < possiblePath.size() - 1) {
						V nextVal = possiblePath.get(i + 1);
						if(nextVal.equals(val)) {
							result += graph.get(currVal).get(val);
							correct = 1;
							flag = 1;
						} else if (flag != 1) correct = 0;
					} 
				} if (correct != 1) return -1;
			} else return -1;
		}	
		return result;
	}
	
}
