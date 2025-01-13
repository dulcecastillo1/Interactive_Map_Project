public class Location{
    private int row, column; 

    public Location(int row, int column){
        this.row = row; 
        this.column = column; 
    }

    public int getRow(){
        return row; 
    }

    public int getCol(){
        return column; 
    }

    public int hashCode(){
        int hashCode = row*100+column; 
        return hashCode; 
    }


    public boolean equals(Object o){
        Location other = (Location)(o); 
        if(other.getRow() == row && other.getCol() == column){
            return true; 
        }
        else{
            return false; 
        }
    }
}
