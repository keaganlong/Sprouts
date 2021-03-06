package com.example.sprouts.Controllers;
import com.example.sprouts.Game.Objects.Node;
import java.util.ArrayList;
import com.example.sprouts.Game.Player;
import com.example.sprouts.Game.GameState;

import android.graphics.PathMeasure;
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
    private Node startNode;
    private Node endNode;
    private GameState gameState;

    private GameController()
    {
        gameState = new GameState();
        roots = new ArrayList();
        nodes = new ArrayList();
        players = new ArrayList();
        players.add(new Player(1));
        currentPlayer = players.get(0);
        currentRoot = new Root();
        roots.add(currentRoot);
        //starting node, will get from settings later
        nodes.add(new Node(150,150,currentPlayer));
    }

    public static GameController getInstance(){
        if(instance == null){
            instance = new GameController();
        }
        return instance;
    }

    public void addNode(float x, float y){
        nodes.add(new Node(x,y,currentPlayer));
    }

    public void actionUp(MotionEvent event){
        System.out.println("-=-=-=-=-=-=-=-=-=-=-+_+_+-=_+_+_=_=_=");
        if(gameState.drawingLine){
            currentX = event.getX();
            currentY = event.getY();
            endNode = getNodeAt(currentX,currentY);
            if(endNode != null){
                currentRoot.currentPath.lineTo(event.getX(),event.getY());
                Path newPath = new Path();
                currentRoot.currentPath = newPath;
                //startNode.unClick();
            }
            else{
                currentRoot.clear();
                if(startNode!=null) startNode.unClick();
            }
        }
    }

    public void actionDown(MotionEvent event){
        if(gameState.drawingLine){
            currentX = event.getX();
            currentY = event.getY();
            startNode = getNodeAt(currentX,currentY);
            if(startNode!=null){
                currentRoot.currentPath.moveTo(startNode.x,startNode.y);
                startNode.click();
            }
        }
    }

    public void actionMove(MotionEvent event){
        if(gameState.drawingLine){
            float x = event.getX();
            float y = event.getY();
            float dx = Math.abs(x-currentX);
            float dy = Math.abs(y-currentY);
            if(dx > 4 && dy > 4){
                if(!checkCollision(x,y)){
                    currentRoot.currentPath.quadTo(currentX,currentY,(x+currentX)/2,(y+currentY)/2);
                    currentX = x;
                    currentY = y;
                }
                else{
                    startNode.unClick();
                    currentRoot.clear();
                }
            }
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

    public boolean checkCollision(float x, float y){
        return false;
    }
}
