package com.csc780.pacmon;

public class Maze {

	private int mazeMaxRow, mazeMaxColumn; //by grids
	
	private static int maze1[][] = {
		{0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0},
		{0,1,1,1,0,1,1,0,0,0,0,0,0,1,1,0,1,1,1,0},
		{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
		{0,0,1,1,1,0,1,1,0,0,0,0,1,1,0,1,1,1,0,0},
		{1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,1},
		{0,0,1,0,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,0},
		{0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0},
		{0,0,0,1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,0,0},
		{1,1,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,1,1},
		{1,1,0,0,0,1,0,1,1,1,1,1,1,0,1,0,0,0,1,1},
		{1,1,0,0,0,1,0,1,1,1,1,1,1,0,1,0,0,0,1,1},
		{1,1,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,1,1},
		{0,0,0,1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,0,0},
		{0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0},
		{0,0,1,0,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,0},
		{1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,1},
		{1,0,1,0,1,0,1,1,0,0,0,0,1,1,0,1,0,1,0,1},
		{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
		{0,1,1,1,0,1,1,0,0,0,0,0,0,1,1,0,1,1,1,0},
		{0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0}
		
	};
	
	static int maze2[][] = {
		{0,0,0,0,1,1,0,0,0,0},
		{1,0,1,0,0,0,0,1,0,1},
		{1,0,1,0,1,1,0,1,0,1},
		{1,0,1,0,1,1,0,1,0,1},
		{0,0,0,0,0,0,0,0,0,0},
		{0,1,0,1,1,1,1,0,1,0},
		{0,1,0,1,0,0,1,0,1,0},
		{0,1,0,1,1,1,1,0,1,0},
		{0,0,0,0,0,0,0,0,0,0},
		{1,0,1,0,1,1,0,1,0,1},
		{1,0,1,0,1,1,0,1,0,1},
		{1,0,1,0,0,0,0,1,0,1},
		{0,0,0,0,1,1,0,0,0,0}
	};
	
	// 0 = wall, 1 = food, 2 = power, 3 = ghost door, 5 = blank, 6 = ghost
	private int maze3[][] = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,1,1,1,0,1,1,1,0,1,1,1,1,0},
			{0,2,0,0,1,0,1,0,1,0,1,0,0,2,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,0,1,0,0,0,0,1,0,0,0,0,1,0,0},  
			{0,0,1,1,1,1,1,2,1,1,1,1,1,0,0},
			{0,0,1,0,0,1,0,0,0,1,0,0,1,0,0},
			{0,1,1,5,5,5,5,5,5,5,5,5,1,1,0},
			{0,1,0,0,5,0,0,0,0,0,5,0,0,1,0},
			{0,1,0,0,5,0,6,6,6,0,5,0,0,1,0},
			{0,1,0,0,5,0,0,3,0,0,5,0,0,1,0},
			{0,1,1,5,5,5,5,5,5,5,5,5,1,1,0},
			{0,0,1,0,0,1,0,0,0,1,0,0,1,0,0},
			{0,0,1,1,1,1,1,2,1,1,1,1,1,0,0},
			{0,0,1,0,0,0,0,1,0,0,0,0,1,0,0},
			{0,0,1,0,1,1,1,1,1,1,1,0,1,0,0},
			{0,1,1,1,1,0,0,1,0,0,1,1,1,1,0},
			{0,1,0,0,0,0,0,1,0,0,0,0,0,1,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,2,0,0,1,0,1,0,1,0,1,0,0,2,0},
			{0,1,1,1,1,0,1,1,1,0,1,1,1,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		};
	
  // t1[0]= 0;
  // t1[1]= 9; // rd (right and down)
  // t1[2]=10; // ld
  // t1[3]= 5; // ru
  // t1[4]= 6; // lu
  // t1[5]=13; // rdu
  // t1[6]=14; // ldu
  // t1[7]=11; // rld
  // t1[8]= 7; // rlu
  // t1[9]=15; // rlud
	
	private int directionMaze[][] = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,0,0,2,0,1,0,2,0,1,0,0,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,5,7,0,8,0,8,7,8,0,8,0,7,4,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},  
			{0,0,5,0,0,7,0,6,0,7,0,0,6,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,1,6,0,7,6,0,0,0,6,7,0,6,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,3,7,0,8,7,0,7,0,7,0,8,0,4,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,5,0,0,8,0,7,0,8,0,0,6,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,9,0,0,2,0,0,0,0},
			{0,1,8,0,4,0,0,0,0,0,3,0,8,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,5,0,0,7,0,7,8,7,0,7,0,0,6,0},
			{0,2,0,0,1,0,0,0,0,0,0,0,0,0,0},
			{0,3,0,0,4,0,3,0,4,0,3,0,0,4,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};
	
	
	
