package game.shay.extras;

import game.shay.manager.ResourcesManager;
import game.shay.manager.SceneManager;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.app.Activity;
import android.widget.Toast;


public class LevelCompleteWindow extends Sprite {
    
	private Activity activity;
	private Engine engine;
	private Scene scene;
	private Camera camera;
	private VertexBufferObjectManager vbom;
	
	
    public LevelCompleteWindow(Activity activity, Engine engine, Scene scene, Camera camera, VertexBufferObjectManager vbom)
    {
        super(0, 0, 650, 400, ResourcesManager.getInstance().level_background_region, vbom);
        this.activity = activity;
        this.engine = engine;
        this.scene = scene;
        this.camera = camera;
        this.vbom = vbom;
    }
    
    private void attachText(int contador, int moves, int necessaryMoves) {
    	attachChild(new Text(325, 270, ResourcesManager.getInstance().font, "Moves: "+contador+"/"+necessaryMoves, vbom));
    	attachChild(new Text(325, 200, ResourcesManager.getInstance().font, "Record: "+moves, vbom));
    }
    
    private void attachButtons(final int level) {
    	TiledSprite menu = new TiledSprite(150, 100, ResourcesManager.getInstance().menu_region, vbom){
    		@Override
    		public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
    			switch(pAreaTouchEvent.getAction()){
    			case TouchEvent.ACTION_DOWN:
    				this.setCurrentTileIndex(1);
    				break;
    			case TouchEvent.ACTION_UP:
    				this.setCurrentTileIndex(0);
    				SceneManager.getInstance().loadSelectScene(engine);
    				break;
    			}
    			return true;
    		}
    	};
    	TiledSprite replay = new TiledSprite(325, 100, ResourcesManager.getInstance().replay_region, vbom){
    		@Override
    		public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
    			switch(pAreaTouchEvent.getAction()){
    			case TouchEvent.ACTION_DOWN:
    				this.setCurrentTileIndex(1);
    				break;
    			case TouchEvent.ACTION_UP:
    				this.setCurrentTileIndex(0);
    				SceneManager.getInstance().loadGameScene(engine, level);
    				break;
    			}
    			return true;
    		}
    	};
    	TiledSprite next = new TiledSprite(500, 100, ResourcesManager.getInstance().next_region, vbom){
    		@Override
    		public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
    			switch(pAreaTouchEvent.getAction()){
    			case TouchEvent.ACTION_DOWN:
    				this.setCurrentTileIndex(1);
    				break;
    			case TouchEvent.ACTION_UP:
    				this.setCurrentTileIndex(0);
    				if(level < 8)
    					SceneManager.getInstance().loadGameScene(engine, level+1);
    				else
    					activity.runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(activity, "Final level!", Toast.LENGTH_SHORT).show();
							}
						});
    				
    				break;
    			}
    			return true;
    		}
    	};
    	
    	scene.registerTouchArea(menu);
    	scene.registerTouchArea(replay);
    	scene.registerTouchArea(next);
    	
    	attachChild(menu);
    	attachChild(replay);
    	attachChild(next);
    }
    
    public void display(int level, int contador, int moves, int necessaryMoves) {
        camera.getHUD().setVisible(false);
        
        setPosition(camera.getCenterX(), camera.getCenterY());
        
        scene.attachChild(this);
        attachText(contador, moves, necessaryMoves);
        attachButtons(level);
    }
 
}
