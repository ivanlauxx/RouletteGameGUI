package client;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.GUI.GameEngineCallbackGUI;
import view.GUI.MainFrame;

public class GUITestClientv2 {
	public static void main(String[] args) {
		GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addPlayer(new SimplePlayer("1", "Come In Spinner", 1000));
		gameEngine.addPlayer(new SimplePlayer("2", "The Loser", 750));
		gameEngine.addPlayer(new SimplePlayer("3", "The Dabbler", 500));
		gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(new MainFrame(gameEngine)));
	}
}
