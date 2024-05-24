package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import org.lwjgl.opengl.Display;

import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import terrain.Terrain;
import texures.ModelTexture;
import texures.TerrainTexture;
import texures.TerrainTexturePack;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();

		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture,rTexture,gTexture,bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));


		ModelData data = OBJFileLoader.loadOBJ("target");
		RawModel targetModel = loader.loadToVAO(data.getVertices(), data.getTextureCoords(),
				data.getNormals(), data.getIndices());

		RawModel model = OBJLoader. loadOBJModel("target", loader);

		TexturedModel staticModel = new TexturedModel(targetModel, new ModelTexture(loader.loadTexture("white")));

		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setRefectivity(1);


		Entity target = new Entity(staticModel, new Vector3f(100,0,100),0,0,0,2f);
		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));

		Terrain terrain = new Terrain(0,0,loader, texturePack, blendMap);
//		Terrain terrain2 = new Terrain(-1,-1,loader, texturePack, blendMap);

		RawModel bunnyModel = OBJLoader.loadOBJModel("golfBall", loader);
		TexturedModel stanfordBunny = new TexturedModel(bunnyModel, new ModelTexture(loader.loadTexture("white_texture")));
		Player player = new Player(stanfordBunny, new Vector3f(0,0,0),0,0,0,1f, terrain);
		Camera camera = new Camera(player);
		player.setCamera(camera);

		MasterRenderer renderer = new MasterRenderer();

		while(!Display.isCloseRequested()){
//			entity.increaseRotation(0,0.1f,0);
			camera.move();
			player.move();
			player.updateBallPosition();

			renderer.processEntity(player);
			renderer.processTerrain(terrain);
//			renderer.processTerrain(terrain2);
			renderer.processEntity(target);

			renderer.render(light,camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
