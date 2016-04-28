package sample;

import java.util.HashMap;

/**
 * Created by thejp on 4/27/2016.
 */
public class DB
{
    private HashMap<String,Integer> nameIRelation = new HashMap<>();
    private static DB instance;

    public DB(){
        instance = this;
    }

    public static DB getInstance()
    {
        return instance;
    }
    public boolean addInt(String key, int add){
        if(nameIRelation.containsKey(key)){ //So we can complain. Generic messages will be suppplied
            return false;
        }
        nameIRelation.put(key,add);
        return true;
    }

    public boolean deleteInt(String key){
        if(!nameIRelation.containsKey(key)){ //To compain
            return false;
        }
        nameIRelation.remove(key);
        return true;
    }
    public int getInt(String key){
        if(!nameIRelation.containsKey(key)){
            return -1; //Since this cannot contain - numbers this will suplement as does not exist
        }
        return  nameIRelation.get(key);

    }
    }


