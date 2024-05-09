package texures;

public class ModelTexture {
    private int textureID;

    private float shineDamper = 1;
    private float reflectivity = 0;

    public ModelTexture(int id) {
        this.textureID = id;
    }

    public int getID() {
        return textureID;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getRefectivity() {
        return reflectivity;
    }

    public void setRefectivity(float refectivity) {
        this.reflectivity = refectivity;
    }
}
