package com.csc780.pacmon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements Runnable {

	private float screenWidth, screenHeight;
	private float dx, dy;
	private SurfaceHolder surfaceHolder;
	private Thread surfaceThread = null;
	boolean isRunning = false;
	
	//pacman data
	private int pX, pY;
	private int direction;
	private int pNormalSpeed;
	private int newDirection;
	boolean canMoveL = true;
	boolean canMoveR = true;
	boolean canMoveU = true;
	boolean canMoveD = true;
	
	//drawing bit map
	private Bitmap ball, wall, door, ghost; // bitmap 
	
	//maze info
	private int[][] mazeArray;
	private Maze maze;
	private int mazeRow, mazeColumn;
	private int blockSize;
	
	public GameSurfaceView(Context context) {
		super(context);
		
		blockSize = 32;  // size of block
		dx = dy = 0;
		maze = new Maze();
		mazeArray = maze.getMaze();
		mazeRow = maze.getMazeRow();
		mazeColumn = maze.getMazeColumn();
		surfaceHolder = getHolder();
		
		pX = pY = 1 * blockSize; // init Pacman
		direction = 0;
		newDirection = 0;
		pNormalSpeed = 2;
	
		//images
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
		door = BitmapFactory.decodeResource(getResources(), R.drawable.ghost_door);
		
		isRunning = true;
		setKeepScreenOn(true);
		
	}
	
	//thread to draw 
	public void run() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(isRunning){
			if(!surfaceHolder.getSurface().isValid())
				continue;
			
			Canvas canvas = surfaceHolder.lockCanvas();

			screenWidth = canvas.getWidth();
			screenHeight = canvas.getHeight();
			canvas.drawRGB(0, 0, 0);
			
			// draw maze
			drawMaze(canvas);
			
			//draw Pacman
			drawPacman(canvas, direction);
			
			surfaceHolder.unlockCanvasAndPost(canvas);
		}
	}

	private void drawPacman(Canvas canvas, int direction) {
		int XmodW, YmodH;
		int boxX, boxY;
		XmodW = pX % blockSize;
		YmodH = pY % blockSize;
		
		// check direction and change if it is allowed
		if(XmodW == 0 && YmodH == 0){
			boxX = pX / blockSize;
			boxY = pY / blockSize;

			if (direction == 4){  // move left allowed if can move to left
		        if (boxX > 0 )
		            if ( mazeArray[boxY][boxX - 1] != 0)
		                newDirection = direction;
			}
			if (direction == 3){   // move right
		        if (boxX < mazeColumn )
		            if (mazeArray[boxY][boxX + 1] != 0) 
		                newDirection = direction;
			}	
			if (direction == 2){ // move down
				if (boxY < mazeRow)
					if (mazeArray[boxY + 1][boxX] != 0 && mazeArray[boxY + 1][boxX] != 3)
						newDirection = direction;
			}
			if (direction == 1) { // move up
		        if (boxY > 0 )
		            if (mazeArray[boxY - 1][boxX] != 0 && mazeArray[boxY - 1][boxX] != 3)
		                newDirection = direction;
			}
		} else {
			if (newDirection != direction){
		        if (((direction == 1) || (direction == 2)) && (XmodW==0) && (YmodH!=0)){
		            newDirection = direction;
		        }
		        if (((direction == 3) || (direction == 4)) && (YmodH==0) && (XmodW!=0) ){
		            newDirection = direction;
		        }
		    }
		}
		
		//evaluate at intersection, collision detection
		if(XmodW == 0 && YmodH == 0){
			boxX = pX / blockSize;
			boxY = pY / blockSize;
		
			canMoveU = canMoveD = canMoveL = canMoveR = true;
			
			if (newDirection == 4){  // move left
                if (boxX > 0 )
                    if ( mazeArray[boxY][boxX - 1] == 0){
                        canMoveL = false;
                    }
			}
			
			if (newDirection == 3){   // move right
                if (boxX < mazeColumn -1 ) 
                    if ( mazeArray[boxY][boxX + 1] == 0) {
                        canMoveR = false;
                    }
			}	
			
			if (newDirection == 2){ // move down
				if (boxY < mazeRow - 1)
					if (mazeArray[boxY + 1][boxX] == 0 || mazeArray[boxY + 1][boxX] == 3){
						canMoveD = false;
				}
			}
			if (newDirection == 1) { // move up
                if (boxY > 0 ) 
					if (mazeArray[boxY - 1][boxX] == 0 || mazeArray[boxY - 1][boxX] == 3){
						canMoveU = false;
					}
			}

		}
		
			if (newDirection == 1 && canMoveU) // up
				pY = pY - pNormalSpeed;
			if (newDirection == 2 && canMoveD) // down
				pY = pY + pNormalSpeed;
			if (newDirection == 3 && canMoveR) // right
				pX = pX + pNormalSpeed;
			if (newDirection == 4 && canMoveL) // left
				pX = pX - pNormalSpeed;
			
		canvas.drawBitmap(ball, pX, pY, null);
		
	}

	public void pause() {
		isRunning = false;
		while(true){
			try {
				surfaceThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		surfaceThread = null;
	}
	
	public void resume() {
		isRunning = true;
		surfaceThread = new Thread(this);
		surfaceThread.start();
		setKeepScreenOn(true);
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub

	}
	
	// draw current maze
	public void drawMaze(Canvas canvas){
		for (int i = 0; i < mazeRow; i++){
			for (int j = 0; j < mazeColumn; j++){
				if (mazeArray[i][j] == 0)
					canvas.drawBitmap(wall, j*blockSize, i*blockSize, null);
				if (mazeArray[i][j] == 3)
					canvas.drawBitmap(door, j*blockSize, i*blockSize, null);
			}
		}
	}
	
	// using accelerometer to set direction of player
	public void setDir(int direction){
		this.direction = direction;
	}
	

}













/*
//check direction
if(XmodW == 0 && YmodH == 0){
	boxX = pX / blockSize;
	boxY = pY / blockSize;

	if (direction == 4){  // move left
        if (boxX > 0 )
            if ( mazeArray[boxY][boxX - 1] == 0)
                newDirection = direction;
	}
	
	if (direction == 3){   // move right
        if (boxX < mazeColumn )
            if ( mazeArray[boxY][boxX + 1] == 0) 
                newDirection = direction;

	}	
	
	if (direction == 2){ // move down
		if (boxY < mazeRow)
			if (mazeArray[boxY + 1][boxX] == 0)
				newDirection = direction;
	}
	if (direction == 1) { // move up
        if (boxY > 0 )
            if (mazeArray[boxY - 1][boxX] == 0)
                newDirection = direction;
	}
} else {
	if (newDirection != direction){
        if (((direction == 1) || (direction == 2)) && (XmodW!=0) && (YmodH==0)){
            newDirection = direction;
        }
        if (((direction == 3) || (direction == 4)) && (YmodH!=0) && (XmodW==0) ){
            newDirection = direction;
        }
    }
}













			if (oldDirection == 1 || oldDirection == 2){
				if (newDirection == 1) // up		
					pY = pY - pNormalSpeed;
				if (newDirection == 2) // down
					pY = pY + pNormalSpeed;
			} else {
				if (oldDirection == 1) // up		
					pY = pY - pNormalSpeed;
				if (oldDirection == 2) // down
					pY = pY + pNormalSpeed;
			}
			
			if (oldDirection == 3 || oldDirection == 4){
				if (newDirection == 3) // right
					pX = pX + pNormalSpeed;
				if (newDirection == 4) // left
					pX = pX - pNormalSpeed;
			} else {
				if (oldDirection == 3) // right
					pX = pX + pNormalSpeed;
				if (oldDirection == 4) // left
					pX = pX - pNormalSpeed;
			}
			
			
			
			
			
			
			
			
					if(XmodW == 0 && YmodH == 0){
			oldDirection = direction;
			boxX = pX / blockSize;
			boxY = pY / blockSize;
		
			if (newDirection == 4){  // move left
                if (boxX > 0 ) {
                    if ( mazeArray[boxY][boxX - 1] == 0){
                        canMove = false;
                    }
                } else 
                    pX = 0;
			}
			
			if (newDirection == 3){   // move right
                if (boxX < mazeColumn -1 ) {
                    if ( mazeArray[boxY][boxX + 1] == 0) {
                        canMove = false;
                    }
                } else 
                    pX = (mazeColumn - 1) * blockSize;
			}	
			
			if (newDirection == 2){ // move down
				if (boxY < mazeRow - 1){
					if (mazeArray[boxY + 1][boxX] == 0){
						canMove = false;
					}
				}
				else {
					pY = 0;
				}
			}
			if (newDirection == 1) { // move up
                if (boxY > 0 ) {
					if (mazeArray[boxY - 1][boxX] == 0) {
						canMove = false;
					}
				} else
					pY = (mazeRow - 1) * blockSize;
			}

		}

		if (canMove) {
			oldX = pX;
			oldY = pY;

				if (oldDirection == 1) // up		
					pY = pY - pNormalSpeed;
				if (oldDirection == 2) // down
					pY = pY + pNormalSpeed;

				if (oldDirection == 3) // right
					pX = pX + pNormalSpeed;
				if (oldDirection == 4) // left
					pX = pX - pNormalSpeed;
			
			
*/


