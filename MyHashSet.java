public class MyHashSet<E>{
    private Object[] hashArray; 
    private DLList<E> hashList; 
    private int size; 

    public MyHashSet(){
        hashArray = new Object[1000000]; 
        hashList = new DLList<E>(); 
        size = 0; 
    }

    public boolean add(E e){
        //get the hashcode and check if it exists in the array location
        int location = e.hashCode(); 

        //if null, set equal to e and return true
        if(hashArray[location] == null){
            hashArray[location] = e; 
            hashList.add(e); 
            size++; 
            return true; 
        }
        //if element already exists, leave it unchanged and return false
        else{
            return false; 
        }
    }

    public void clear(){
        //removes all items from the set/hashArray
        for(int i = 0; i < hashArray.length; i++){
            hashArray[i] = null; 
            hashList.remove(i); 
        }
        size = 0; 
    }

    public boolean contains(Object o){
        //get the hashcode and check if it exists in the array location
        int location = o.hashCode(); 

        //check if location in hashArray is not null and is equal to o
        if(hashArray[location] != null){
            if((hashArray[location]).equals(o)){
                return true;
            }
            else{
                return false; 
            }
        }
        else{
            return false; 
        }
    }

    public boolean remove(Object o){
        //get the hashcode and check if it exists in the array 
        int location = o.hashCode(); 

        //check if the element is the same
        //if all is true, set equal to null and return true if successful

        if(hashArray[location] != null){
            if((hashArray[location]).equals(o)){
                hashArray[location] = null; 
                hashList.remove(o); 
                size--; 
                return true; 
            }
            else{
                return false; 
            }
        }
        else{
            return false; 
        }

    }

    public int size(){
        //return the size
        return size; 
    }

    public DLList<E> toDLList(){
        //return a DLList of all items in the hashSet
        return hashList; 
    }
}
