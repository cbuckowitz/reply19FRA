import java.util.HashMap;
import java.util.Map;

public class TerrainCost {

//    protected Dictionary terrainCostDict = new Hashtable();

    private Map<Character, Integer> terrainCostDict = new HashMap<Character, Integer>();

    public TerrainCost(){
        terrainCostDict.put('~',800);
        terrainCostDict.put('*',200);
        terrainCostDict.put('+',150);
        terrainCostDict.put('X',120);
        terrainCostDict.put('_',100);
        terrainCostDict.put('H',70);
        terrainCostDict.put('T',50);
    }


    public Object getCost(char Terrain) {
        return terrainCostDict.get(Terrain);
    }
}
