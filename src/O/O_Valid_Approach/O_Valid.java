package O.O_Valid_Approach;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class O_Valid {
    public static void main (String[] args){
        PackageShippingMethodsManager packageShippingMethodsManager = new PackageShippingMethodsManager(new ExpressShippingMethodsMethod()) ;

        System.out.println(packageShippingMethodsManager.shippingMethod(new Package("3", "catle")));
    }
    public static class PackageShippingMethodsManager{
        private final ShippingMethodsInterface shippingMethodsInterface ;

        public PackageShippingMethodsManager(ShippingMethodsInterface shippingMethodsInterface){
            this.shippingMethodsInterface = shippingMethodsInterface ;
        }
        public String shippingMethod (Package newPackage){
            LocalDateTime now = LocalDateTime.now();
            String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new String("[ " + time + " ] " + this.shippingMethodsInterface.shippingMethod(newPackage));
        }
    }
}
