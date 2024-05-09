package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import shaders.StaticShader;
import texures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();


		RawModel model = OBJLoader. loadOBJModel("dragon", loader);

		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("stallTexture")));

		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setRefectivity(1);


		Entity entity = new Entity(staticModel, new Vector3f(0,-5,-25),0,0,0,1);
		Light light = new Light(new Vector3f(0,-5,-22), new Vector3f(1,1,1));
		Camera camera = new Camera();

		MasterRenderer renderer = new MasterRenderer();

		while(!Display.isCloseRequested()){
			//game logic
			entity.increaseRotation(0,0.1f,0);
			camera.move();
			renderer.processEntity(entity);
			renderer.render(light,camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
