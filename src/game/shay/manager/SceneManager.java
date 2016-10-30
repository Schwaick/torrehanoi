package game.shay.manager;

import game.shay.scenes.BaseScene;
import game.shay.scenes.Level1Scene;
import game.shay.scenes.Level2Scene;
import game.shay.scenes.Level3Scene;
import game.shay.scenes.Level4Scene;
import game.shay.scenes.Level5Scene;
import game.shay.scenes.Level6Scene;
import game.shay.scenes.Level7Scene;
import game.shay.scenes.Level8Scene;
import game.shay.scenes.LoadingScene;
import game.shay.scenes.SelectScene;
import game.shay.scenes.SplashScene;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;


public class SceneManager {

	private static SceneManager instance;
	
	//---------------------------------------------
    // SCENES
    //---------------------------------------------
	
	private BaseScene splashScene;
	private BaseScene selectScene;
    private BaseScene gameScene;
    private BaseScene loadingScene; //As propagandas ficaram aqui
    
    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------
    
    private SceneType currentSceneType = SceneType.SCENE_SPLASH;
    private BaseScene currentScene;
    private Engine engine = ResourcesManager.getInstance().engine;
    private float timeLoading  = 1f;
    
    public enum SceneType
    {
        SCENE_SPLASH, SCENE_LOADING, SCENE_SELECT, SCENE_LEVEL1, SCENE_LEVEL2, 
        SCENE_LEVEL3, SCENE_LEVEL4, SCENE_LEVEL5, SCENE_LEVEL6, SCENE_LEVEL7, 
        SCENE_LEVEL8, SCENE_LEVEL9, SCENE_LEVEL10
    }
    
    //---------------------------------------------
    // SINGLETON
    //---------------------------------------------
    
    public static SceneManager getInstance()
    {
    	if(instance == null) {
    		instance = new SceneManager();
    	}
        return instance;
    }
    
    //---------------------------------------------
    // CLASS LOGIC
    //---------------------------------------------
    
    public void setScene(BaseScene scene)
    {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }
    
    public void setScene(SceneType sceneType)
    {
        switch (sceneType)
        {
	        case SCENE_SPLASH:
	            setScene(splashScene);
	            break;
	        case SCENE_SELECT:
	            setScene(selectScene);
	            break;
            case SCENE_LEVEL1:case SCENE_LEVEL2:case SCENE_LEVEL3:case SCENE_LEVEL4:case SCENE_LEVEL5:case SCENE_LEVEL6:case SCENE_LEVEL7:case SCENE_LEVEL8:
                setScene(gameScene);
                break;
            case SCENE_LOADING:
                setScene(loadingScene);
                break;
            default:
                break;
        }
    }
    
    //Inicializa a SplashScene(método será chamado na activity)
    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback, final Engine mEngine)
    {
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        
        mEngine.registerUpdateHandler(new TimerHandler(5f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
            }
        }));
        
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }
    
    //Elimina a SplashScene
    private void disposeSplashScene()
    {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }
    
    public void createSelectScene()
    {
    	ResourcesManager.getInstance().loadSelectResources();
    	ResourcesManager.getInstance().loadGameResources();
    	//ResourcesManager.getInstance().loadRecordResources();
    	
    	selectScene = new SelectScene();
        loadingScene = new LoadingScene();
        SceneManager.getInstance().setScene(selectScene);
        disposeSplashScene();
    }
    
    public void loadSelectScene(final Engine mEngine)
    {
        setScene(loadingScene);
        gameScene.disposeScene();
       
        //ResourcesManager.getInstance().unloadGameTextures();
        mEngine.registerUpdateHandler(new TimerHandler(timeLoading, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                //ResourcesManager.getInstance().loadSelectTextures();
                selectScene = new SelectScene();
                setScene(selectScene);
            }
        }));
    }

    public void loadGameScene(final Engine mEngine, final int index)
    {
        setScene(loadingScene);
        if(gameScene!=null)gameScene.disposeScene();
        
        //ResourcesManager.getInstance().unloadSelectTextures();
        mEngine.registerUpdateHandler(new TimerHandler(timeLoading, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                //ResourcesManager.getInstance().loadGameResources();
                
                switch(index) {
                	case 1:
                		gameScene = new Level1Scene();
                		break;
                	case 2:
                		gameScene = new Level2Scene();
                		break;
                	case 3:
                		gameScene = new Level3Scene();
                		break;
                	case 4:
                		gameScene = new Level4Scene();
                		break;
                	case 5:
                		gameScene = new Level5Scene();
                		break;
                	case 6:
                		gameScene = new Level6Scene();
                		break;
                	case 7:
                		gameScene = new Level7Scene();
                		break;
                	case 8:
                		gameScene = new Level8Scene();
                		break;
                	default:
                		break;
                	
                }
                
                setScene(gameScene);
            }
        }));
    }
    
    //---------------------------------------------
    // GETTERS AND SETTERS
    //---------------------------------------------
    
    public SceneType getCurrentSceneType()
    {
        return currentSceneType;
    }
    
    public BaseScene getCurrentScene()
    {
        return currentScene;
    }
	
}
