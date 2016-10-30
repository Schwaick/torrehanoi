package game.shay.scenes;

import game.shay.database.DBHelper;
import game.shay.manager.SceneManager;
import game.shay.manager.SceneManager.SceneType;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import android.widget.Toast;


public class SelectScene extends BaseScene implements IOnMenuItemClickListener {

	private MenuScene menuChildScene;
	private static final int ONE = 1, TWO = 2, THREE = 3, FOUR = 4, FIVE = 5, SIX = 6, SEVEN = 7, EIGHT = 8;
	private IMenuItem one,two,three,four,five,six,seven,eight;
	private static int lockContador = 9;
	
	@Override
	public void createScene() {
		createBackground();
		createButtons();
	}

	@Override
	public void onBackKeyPressed() {
		System.exit(0);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_SELECT;
	}

	@Override
	public void disposeScene() {
		menuChildScene.dispose();
		menuChildScene = null;
	}

	private void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.select_background_region, vbom)
	    {
	        @Override
	        protected void preDraw(GLState pGLState, Camera pCamera) 
	        {
	            super.preDraw(pGLState, pCamera);
	            pGLState.enableDither();
	        }
	    });
	}
	
	private void createButtons() {
		menuChildScene = new MenuScene(camera);
	    menuChildScene.setPosition(400, 240);
	    
	    one = new ScaleMenuItemDecorator(new SpriteMenuItem(ONE, resourcesManager.one_region, vbom), 1.2f, 1);
	    two = new ScaleMenuItemDecorator(new SpriteMenuItem(TWO, resourcesManager.two_region, vbom), 1.2f, 1);
	    three = new ScaleMenuItemDecorator(new SpriteMenuItem(THREE, resourcesManager.three_region, vbom), 1.2f, 1);
	    four = new ScaleMenuItemDecorator(new SpriteMenuItem(FOUR, resourcesManager.four_region, vbom), 1.2f, 1);
	    five = new ScaleMenuItemDecorator(new SpriteMenuItem(FIVE, resourcesManager.five_region, vbom), 1.2f, 1);
	    six = new ScaleMenuItemDecorator(new SpriteMenuItem(SIX, resourcesManager.six_region, vbom), 1.2f, 1);
	    seven = new ScaleMenuItemDecorator(new SpriteMenuItem(SEVEN, resourcesManager.seven_region, vbom), 1.2f, 1);
	    eight = new ScaleMenuItemDecorator(new SpriteMenuItem(EIGHT, resourcesManager.eight_region, vbom), 1.2f, 1);
	    
	    ArrayList<IMenuItem> buttons = new  ArrayList<IMenuItem>();
	    IMenuItem button = null;
 
	    for(int i=ONE;i<=EIGHT;i++) {
	    	
	    	if(isLevelLocked(i) == false) {
	    		switch(i){case ONE:button=one;break;case TWO:button=two;break;case THREE:button=three;break;case FOUR:button=four;break;case FIVE:button=five;break;case SIX:button=six;break;case SEVEN:button=seven;break;case EIGHT:button=eight;break;}
	    		buttons.add(button);
		    } else {
		    	button = new ScaleMenuItemDecorator(new SpriteMenuItem(lockContador, resourcesManager.lock_region, vbom), 1.2f, 1);
		    	buttons.add(button);
		    	lockContador++;
		    }
	    }
	    
	    for (IMenuItem iMenuItem : buttons) {
	    	menuChildScene.addMenuItem(iMenuItem);
		}
	    
	    menuChildScene.buildAnimations();
	    menuChildScene.setBackgroundEnabled(false);	
	    
	    buttons.get(0).setPosition(-200, 30);
	    buttons.get(1).setPosition(-66, 30);
	    buttons.get(2).setPosition(66, 30);
	    buttons.get(3).setPosition(200, 30);
	    buttons.get(4).setPosition(-200, -80);
	    buttons.get(5).setPosition(-66, -80);
	    buttons.get(6).setPosition(66, -80);
	    buttons.get(7).setPosition(200, -80);
	    
	    menuChildScene.setOnMenuItemClickListener(this);
	    setChildScene(menuChildScene);
	}
	
	private boolean isLevelLocked(int level) {
		DBHelper dbHelper = new DBHelper(activity);
		boolean myReturn = dbHelper.isLevelLocked(level);
		dbHelper.close();
		return myReturn;
	}
	
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
		lockContador = 9;
		switch(pMenuItem.getID()) {
			case ONE:
	            SceneManager.getInstance().loadGameScene(engine, ONE);
				return true;
			case TWO:
				SceneManager.getInstance().loadGameScene(engine, TWO);
				return true;
			case THREE:
				SceneManager.getInstance().loadGameScene(engine, THREE);
				return true;
			case FOUR:
				SceneManager.getInstance().loadGameScene(engine, FOUR);
				return true;
			case FIVE:
				SceneManager.getInstance().loadGameScene(engine, FIVE);
				return true;
			case SIX:
				SceneManager.getInstance().loadGameScene(engine, SIX);
				return true;
			case SEVEN:
				SceneManager.getInstance().loadGameScene(engine, SEVEN);
				return true;
			case EIGHT:
				SceneManager.getInstance().loadGameScene(engine, EIGHT);
				return true;
			default:
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(activity, "Locked! Complete previous level to unlock", Toast.LENGTH_SHORT).show();
					}
				});
				
				return true;
		}
	}
	
}
