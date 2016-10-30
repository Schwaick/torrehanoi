package game.shay.scenes;

import game.shay.database.DBHelper;
import game.shay.extras.LevelCompleteWindow;
import game.shay.manager.SceneManager;
import game.shay.manager.SceneManager.SceneType;
import game.shay.object.Ring;

import java.util.Stack;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.adt.align.HorizontalAlign;


public class Level3Scene extends BaseScene {

	private final static int necessaryMoves = 31;
	private int contador = 0;
	private Ring ring;
	private boolean ring1Touched=false,ring2Touched=false,ring3Touched=false,ring4Touched=false,ring5Touched=false;
	
	private LevelCompleteWindow levelCompleteWindow;
	private HUD gameHUD;
	private Text moves;
	private Text level;
	private Sprite tower1, tower2, tower3;
	private Ring ring1, ring2, ring3, ring4, ring5;
	private Stack<Ring> stack1, stack2, stack3;
	
	@Override
	public void createScene() {
		createBackground();
		createTowers();
		createRings();
		createHUD();
		levelCompleteWindow = new LevelCompleteWindow(activity, engine, this, camera, vbom);
	}

	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadSelectScene(engine);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_LEVEL3;
	}

	@Override
	public void disposeScene() {
		camera.setHUD(null);
		gameHUD = null;
		moves = null;
		level = null;
	}

	private void createHUD()
	{
		gameHUD = new HUD();

		level = new Text(530, 385, resourcesManager.font, "Level: 0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
		level.setAnchorCenter(0, 0);  	    
		level.setText("Level: 3");
		gameHUD.attachChild(level);

		moves = new Text(20, 385, resourcesManager.font, "Moves: 0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
		moves.setAnchorCenter(0, 0);    
		 moves.setText("Moves: 0/" + necessaryMoves);
		gameHUD.attachChild(moves);

		camera.setHUD(gameHUD);
	}
	
	private void createBackground() {
		attachChild(new Sprite(400, 240, resourcesManager.game_background_region, vbom)
	    {
	        @Override
	        protected void preDraw(GLState pGLState, Camera pCamera) 
	        {
	            super.preDraw(pGLState, pCamera);
	            pGLState.enableDither();
	        }
	    });
	}
	
	private void createTowers() {
		tower1 = new Sprite(150, 205, resourcesManager.tower_region, vbom) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				
				if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					ring1Touched = ring2Touched = ring3Touched = ring4Touched = ring5Touched = false;
					changeRingPosition(this, ring);
				}
				
				return true;
			}
		};
		
		tower2 = new Sprite(400, 205, resourcesManager.tower_region, vbom) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					ring1Touched = ring2Touched = ring3Touched = ring4Touched = ring5Touched = false;
					changeRingPosition(this, ring);
				}
				
				return true;
			}
		};
		
		tower3 = new Sprite(650, 205, resourcesManager.tower_region, vbom) {
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					ring1Touched = ring2Touched = ring3Touched = ring4Touched = ring5Touched = false;
					changeRingPosition(this, ring);
				}
				
				return true;
			}
		};
		
		tower1.setTag(1);
		tower2.setTag(2);
		tower3.setTag(3);
		
		attachChild(tower1);
		attachChild(tower2);
		attachChild(tower3);
	}
	
	private void createRings() {

		stack1 = new Stack<Ring>();
		stack2 = new Stack<Ring>();
		stack3 = new Stack<Ring>();
		
		//O 'Y' aumenta de 31 em 31, porque essa é o height das imagens dos aneis
		ring1 = new Ring(10, 150, 65, resourcesManager.ring1_region, vbom) {
			@Override
		    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		        if (((Ring) this.getStack().peek()).getWeight() != this.getWeight())
		            return false;
		      
		        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					selectRing(this);
				}
		        
		        return true;
		    }
		};

		ring2 = new Ring(9, 150, 95, resourcesManager.ring2_region, vbom) {
			@Override
		    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		        if (((Ring) this.getStack().peek()).getWeight() != this.getWeight())
		            return false;
		       
		        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					selectRing(this);
				}
		        
		        return true;
		    }
		};

		ring3 = new Ring(8, 150, 125, resourcesManager.ring3_region, vbom) {
			@Override
		    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		        if (((Ring) this.getStack().peek()).getWeight() != this.getWeight())
		            return false;
		     
		        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					selectRing(this);
				}
		        
		        return true;
		    }
		};

		ring4 = new Ring(7, 150, 155, resourcesManager.ring4_region, vbom) {
			@Override
		    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		        if (((Ring) this.getStack().peek()).getWeight() != this.getWeight())
		            return false;
		      
		        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					selectRing(this);
				}
		        
		        return true;
		    }
		};

		ring5 = new Ring(6, 150, 185, resourcesManager.ring5_region, vbom) {
			@Override
		    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		        if (((Ring) this.getStack().peek()).getWeight() != this.getWeight())
		            return false;
		      
		        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					selectRing(this);
				}
		        
		        return true;
		    }
		};
		
		attachChild(ring1);
		attachChild(ring2);
		attachChild(ring3);
		attachChild(ring4);
		attachChild(ring5);

		stack1.add(ring1);
		stack1.add(ring2);
		stack1.add(ring3);
		stack1.add(ring4);
		stack1.add(ring5);

		ring1.setStack(stack1);
		ring2.setStack(stack1);
		ring3.setStack(stack1);
		ring4.setStack(stack1);
		ring5.setStack(stack1);

		ring1.setTower(tower1);
		ring2.setTower(tower1);
		ring3.setTower(tower1);
		ring4.setTower(tower1);
		ring5.setTower(tower1);

		registerTouchArea(ring1);
		registerTouchArea(ring2);
		registerTouchArea(ring3);
		registerTouchArea(ring4);
		registerTouchArea(ring5);
		setTouchAreaBindingOnActionDownEnabled(true);

	}
	
	private void selectRing(Ring currentRing) {
		if(!ring1Touched && !ring2Touched && !ring3Touched && !ring4Touched && !ring5Touched) {
			currentRing.setY(currentRing.getY() + 20);
			ring = currentRing;
			enableTouches(currentRing.getTower());
			
			if(currentRing == ring1) ring1Touched = true;
			if(currentRing == ring2) ring2Touched = true;
			if(currentRing == ring3) ring3Touched = true;
			if(currentRing == ring4) ring4Touched = true;
			if(currentRing == ring5) ring5Touched = true;
			
		} else {
			if(currentRing == ring1 && ring1Touched) {
				ring1.setY(ring1.getY() - 20);
				ring = null;
				desableTouches();
				ring1Touched = false;
			}
			if(currentRing == ring2 && ring2Touched) {
				ring2.setY(ring2.getY() - 20);
				ring = null;
				desableTouches();
				ring2Touched = false;
			}
			if(currentRing == ring3 && ring3Touched) {
				ring3.setY(ring3.getY() - 20);
				ring = null;
				desableTouches();
				ring3Touched = false;
			}
			if(currentRing == ring4 && ring4Touched) {
				ring4.setY(ring4.getY() - 20);
				ring = null;
				desableTouches();
				ring4Touched = false;
			}
			if(currentRing == ring5 && ring5Touched) {
				ring5.setY(ring5.getY() - 20);
				ring = null;
				desableTouches();
				ring5Touched = false;
			}
		}
	}
	
	private void enableTouches(Sprite currentTower) {
		if(currentTower.getTag() == tower1.getTag()) {
			registerTouchArea(tower2);
			registerTouchArea(tower3);
		} else if(currentTower.getTag() == tower2.getTag()) {
			registerTouchArea(tower1);
			registerTouchArea(tower3);
		} else {
			registerTouchArea(tower1);
			registerTouchArea(tower2);
		}
	}
	
	private void desableTouches() {
		unregisterTouchArea(tower1);
		unregisterTouchArea(tower2);
		unregisterTouchArea(tower3);
	}
	
	private void changeRingPosition(Sprite tower, Ring ring) {
		Stack<Ring> stack = null;
	    //Sprite tower = null;
	    //SE o anel colidir com a torre1 'E' (a pilha dela estiver vazia 'OU' o peso do anel em movimento 	    
	    //		for menor que o peso do	anel no topo da pilha) ,ENTAO joga o anel na torre1
	    if (tower.getTag() == tower1.getTag() && (stack1.size() == 0 ||             
	            ring.getWeight() < ((Ring) stack1.peek()).getWeight())) {
	        stack = stack1;
	        tower = tower1;
	    } else if (tower.getTag() == tower2.getTag() && (stack2.size() == 0 || 
	            ring.getWeight() < ((Ring) stack2.peek()).getWeight())) {
	        stack = stack2;
	        tower = tower2;
	    } else if (tower.getTag() == tower3.getTag() && (stack3.size() == 0 || 
	            ring.getWeight() < ((Ring) stack3.peek()).getWeight())) {
	        stack = stack3;
	        tower = tower3;
	    } else {
	        stack = ring.getStack();
	        tower = ring.getTower();
	    }
	    
	    ring.getStack().remove(ring);

	    if (stack != null && tower !=null && stack.size() == 0) {
	    	// Se não houver rings na torre
	     
	        ring.setPosition(tower.getX(), 
	        		tower.getY() - tower.getHeight()/2 + ring.getHeight()/2 - 0.2f);
	        
	    } else if (stack != null && tower !=null && stack.size() > 0) {
	    	// Se já houver rings na torre
	     
	        ring.setPosition(tower.getX(), ((Ring) stack.peek()).getY() + 
		            ring.getHeight() - 0.5f);
	    }
	    stack.add(ring);
	    ring.setStack(stack);
	    ring.setTower(tower);
	    
	    desableTouches();
	    contador++;
	    setMoves(contador);
	    
	    if(stack3.size() == 5) {
	    	unlockLevel(4);
	    	checkMoves();
	    	
	    	pauseGame();
	    	levelCompleteWindow.display(3, contador, getMoves(), necessaryMoves);
	    }
	}
	
	private void setMoves(int value) {
		moves.setText("Moves: "+value+"/"+necessaryMoves);
	}
	
	private void unlockLevel(int level) {
		DBHelper dbHelper = new DBHelper(activity);
		dbHelper.unlockLevel(level);
	}
	
	private void checkMoves() {
		DBHelper dbHelper = new DBHelper(activity);
		if(contador < dbHelper.getRecord(3)) {
			dbHelper.setRecord(3, contador);
		}
	}
	
	private int getMoves() {
		DBHelper dbHelper = new DBHelper(activity);
		return dbHelper.getRecord(3);
	}
	
	private void pauseGame() {
		unregisterTouchArea(ring1);
		unregisterTouchArea(ring2);
		unregisterTouchArea(ring3);
		unregisterTouchArea(ring4);
		unregisterTouchArea(ring5);
	}
}
