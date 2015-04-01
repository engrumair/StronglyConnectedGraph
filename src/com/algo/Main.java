package com.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;





public class Main {
	
	static int TOTAL_VERTICES =12;  //875714;  // make it plus one 5+1
	static int LINES_IN_FILE = 20 ; //5105043;
	static String FILE_ADDRESS =  "G:/assignment4/test5.txt";  //"G:/assignment4/SCC.txt";//   "G:/assignment4/locTest.txt"; 
	
	
	static int[] isVertexExplored ; // if its elment contain 1 it means it is explored
	static int[] isVertexExploredReversed;
	
	static int[] finishingTime;
	static int[] leaders;

	public static void main(String[] args) {
		// TODO Auto-generated me	thod stub
		
		
		
		isVertexExplored = new int[TOTAL_VERTICES+1];
		
		isVertexExploredReversed=  new int[TOTAL_VERTICES+1];
		
		finishingTime = new int[TOTAL_VERTICES+1];
		
		leaders =new int[TOTAL_VERTICES+1];
		
		
		In aFile = new In(FILE_ADDRESS);
		
			
		
		SGraph g=new SGraph(TOTAL_VERTICES);
		int dummyCounter =0;
		for(int l=0 ; l< LINES_IN_FILE; l++)
		
		{
		
			
			int vertexOfGraph = aFile.readInt();
			int path = aFile.readInt();
			
			g.addDirectedEdge(vertexOfGraph, path);

			
			
		}
		
		/*g.setEdges_Array(1, new int[]{4});
		g.setEdges_Array(2, new int[]{8});
		g.setEdges_Array(3, new int[]{6});
		g.setEdges_Array(4, new int[]{7});
		g.setEdges_Array(5, new int[]{2});
		g.setEdges_Array(6, new int[]{9});
		g.setEdges_Array(7, new int[]{1});
		g.setEdges_Array(8, new int[]{6,5});
		g.setEdges_Array(9, new int[]{7,3});
		
	*/	
		System.out.println("Data Loaded");
		
		SGraph gReversed= ReverseThisGraph(g);
		
		DFSLoopFirst(gReversed);
		
		// change the graph g according to 
		
		SGraph changeGraph = ChangeGraph(g);
	
		DFSLoopSecond(changeGraph);
		
		
		/*for(int j=0 ; j< leaders.length; j++){
			
			
			System.out.println(leaders[j]);
		}
		*/
			
		
		ArrayList<Integer> uniqueLeaders = new  ArrayList<Integer>();
		
		System.out.println("Completed all LEADERS: ");
		
		for(int locLeaders =0; locLeaders< leaders.length;locLeaders++){
			
				if(leaders[locLeaders] !=0){
					
					int aLeader = leaders[locLeaders];
					int aLeaderTotalCount =0;
					for(int innerL= locLeaders; innerL<leaders.length;innerL++){
						
						
						
						if(aLeader== leaders[innerL]){
							aLeaderTotalCount++;
							leaders[innerL]=0;
						}
						
						
					}
					uniqueLeaders.add(aLeaderTotalCount);
					
					/*System.out.print(aLeader);
					System.out.print("   ");
					System.out.println(aLeaderTotalCount); */
					
					
					
				}	
							
			
		}
		System.out.println("Result Processed");
		
		//Integer[] resultArray = (Integer[]) uniqueLeaders.toArray();
		
		int[] resultArray = new int[uniqueLeaders.size()];
		
		for(int tempIndex=0 ; tempIndex< uniqueLeaders.size(); tempIndex++){
			
			resultArray[tempIndex] = uniqueLeaders.get(tempIndex);
			
		}
		
		Arrays.sort(resultArray);
		
		int resultSize =10;
		
		if(resultArray.length <=10) {
			resultSize = resultArray.length;
		}
		
		for(int resultIndex =resultArray.length-1; resultIndex>= resultArray.length- resultSize ; resultIndex--){
			
			System.out.println(resultArray[resultIndex]); 
			
		}
		
		/*for(int uniqueCount=0; uniqueCount< uniqueLeaders.size();uniqueCount++){
			
			System.out.println(uniqueLeaders.get(uniqueCount));
		}
		*/
		
		

	}
	
