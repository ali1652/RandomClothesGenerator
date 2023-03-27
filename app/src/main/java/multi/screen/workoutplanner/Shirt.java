package multi.screen.workoutplanner;

public class Shirt {
    String shirtName,id;

    public Shirt() {
    }

    public Shirt(String shirtName, String id) {
        this.shirtName = shirtName;
        this.id = id;
    }

    public Shirt(String shirtName) {
        this.shirtName = shirtName;

    }

    public String getShirtName() {
        return shirtName;
    }

    public void setShirtName(String shirtName) {
        this.shirtName = shirtName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


