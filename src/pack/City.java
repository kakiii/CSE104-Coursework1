package pack;

public class City {
    //encapsulation!
    private final int num,X,Y;
    // provide members here

    //constructor
    public City(int num,int X,int Y){
         this.X =X;
         this.Y=Y;
         this.num = num;
    }

    @Override
    //overwrite the toString method to test if I write the readFile correctly.
    public String toString(){

        return this.num+" "+this.X+" "+this.Y;
    }


    public double distance(City b) {
		// how to calculate the Euclidean distance between this and b?
        // D(a,b)= √(x1-x2)^2+(y1-y2)^2
        return Math.sqrt(Math.pow(this.X-b.X,2)+Math.pow(this.Y-b.Y,2));
    }
    public int getNum(){
        return this.num;
    }
    public double getX(){
        return this.X;
    }
    public double getY(){
        return this.Y;
    }
}
