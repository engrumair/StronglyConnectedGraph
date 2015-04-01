package com.algo;

import java.util.ArrayList;

public class SGraph {
	
	ArrayList< ArrayList<Integer> > data = null;
	
	public SGraph(int v){
		
		data= new ArrayList<ArrayList<Integer>>();
		
		data.add(new ArrayList<Integer>());
		
		for(int k=0; k<v;k++){
			
			data.add(new ArrayList<Integer>());
			
		}
		
		
		
	}
	
	
	
	public int totalEdges(int v){
		
		ArrayList<Integer> edgeRepresented =data.get(v);
		int num_edges = edgeRepresented.size();
				
		return num_edges;
		
	}
	
	public ArrayList<Integer> getEdges(int v){
		
		return data.get(v);
	}
	
	public void setEdges(int v, ArrayList<Integer> anEdge){
		data.set(v, anEdge);
			
	}
	
	public void setEdges_Array(int v, int[] array)
	{
		
		setEdges(v, AddArrayToArrayList(array));
		
	}
	
	private ArrayList<Integer> AddArrayToArrayList(int[] arr){
		
	ArrayList<Integer> row1 = new ArrayList<>();
	
		for(int i =0; i<arr.length;i++){
			row1.add(arr[i]);
			
		}
	
	
	
	return row1;
	
	}

	public void addDirectedEdge(int v, int m){
		
		ArrayList<Integer> allValues =  this.getEdges(v);
		allValues.add(m);
		
	}
//	public int RemoveEdge(int v, int edgeElement){
//
//		int totalRemoved =0;
//		ArrayList<Integer> rowElements =   this.data.get(v); //if graph is : 1 | 2   3 then it will return 2 3
//		int indexOfElement =-1;
//		 indexOfElement =  rowElements.indexOf(edgeElement);
//		 while(indexOfElement !=-1){
//			 rowElements.remove(indexOfElement);
//			 totalRemoved++;
//			 indexOfElement =  rowElements.indexOf(edgeElement);
//		 }
//		
//		
//		return totalRemoved;
//		
//	}
	
	/*public void ReplaceElement(int v, int elementToReplace, int withelement){

		
		
		ArrayList<Integer> arrayList = this.data.get(v);
		int indexOfElement =-1;
		
		 indexOfElement =  arrayList.indexOf(elementToReplace);
		while(indexOfElement !=-1){
			arrayList.set(indexOfElement, withelement);
			 indexOfElement =  arrayList.indexOf(elementToReplace);
			
		}
	
		
	}*/
}