	public Maze() {
		mazeMaxRow = 22;
		mazeMaxColumn = 15;
	}

	public int[][] getMaze() {
		return maze3;
	}
	
	public int getMazeRow(){
		return mazeMaxRow;
	}
	
	public int getMazeColumn(){
		return mazeMaxColumn;
	}
	
	public void clearFood(int x, int y){
		maze3[x][y] = 5;
	}
	
	public int[][] getDirectionMaze(){
		return directionMaze;
	}
}



/*Maze layout
		{"WWWWWWWWWWWWWWW"},
		{"W....W...W....W"},
		{"WoWW.WW.WW.WWoW"},
		{"W.............W"},
		{"WW.WWWW.WWWW.WW"},
		{"WW.....o.....WW"},
		{"WW.WW.WWW.WW.WW"},
		{"W..---------..W"},
		{"W.WW-WWDWW-WW.W"},
		{"W.WW-WGGGW-WW.W"},
		{"W.WW-WWDWW-WW.W"},
		{"W..---------..W"},
		{"WW.WW.WWW.WW.WW"},
		{"WW.....o.....WW"},
		{"WW.WWWW.WWWW.WW"},
		{"WW.W.......W.WW"},
		{"W....WW.WW....W"},
		{"W.WWWWW.WWWWW.W"},
		{"W.............W"},
		{"WoWW.WW.WW.WWoW"},
		{"WWWWWWWWWWWWWWW"}
		
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,1,1,1,1,0,1,1,1,0,1,1,1,1,0},
		{0,2,0,0,1,0,0,1,0,0,1,0,0,2,0},
		{0,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
		{0,0,1,0,0,0,0,1,0,0,0,0,1,0,0},
		{0,0,1,0,0,0,0,1,0,0,0,0,1,0,0},
		{0,0,1,1,1,1,1,2,1,1,1,1,1,0,0},
		{0,0,1,0,0,1,0,0,0,1,0,0,1,0,0},
		{0,1,1,5,5,5,5,5,5,5,5,5,1,1,0},
		{0,1,0,0,5,0,0,3,0,0,5,0,0,1,0},
		{0,1,0,0,5,0,6,6,6,0,5,0,0,1,0},
		{0,1,0,0,5,0,0,0,0,0,5,0,0,1,0},
		{0,1,1,5,5,5,5,5,5,5,5,5,1,1,0},
		{0,0,1,0,0,1,0,0,0,1,0,0,1,0,0},
		{0,0,1,1,1,1,1,2,1,1,1,1,1,0,0},
		{0,0,1,0,0,0,0,1,0,0,0,0,1,0,0},
		{0,0,1,0,1,1,1,1,1,1,1,0,1,0,0},
		{0,1,1,1,1,0,0,1,0,0,1,1,1,1,0},
		{0,1,0,0,0,0,0,1,0,0,0,0,0,1,0},
		{0,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
		{0,2,0,0,1,0,0,1,0,0,1,0,0,2,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
*/
