package S;

public class S_Valid {
    public static void main (String[] args){
        Appliance appliance = new Appliance("stove", "10-12-2024") ;
        ApplianceDisplayer applianceDisplayer = new ApplianceDisplayer(appliance) ;
        System.out.println(applianceDisplayer.ApplianceSimpleDisplayer());
        Appliance appliance1 = new Appliance("dishwasher", "1-05-2024") ;
        System.out.println(ApplianceDisplayer.ApplianceSimpleDisplayer(appliance1));
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
    }
    public static class ApplianceDisplayer{
        Appliance appliance ;

        public ApplianceDisplayer(Appliance appliance){
            this.appliance = appliance ;
        }

        public String ApplianceSimpleDisplayer(){
            return new String("Appliance name: " + appliance.name + " Appliance end warranty date: " + appliance.warrantyDay + " " + appliance.warrantyMonth + " " + appliance.warrantyYear);
        }

        public static String ApplianceSimpleDisplayer(Appliance appliance){
            ApplianceDisplayer applianceDisplayer = new ApplianceDisplayer(appliance) ;
            return applianceDisplayer.ApplianceSimpleDisplayer();
        }
    }
}
