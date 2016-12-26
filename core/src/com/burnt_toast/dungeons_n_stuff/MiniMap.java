package com.burnt_toast.dungeons_n_stuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MiniMap {
	private int[][] textMap;
	boolean visible;
	int[][] visibilityMap;
	TextureRegion seenTilePic;
	TextureRegion unseenTilePic;
	TextureRegion youAreHerePic;
	
	private Vector2 playerXnY;
	
	float midOfScreenX;
	float midOfScreenY;
	
	float areHereTimer;
	int blockSightDistance;//how many blocks can be seen
	float miniTileSize;
	float mapAlphaLevel;
	
	public MiniMap(TextureRegion passSeenPixel, TextureRegion passUnseenPixel, TextureRegion youAreHerePixel,
			float passMidOfScreenX, float passMidOfScreenY){
		seenTilePic = passSeenPixel;
		unseenTilePic = passUnseenPixel;
		youAreHerePic = youAreHerePixel;
		midOfScreenY = passMidOfScreenY;
		midOfScreenX = passMidOfScreenX;
		//if map is below a certain size, 5 pixels is a good size
		miniTileSize = 5;
		playerXnY = new Vector2();
		
	}
	
	public void setMap(int[][] newTextMap){
		textMap = newTextMap;
		visibilityMap = newTextMap;
		}
	public void setMapAlphaLevel(float passAlphaLevel){
		mapAlphaLevel = passAlphaLevel;
	}
	public boolean getIfOnScreen(){
		return visible;
	}
	public void activateBlock(int x, int y){
		playerXnY.x = x;
		playerXnY.y = y;
		if(textMap[x][y+1] == 0 && visibilityMap[x][y+1] != 9){//if UP is not a wall
			for(int i = 0; i < blockSightDistance; i++){
				if(textMap[x][y+1+i] == 0){
					visibilityMap[x][y+1+i] = 9;//set visible
				}
			}
		}//end if UP is available
		if(textMap[x][y-1] == 0 && visibilityMap[x][y-1] != 9){//if DOWN is not a wall or already seen
			for(int i = 0; i < blockSightDistance; i++){
				if(textMap[x][y-1-i] == 0){
					visibilityMap[x][y-1-i] = 9;//set visible
				}
			}
		}//end if DOWN is available
		if(textMap[x+1][y] == 0 && visibilityMap[x+1][y] != 9){//if RIGHT is not a wall or already seen
			for(int i = 0; i < blockSightDistance; i++){
				if(textMap[x+1+i][y] == 0){
					visibilityMap[x+1+i][y] = 9;//set visible
				}
			}
		}//end if RIGHT is available
		if(textMap[x-1][y] == 0 && visibilityMap[x-1][y] != 9){//if LEFT is not a wall or already seen
			for(int i = 0; i < blockSightDistance; i++){
				if(textMap[x-1-i][y] == 0){
					visibilityMap[x-1-i][y] = 9;//set visible
				}
			}
		}//end if LEFT is available
	}//end activate tile method
	
	public void update(){
		
	}
	
	public void draw(SpriteBatch batch){
		for(int i = 0; i < textMap[0].length; i ++){
			for( int k = 0; k < textMap[0].length; k++){//textMap[0] because the width & height is same
				if(visibilityMap[i][k] == 1 || visibilityMap[i][k] == 0){
					batch.draw(this.unseenTilePic,
							midOfScreenX/2 - (textMap[0].length * miniTileSize / 2) + miniTileSize * i,
							midOfScreenY/2 + (textMap[0].length * miniTileSize / 2) - miniTileSize * k,
							miniTileSize, miniTileSize);
				}//end if wall or unseen
				if(visibilityMap[i][k] == 9){//if it's visible
					batch.draw(this.seenTilePic,
							midOfScreenX/2 - (textMap[0].length * miniTileSize / 2) + miniTileSize * i,
							midOfScreenY/2 + (textMap[0].length * miniTileSize / 2) - miniTileSize * k,
							miniTileSize, miniTileSize);
				}
			}
		}
	}
}
