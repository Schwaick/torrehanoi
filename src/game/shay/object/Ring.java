package game.shay.object;

import java.util.Stack;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Ring extends Sprite {
	//mWeight é usado para acompanhar o peso da torre; este é um valor inteiro, ou seja, quanto maior o valor, maior o anel.
	private int weight;
    private Stack<Ring> stack; //this represents the stack that this ring belongs to
    private Sprite tower;//the tower on which ring is currently placed.
 
    //peso,posX,posY,TexReg,Vertex
    public Ring(int weight, float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        this.weight = weight;
    }
 
    public int getWeight() {
        return weight;
    }
 
    public Stack<Ring> getStack() {
        return stack;
    }
 
    public void setStack(Stack<Ring> mStack) {
        this.stack = mStack;
    }
 
    public Sprite getTower() {
        return tower;
    }
 
    public void setTower(Sprite tower) {
        this.tower = tower;
    }
}
