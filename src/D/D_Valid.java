package D;

public class D_Valid {
    public static void main(String[] args){
     Order order = new Order(150) ;
     CheckoutProcessor checkoutProcessor = new CheckoutProcessor(new PayMobPaymentGateWay()) ;
     checkoutProcessor.checkout(order);
    }
    public static class StripePaymentGateway implements GateWay{
        @Override
        public void charge(int amount){
            System.out.println("Charging through Stripe Gateway");
        }
    }
    public static class PayMobPaymentGateWay implements GateWay{
        @Override
        public void charge(int amount){
            System.out.println("Charging through PayMob Gateway");
        }
    }
    public class FawryPaymentGateWay implements GateWay{
        @Override
        public void charge(int amount){
            System.out.println("Charging through Fawry Gateway");
        }
    }

    public static class Order {
        public int amount ;
        public Order(int amount){
            this.amount = amount ;
        }
        public int getAmount(){
            return this.amount;
        }
    }
    interface GateWay{
        void charge(int orderPrice) ;
    }
    public static class CheckoutProcessor {
        private GateWay gateway;

        public CheckoutProcessor(GateWay gateway) {
            this.gateway = gateway ;
        }

        public void checkout(Order order) {
            gateway.charge(order.getAmount());
        }
    }
}
