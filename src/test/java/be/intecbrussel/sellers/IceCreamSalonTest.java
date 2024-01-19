package be.intecbrussel.sellers;

import be.intecbrussel.eatables.Cone;
import be.intecbrussel.eatables.IceRocket;
import be.intecbrussel.eatables.Magnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IceCreamSalonTest {
    private IceCreamSalon iceCreamSalon;
    Cone.Flavor[] flavor = Cone.Flavor.values();
    Magnum.MagnumType magnumType = Magnum.MagnumType.ALPINENUTS;
    PriceList priceList = new PriceList(2, 2, 5);


    // Assertions assertions;
    {

        this.iceCreamSalon = new IceCreamSalon(priceList);
    }


    @Test
    public void testCalculateConeProfit() {

        // Save initial profit

        double initialProfit = iceCreamSalon.getProfit();
        Cone.Flavor[] flavorOrdered = new Cone.Flavor[]{Cone.Flavor.VANILLA, Cone.Flavor.CHOCOLATE};
        Cone orderedCone = iceCreamSalon.orderCone(flavorOrdered);

        int noOfBallsOrdered = flavorOrdered.length;

        // Verify that the ordered cone is not null
        Assertions.assertNotNull(orderedCone,"Ordered Cone should not be null");

        // Verify that the profit has been updated correctly
        double expectedProfit = initialProfit + priceList.getBallPrice()*noOfBallsOrdered * 0.25; // Assuming 2 balls in the ordered cone
        Assertions.assertEquals( expectedProfit, iceCreamSalon.getProfit(),"in this case expected profit is 1 and actual profit is also 1" );

    }

    @Test
    public void testCalculateIceRocketProfit() {

        // Save initial profit
        double initialProfit = iceCreamSalon.getProfit();

        IceRocket orderedIceRocket = iceCreamSalon.orderIceRocket();

        // Verify that the ordered cone is not null
        Assertions.assertNotNull(orderedIceRocket,"Ordered IceRocket should not be null");

        // Verify that the profit has been updated correctly
        double expectedProfit = initialProfit + priceList.getRocketPrice() * 0.20;

        Assertions.assertEquals( expectedProfit, iceCreamSalon.getProfit(),"in this case expected profit is .40 and actual profit is also .40" );

    }


    @Test
    public void testCalculateMagnumProfit() {

        // Save initial profit
        double initialProfit = iceCreamSalon.getProfit();

        // Order a cone with two flavors
        Magnum orderedMagnum = iceCreamSalon.orderMagnum(Magnum.MagnumType.MILKCHOCOLATE);


        // Verify that the ordered cone is not null
        Assertions.assertNotNull(orderedMagnum,"Ordered Magnum should not be null");

        // Verify that the profit has been updated correctly
        double expectedProfit = initialProfit + priceList.getMagnumPrice(Magnum.MagnumType.MILKCHOCOLATE) * 0.01;

        Assertions.assertEquals( expectedProfit, iceCreamSalon.getProfit(),"in this case expected profit is 0.055 and actual profit is also 0.055" );

    }






}
