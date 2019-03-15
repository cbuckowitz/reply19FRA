import java.util.*;
public class TerrainCost {

    protected Dictionary TerrainCostDict = new Hashtable();

    public TerrainCost(){
        TerrainCostDict.put('~',800);
        TerrainCostDict.put('*',200);
        TerrainCostDict.put('+',150);
        TerrainCostDict.put('X',120);
        TerrainCostDict.put('_',100);
        TerrainCostDict.put('H',70);
        TerrainCostDict.put('T',50);
    }


    public Object getCost(char Terrain) {
        Object Cost = TerrainCostDict.get(Terrain);
        return Cost;
    }
}
