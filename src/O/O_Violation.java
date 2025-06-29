package O;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class O_Violation {
    public static void main (String[] args){
        Package newPackage = new Package("3", "stove") ;
        newPackage.packageShippingMethods("Drone"); ;
    }

    public static class Package{
        public String itemId = null ;
        public String name = null ;
        public int deliveryDay = 15 ;
        public int deliveryMonth = 10 ;
        public int deliveryYear = 2024 ;
        public Package(String itemId, String name){
            this.itemId = itemId ;
            this.name = name ;
        }

        public String displayPackageInformation(){
            return new String("Item id: " + this.itemId + " Item name: " + this.name + " Estimated Deliver time is:" +
                    this.deliveryDay + " " + this.deliveryMonth + " " + this.deliveryYear) ;
        }


        public void packageShippingMethods(String shippingType) {
            LocalDateTime now = LocalDateTime.now();
            String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            if (shippingType.equalsIgnoreCase("standard")) {
                System.out.println("[" + time + "] Shipping method: Standard Shipping");
            }
            else if (shippingType.equalsIgnoreCase("express")) {
                System.out.println("[" + time + "] Shipping method: Express Shipping");
            }
            else if (shippingType.equalsIgnoreCase("in-store pickup")) {
                System.out.println("[" + time + "] Shipping method: In-Store Pickup");
            }
            else if (shippingType.equalsIgnoreCase("drone")) {
                System.out.println("[" + time + "] Shipping method: Drone Delivery");
            }
            else {
                System.out.println("[" + time + "] Unknown shipping method");
            }
        }

        public String packageDeliveryMonitoring(Package newPackage) {
            // build delivery date
            LocalDate deliveryDate = LocalDate.of(deliveryYear, deliveryMonth, deliveryDay);

            // get current date
            LocalDate today = LocalDate.now();

            // calculate days remaining
            long daysRemaining = ChronoUnit.DAYS.between(today, deliveryDate);

            // format timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // build logger-style string
            if (daysRemaining >= 0) {
                return "[" + timestamp + "] Package delivery is scheduled in " + daysRemaining + " days.";
            } else {
                return "[" + timestamp + "] Delivery date has already passed.";
            }
        }

    }
}
