package S;

public class S_Violation {
    public static void main (String[] args){
        Appliance appliance = new Appliance("stove", "10-12-2024") ;
        System.out.println(appliance.ApplianceSimpleDisplayer());
        Appliance appliance1 = new Appliance("stove", "10-12-2030") ;
        System.out.println(Appliance.ApplianceSimpleDisplayer(appliance1));
    }
    public static class Appliance{
        public String name = null ;
        public String warrantyDay = null ;
        public String warrantyMonth = null ;
        public String warrantyYear = null ;

        public Appliance (String name, String warrantyDate){
            this.name = name ;
            String[] parts = warrantyDate.split("-");
            this.warrantyDay = parts[0];
            this.warrantyMonth = parts[1];
            this.warrantyYear = parts[2];
        }
        public Appliance (Appliance appliance){
            this.name = appliance.name ;
            this.warrantyMonth = appliance.warrantyMonth;
            this.warrantyYear = appliance.warrantyYear;
            this.warrantyDay = appliance.warrantyDay;
        }

        public String ApplianceSimpleDisplayer(){
            return new String("Appliance name: " + this.name + " Appliance end warranty date: " + this.warrantyDay + " " + this.warrantyMonth + " " + this.warrantyYear);
        }
        public static String ApplianceSimpleDisplayer(Appliance appliance){
            Appliance appliance1 = new Appliance(appliance) ;
            return appliance1.ApplianceSimpleDisplayer();
        }
    }
}
