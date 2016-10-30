package game.shay.manager;

import game.shay.activities.GameActivity;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.graphics.Color;


public class ResourcesManager {
	
	private static ResourcesManager instance;
	
	public Engine engine;
    public GameActivity activity;
    public Camera camera;
    public VertexBufferObjectManager vbom;
    public Font font;
    
    //Splash
    public ITextureRegion splash_region;
    //Select
    public ITextureRegion select_background_region;
    public ITextureRegion one_region,two_region,three_region,four_region,five_region,six_region,seven_region,eight_region;
    public ITextureRegion lock_region;
    //Game
    public ITextureRegion game_background_region;
    public ITextureRegion level_background_region;
    public ITextureRegion tower_region;
    public ITextureRegion ring1_region,ring2_region,ring3_region,ring4_region,ring5_region,ring6_region,ring7_region,ring8_region,ring9_region,ring10_region;
    public ITiledTextureRegion menu_region;
    public ITiledTextureRegion replay_region;
    public ITiledTextureRegion next_region;
    
    private BitmapTextureAtlas splashTextureAtlas;
    private BuildableBitmapTextureAtlas selectTextureAtlas;
    private BuildableBitmapTextureAtlas gameTextureAtlas;
	
	public static ResourcesManager getInstance() {
		if(instance == null) {
			instance = new ResourcesManager();
		}
        return instance;
    }
	
	public static void prepareManager(Engine engine, GameActivity activity, Camera camera, VertexBufferObjectManager vbom) {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vbom = vbom;
    }
	
    public void loadSelectResources() {
        loadSelectGraphics();
        loadSelectFonts();
    }
    
    public void loadGameResources() {
        loadGameGraphics();
    }
    
    private void loadSelectGraphics() {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/select_lvl/");
    	selectTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
    	
    	select_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectTextureAtlas, activity, "select_background.jpg");
    	one_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectTextureAtlas, activity, "1.png");
    	two_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectTextureAtlas, activity, "2.png");
    	three_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectTextureAtlas, activity, "3.png");
    	four_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectTextureAtlas, activity, "4.png");
    	five_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectTextureAtlas, activity, "5.png");
    	six_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectTextureAtlas, activity, "6.png");
    	seven_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectTextureAtlas, activity, "7.png");
    	eight_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectTextureAtlas, activity, "8.png");
    	lock_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(selectTextureAtlas, activity, "lock.png");
    	
    	try 
    	{
    	    this.selectTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
    	    this.selectTextureAtlas.load();
    	} 
    	catch (final TextureAtlasBuilderException e)
    	{
    		Debug.e(e);
    	}
    }
    
    private void loadSelectFonts() {
    	FontFactory.setAssetBasePath("font/");
        final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "RAVIE.TTF", 44, true, Color.WHITE, 2, Color.BLACK);
        font.load();
    }
    
    private void loadGameGraphics() {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
    	gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
    	game_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "background.jpg");
    	level_background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "lvl_background.png");
    	tower_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "tower.png");
    	ring1_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "ring1.png");
    	ring2_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "ring2.png");
    	ring3_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "ring3.png");
    	ring4_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "ring4.png");
    	ring5_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "ring5.png");
    	ring6_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "ring6.png");
    	ring7_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "ring7.png");
    	ring8_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "ring8.png");
    	ring9_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "ring9.png");
    	ring10_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "ring10.png");
    	
    	menu_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "tiledmenu.png", 2, 1);
    	replay_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "tiledreplay.png", 2, 1);
    	next_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "tilednext.png", 2, 1);
    	
    	try 
    	{
    	    this.gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
    	    this.gameTextureAtlas.load();
    	} 
    	catch (final TextureAtlasBuilderException e)
    	{
    	        Debug.e(e);
    	}
    }
   
    public void loadSplashScreen()
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
    	splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
    	splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.jpg",0,0);
    	splashTextureAtlas.load();
    }
    
    public void unloadSplashScreen() {
    	splashTextureAtlas.unload();
    	splash_region = null;
    }
    
    public void loadSelectTextures() {
    	selectTextureAtlas.load();
    }
    
    public void unloadSelectTextures() {
    	selectTextureAtlas.unload();
    }
    
    public void loadGameTextures() {
    	gameTextureAtlas.load();
    }
    
    public void unloadGameTextures() {
    	gameTextureAtlas.unload();
    }
   
}