	private static SGraph ChangeGraph(SGraph g) {
		// TODO Auto-generated method stub
		
		SGraph gWithChange = new SGraph(TOTAL_VERTICES);
		
		int finishingTimeIndex =1;
		
		for(int i =1; i<= TOTAL_VERTICES;i ++){
			
			int value =  finishingTime[i];
			
			// get gWithChange Edge
			
			ArrayList<Integer> changeArray = gWithChange.getEdges(value);
			
			ArrayList<Integer> originalArray =  g.getEdges(i);
			
			for(int k=0; k< originalArray.size();k++){
				
				int elementAtKthLocation  = originalArray.get(k);
				
				changeArray.add( finishingTime[elementAtKthLocation] );
				
			}
			
			
			//gWithChange.setEdges(i, anArray);
			
			
		}
		return gWithChange;
		
	}

	/*public static void DFS(SGraph g, int startVertex){
		
		
		isVertexExplored[startVertex] = 1;
		
		Stack<Integer> aStack = new Stack<Integer>();
		
		aStack.push(startVertex);
		
		while(aStack.isEmpty()){
			
			int aVertex = aStack.pop();
			
			ArrayList<Integer> allEdges = g.getEdges(aVertex);
			
			for(int i=0; i< allEdges.size();i++){
				
				int aVertexTo = allEdges.get(i);
				if(isVertexExplored[aVertexTo] ==0 ){
					
					isVertexExplored[aVertexTo] =1;
					aStack.push(aVertexTo);					
				}
				
			}
			
			
			
		}
		
	}
	
	*/
	
	public static void DFSRecursive(SGraph g , int startVertex){
		
		isVertexExplored[startVertex] = 1;
		
		
		ArrayList<Integer> allEdges = g.getEdges(startVertex);
		
		for(int i =0 ; i< allEdges.size();i++){
			
			int StoV = allEdges.get(i);
			if(isVertexExplored[StoV] == 0){
				
				DFSRecursive(g, StoV);
			}
			
			
		}
		
		
		
	}
	
	
	static int t;
public static void DFSLoopFirst(SGraph gReversed){
		t=0; 
		
		for(int i= TOTAL_VERTICES ; i>=1;i--){
			
			if(isVertexExploredReversed[i]==0){
				
				DFSRecursiveFirst(gReversed,i);
				
			}
			
		}
		
	}
public static void DFSRecursiveFirst(SGraph g , int startVertex){
		
	isVertexExploredReversed[startVertex] = 1;
		
		
		ArrayList<Integer> allEdges = g.getEdges(startVertex);
		
		for(int i =0 ; i< allEdges.size();i++){
			
			int StoV = allEdges.get(i);
			if(isVertexExploredReversed[StoV] == 0){
				
				DFSRecursiveFirst(g, StoV);
			}
			
			
		}
		
		t++;
		finishingTime[startVertex] = t;
		
		
	}
	


	static int s;
public static void DFSLoopSecond(SGraph g){
		s= -1;
		for(int i = TOTAL_VERTICES ;i >=1; i--){
			
			if(isVertexExplored[i] ==0){
				//s = finishingTime[i] ;
				s = i;
				
			//	DFSRecursiveSecond(g, finishingTime[i]);
				DFSRecursiveSecond(g, i);
				
			}
			
		}
		
	}
	
public static void DFSRecursiveSecond(SGraph g , int startVertex){
		
		isVertexExplored[startVertex] = 1;
		leaders[startVertex] = s;
		
		ArrayList<Integer> allEdges = g.getEdges(startVertex);
		
		for(int i =0 ; i< allEdges.size();i++){
			
			int StoV = allEdges.get(i);
			if(isVertexExplored[StoV] == 0){
				
				DFSRecursiveSecond(g, StoV);
			}
			
			
		}
		
		
		
	}

public static SGraph ReverseThisGraph(SGraph aGraph){
	
	
	SGraph aNewGraph = new SGraph(TOTAL_VERTICES);
	
	for(int i=1; i<= TOTAL_VERTICES;i++){
		
		ArrayList<Integer> locEdges =   aGraph.getEdges(i);
		
		for(int k=0; k<locEdges.size();k++){
			
			ArrayList<Integer> replaceArray =   aNewGraph.getEdges(locEdges.get(k));
			replaceArray.add(i);
		}
		
		
	}
	
	return aNewGraph;
}

}
