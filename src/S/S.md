static methods belong to the class

non-static methods belong to the object

when there is a need to use a non static method inside a static method as the following

        public String ApplianceSimpleDisplayer(){
            return new String("Appliance name: " + this.name + " Appliance end warranty date: " + this.warrantyDay + " " + this.warrantyMonth + " " + this.warrantyYear);
        }
        public static String ApplianceSimpleDisplayer(Appliance appliance){
            return Appliance.ApplianceSimpleDisplayer();
        }

you can't so basically all you do is instantiate an object in the static method
    Appliance appliance1 = new Appliance(appliance) ;
and use it to call the function u prefer like this

        public String ApplianceSimpleDisplayer(){
            return new String("Appliance name: " + this.name + " Appliance end warranty date: " + this.warrantyDay + " " + this.warrantyMonth + " " + this.warrantyYear);
        }
        public static String ApplianceSimpleDisplayer(Appliance appliance){
            Appliance appliance1 = new Appliance(appliance) ;
            return appliance1.ApplianceSimpleDisplayer();
        }

reference: https://stackoverflow.com/questions/659232/it-this-an-example-of-the-single-responsibility-principle
https://www.codeproject.com/Articles/611593/SOLID-Principles-Single-Respons
https://en.wikipedia.org/wiki/SOLID
https://stackoverflow.com/questions/10620022/what-is-an-example-of-the-single-responsibility-principle

