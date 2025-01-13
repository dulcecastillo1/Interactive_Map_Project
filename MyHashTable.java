public class MyHashTable<K, V>{ 
    private DLList<V>[] table; 
    private MyHashSet<K> keySet; 

    @SuppressWarnings("unchecked")
    public MyHashTable(){
        table = new DLList[10000]; 
        keySet = new MyHashSet<K>(); 
    }

    public void put(K key, V value){
        int location = key.hashCode(); 
        if(table[location] == null){
            table[location] = new DLList<V>(); 
            table[location].add(value); 
            keySet.add(key); 
        }
        else{ 
            table[location].add(value); 
        }

    }

    public DLList<V> get(K key){
        int location = key.hashCode(); 
        return(table[location]); 
    }

    public MyHashSet<K> keySet(){
        return keySet; 
    }

    public String toString(){
        String returnValue = ""; 
        for(int i = 0; i < keySet.size(); i++){
            int location = (keySet.toDLList().get(i).hashCode()); 
            returnValue += (keySet.toDLList().get(i) + " - " + table[location].toString() + "\n"); 
        }

        return returnValue; 
    }

    public void remove(K key, V value){
        int location = key.hashCode();
        if(table[location] != null){
            table[location].remove(value);
            if(table[location].size() == 0){
                table[location] = null;
                keySet.remove(key);
            }
        }
    }

    public void remove(K key){
        int location = key.hashCode();
        if(table[location] != null){
            table[location] = null;
            keySet.remove(key);
        }
    }
}
