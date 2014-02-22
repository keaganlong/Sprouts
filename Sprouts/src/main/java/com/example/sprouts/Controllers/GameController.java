package com.example.sprouts.Controllers;
import com.example.sprouts.Game.Objects.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.example.sprouts.Game.Player;
import com.example.sprouts.Game.GameState;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.graphics.Path;
import com.example.sprouts.Game.Objects.Root;

/**
 * Created by Keagan on 2/17/14.
 */
public class GameController {
    private static GameController instance;
    public ArrayList<Node> nodes;
    public ArrayList<Root> roots;
    public Root currentRoot;
    public ArrayList<Player> players;
    public float currentX;
    public float currentY;
    public Player currentPlayer;
    private ArrayList<Point> closeList = new ArrayList<Point>();
    private ArrayList<Point> transactionScope = new ArrayList<Point>();
    private Node startNode;
    private Node endNode;
    private GameState gameState;
    private int currentPlayerIndex;

    private GameController()
    {
        gameState = GameState.SELECTING_START_NODE;
        roots = new ArrayList<Root>();
        nodes = new ArrayList<Node>();
        players = new ArrayList();
        players.add(new Player(1));
        players.add(new Player(2));
        currentPlayer = players.get(0);
        currentPlayerIndex = 0;
        currentRoot = new Root(currentPlayer);
        roots.add(currentRoot);
        //starting node, will get from settings later
    }

    public ArrayList<Node> getNodesCopy(){
        return (ArrayList<Node>)nodes.clone();
    }

    public ArrayList<Root> getRootsCopy(){
        return (ArrayList<Root>)roots.clone();
    }

    public static void destroyInstance(){
        instance = null;
    }

    public static GameController getInstance(){
        if(instance == null){
            instance = new GameController();
        }
        return instance;
    }

    public void actionUp(MotionEvent event){
        switch(gameState){
            case SELECTING_START_NODE:
                break;
            case DRAWING_LINE:
                currentX = event.getX();
                currentY = event.getY();
                endNode = getNodeAt(currentX,currentY);
                if(endNode != null && endNode.isActive()){
                    currentRoot.lineTo(endNode.x,endNode.y);
                    startNode.unClick();
                    endNode.unClick();
                    endNode.set();
                    for (Point p : transactionScope)
                        closeList.add(p);
                    gameState = GameState.PLACING_NODE;
                }
                else{
                    currentRoot.clear();
                    if(startNode!=null){
                        startNode.unClick();
                        startNode.unSet();
                    }
                    gameState = GameState.SELECTING_START_NODE;
                }
                break;
            case PLACING_NODE:
                break;
            case TURN_COMPLETE:
                break;
        }
    }

    public void actionDown(MotionEvent event){
        currentX = event.getX();
        currentY = event.getY();
        switch(gameState){
            case SELECTING_START_NODE:
                startNode = getNodeAt(currentX,currentY);
                if(startNode!=null && startNode.isActive()){
                    currentRoot.startAt(startNode.x, startNode.y);
                    startNode.set();
                    startNode.click();
                    gameState = GameState.DRAWING_LINE;
                }
                break;
            case DRAWING_LINE:
                break;
            case PLACING_NODE:
                if(getNodeAt(currentX, currentY)==null){
                    nodes.add(new Node((int)currentX,(int)currentY,2));
                    gameState = GameState.TURN_COMPLETE;
                    gameState = GameState.SELECTING_START_NODE;
                    currentPlayerIndex++;
                    currentPlayer = players.get(currentPlayerIndex%players.size());
                    Root newRoot = new Root(currentPlayer);
                    roots.add(newRoot);
                    currentRoot = newRoot;
                }
                break;
            case TURN_COMPLETE:
                break;
        }
    }

    public void actionMove(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        switch(gameState){
            case SELECTING_START_NODE:
                break;
            case DRAWING_LINE:
                float dx = Math.abs(x-currentX);
                float dy = Math.abs(y-currentY);
                if(dx > 3 && dy > 3){
                    if(!hasCollision(Bitmap bitMap)){
                        currentRoot.lineTo(x,y);
                        currentX = x;
                        currentY = y;
                    }
                    else{
                        transactionScope.clear();
                        gameState = GameState.SELECTING_START_NODE;
                    }
                }
                break;
            case PLACING_NODE:
                break;
            case TURN_COMPLETE:
                break;
        }
    }

    public Node getNodeAt(float x, float y){
        for(Node node: nodes){
            if(Math.sqrt(Math.pow(x-node.x,2.0)+Math.pow(y-node.y,2.0))<=node.radius+10){
                return node;
            }
        }
        return null;
    }

    public void updateRoots(Bitmap bitmap){
        //System.out.println(Color.red(bitmap.getPixel(250,200))+" "+Color.blue(bitmap.getPixel(200,200))+" "+Color.green(bitmap.getPixel(200,200)));
    }

    public boolean hasCollision(Bitmap bitMap){
        for (int i = 0; i < bitMap.getWidth(); i++){
            for (int j = 0; j < bitMap.getHeight(); j++){
                if(bitMap.getPixel(i, j) == Color.GREEN){
                    Point point = new Point(i, j);
                    if (closeList.contains(point)){
                        //INTERSECTION!!
                        return true;
                    }
                    else{
                        //ALL IS GOOD
                        transactionScope.add(point);
                    }
                }
            }
        }
        return true;
    }

    public boolean checkPointOnCurrentRoot(float x, float y){
        return true;
    }

}
