package L;

public class L_Violation {
    public static void main (String[] args){
        Person person = new Doctor("Peter", "20") ;
        person.display();
    }

    public static class Person{
        public String name ;
        public String age ;
        public Person(String name, String age){
            this.name = name ;
            this.age = age ;
        }
        public void display(){
            System.out.println("Name: " + this.name + " age: " + this.age);
        }
    }
    public static class Doctor extends Person{
        public Doctor(String name, String age) {
            super(name, age);
        }

        @Override
        public void display(){
            throw new RuntimeException("No information to show") ;
        }
    }

    public class Engineer extends Person{
        public Engineer(String name, String age) {
            super(name, age);
        }

        @Override
        public void display(){
            System.out.println(" Job: Engineer");
        }
    }
}
