package L.L_Valid_Approach;

import L.L_Violation;

public class L_Valid {
    public static void main(String[] args){
        Person newPerson1 = new Person("Paulo", "24") ;
        PersonDisplayInterface person = new Engineer("Peter", "20") ;
        Person newPerson2 = new Doctor("danil", "18") ;
        newPerson1.display() ;
        newPerson2.display() ;
        person.display() ;
    }

    public static class Person implements PersonDisplayInterface{
        public String name ;
        public String age ;
        public Person(String name, String age){
            this.name = name ;
            this.age = age ;
        }
        public void display(){
            System.out.println("name: " + this.name + " age: " + this.age);
        }
    }
    public static class Doctor extends Person implements PersonDisplayInterface{
        public Doctor(String name, String age) {
            super(name, age);
        }

        @Override
        public void display(){
            System.out.println("name: " + this.name + " age: " + this.age + " Job: Doctor");
        }
    }

    public static class Engineer extends Person implements PersonDisplayInterface{
        public Engineer(String name, String age) {
            super(name, age);
        }

        @Override
        public void display(){
            System.out.println("name: " + this.name + " age: " + this.age + " Job: Eng");
        }
    }
}
