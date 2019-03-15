import java.util.HashMap;
import java.util.Map;

public class TerrainCost {

//    protected Dictionary terrainCostDict = new Hashtable();

    private Map<String, Integer> terrainCostDict = new HashMap<String, Integer>();

    public TerrainCost(){
        terrainCostDict.put("~",800);
        terrainCostDict.put("*",200);
        terrainCostDict.put("+",150);
        terrainCostDict.put("X",120);
        terrainCostDict.put("_",100);
        terrainCostDict.put("H",70);
        terrainCostDict.put("T",50);


    }


    public int getCost(String Terrain) throws Exception {
        int lv_cost = terrainCostDict.get(Terrain);
        if (lv_cost < 1) {
            throw new Exception();
        }
        return lv_cost;
    }
}
