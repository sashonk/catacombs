package ru.asocial.games.catacombs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

public class CatacombsGame extends Game implements IGame{

	private Map<String, BaseScreen> screens = new HashMap<>();

	private Graphics.DisplayMode displayMode;

	private ResourcesManager resourcesManager;


	private IMessageService messagingService;

	@Override
	public ResourcesManager getResourcesManager() {
		return resourcesManager;
	}

	@Override
	public void setResourceManager(ResourcesManager resourcesManager) {
		this.resourcesManager = resourcesManager;
	}

	public CatacombsGame(Graphics.DisplayMode displayMode) {
		this.displayMode = displayMode;
	}

	public CatacombsGame() {

	}

	@Override
	public void create() {
		//Gdx.graphics.set
		Gdx.graphics.setWindowedMode(600, 1000);

		//Gdx.graphics.setWindowedMode(1000, 1000);

		setScreen(new SplashScreen(this));

		ServiceLoader<IMessageService> serviceLoader = ServiceLoader.load(IMessageService.class);
		Iterator<IMessageService> messagingServiceIterator = serviceLoader.iterator();
		messagingService = messagingServiceIterator.hasNext() ? messagingServiceIterator.next() : new IMessageService() {
			@Override
			public void writeMessage(String tag, String message) {
				if (!message.contains("MoveEvent")) {
					System.out.println(tag + ":" + message);
				}
			}

			@Override
			public void close() {
				//NOOP
			}
		};


		Preferences keyboard = Gdx.app.getPreferences("keyboard");
		keyboard.putInteger("up", Input.Keys.W);
		keyboard.putInteger("left", Input.Keys.A);
		keyboard.putInteger("down", Input.Keys.S);
		keyboard.putInteger("right", Input.Keys.D);
		keyboard.putInteger("action", Input.Keys.SPACE);

		Preferences neptun = Gdx.app.getPreferences("neptun");
		//neptun.putBoolean("replay", true);

	}

	@Override
	public void dispose() {
		if (resourcesManager != null) {
			resourcesManager.dispose();
		}

		for (Screen screen : screens.values()) {
			screen.dispose();
		}

		messagingService.close();

		Gdx.app.exit();
	}

	@Override
	public void onLoad() {
		Screen exaustedScreen = super.getScreen();
		exaustedScreen.dispose();
		setScreen(new GameScreen(this));
	}

	@Override
	public IMessageService getMessagingService() {
		return messagingService;
	}

}
