package O.O_Valid_Approach;

public class DrawnShippingMethodsMethod implements ShippingMethodsInterface {

    @Override
    public String shippingMethod( Package newPackage) {
        return new String("Item Name: " + newPackage.name+ " ItemId: " + newPackage.itemId + " Shipping method: Drone Shipping");
    }
}
