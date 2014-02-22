package com.example.sprouts.Game;

import com.example.sprouts.Controllers.GameController;
import com.example.sprouts.Game.Objects.Node;

import java.util.ArrayList;

/**
 * Created by Keagan on 2/22/14.
 */
public class GameSettings{
    GameController gameController;
    int numNodes;

    public GameSettings(GameController gameController, int numNodes){
        this.gameController = gameController;
        this.numNodes = numNodes;
    }

    public void initializeAll(){
        createNodesInRandomLocations();
    }

    private void createNodesInRandomLocations(){
        for(int i = numNodes;i>0;i--){
            int x = (int)(Math.random()*350);
            int y = (int)(Math.random()*350);
            while(!isValidNodeLocation(x,y)){
                x = (int)(Math.random()*350);
                y = (int)(Math.random()*350);
            }
            gameController.nodes.add(new Node(x,y));
        }
    }

    private boolean isValidNodeLocation(int x, int y){
        if(x < 70 || y < 70) return false;
        for(Node node: gameController.nodes){
            double distance = Math.sqrt(Math.pow(node.x-x,2)+Math.pow(node.y-y,2));
            if(distance < 130){
                return false;
            }
        }
        return true;
    }
}
