package I.I_Valid_Approach;

import I.Violation.StoveManagerInterface;

public class I_Valid {

    public static void main (String[] args){

    }

    public static class Stove implements StoveManagerInterface, TimerManagerInterface {
        public String name ;
        public Stove(String name){
            this.name = name ;
        }

        public void incTemp(){
            System.out.println("increase temp with 25 degrees");
        }
        public void decTemp(){
            System.out.println("decrease temp with 25 degrees");

        }

        public void setTime(int time){
            System.out.println("initialize timer for the over with " + time);
        }

        public String timerFinished(){
            // notify the chef that the timer has finished
            // apply some logic to check the time has passed or not yet
            // if time has passed
            return new String("Timer has finished") ;
        }
    }
    public static class Microwave implements TimerManagerInterface{

        @Override
        public void setTime(int time) {
            System.out.println("initialize timer for the over with " + time);
        }

        @Override
        public String timerFinished() {
            return new String("Timer has finished") ;
        }
    }

}
