package be.intecbrussel.sellers;

import be.intecbrussel.eatables.Cone;
import be.intecbrussel.eatables.IceRocket;
import be.intecbrussel.eatables.Magnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IceCreamCarTest {
    private IceCreamCar iceCreamCar;
    Cone.Flavor[] flavor = Cone.Flavor.values();
    Magnum.MagnumType magnumType = Magnum.MagnumType.ALPINENUTS;
    PriceList priceList = new PriceList(2, 2, 5);
    Stock stock = new Stock(2, 2, 10, 2);

    // Assertions assertions;
    {

        this.iceCreamCar = new IceCreamCar(priceList, stock);
    }

    @Test
    public void testInsufficientConeStock()
    {
        stock.setCones(0);  // Set a low value for testing
        stock.setBalls(10);
        // Try to order more cones than available
        Cone orderedCone = iceCreamCar.orderCone(new Cone.Flavor[]{Cone.Flavor.STRAWBERRY, Cone.Flavor.CHOCOLATE});
        Assertions.assertNull(orderedCone);
    }


    @Test
    public void testInsufficientMagnumStock()
    {
        stock.setMagni(0);  // Set a low value for testing
        // Try to order more Magnum than available
        Magnum orderedMagnum = iceCreamCar.orderMagnum(Magnum.MagnumType.WHITECHOCOLATE);
        Assertions.assertNull(orderedMagnum);
    }
    @Test
    public void testInsufficientIceRocketStock()
    {
        stock.setIceRockets(0);  // Set a low value for testing
        // Try to order more IceRocket than available
        IceRocket orderedIceRocket = iceCreamCar.orderIceRocket();
        Assertions.assertNull(orderedIceRocket);
    }

    @Test
    public void testBallsLeftInStockAfterOrder() {


        // Set initial stock to a sufficient value for testing
        stock.setCones(10);
        stock.setBalls(50);

        // Save initial balls stock
        int initialBallsStock = stock.getBalls();

        // Order a cone with two flavors
        Cone.Flavor[] flavorOrdered = new Cone.Flavor[]{Cone.Flavor.VANILLA, Cone.Flavor.CHOCOLATE};
        Cone orderedCone = iceCreamCar.orderCone(flavorOrdered);
        int noOfBallsOrdered = flavorOrdered.length;

        // Verify that the ordered cone is not null
        Assertions.assertNotNull(orderedCone,"Ordered cone should not be null" );

        // Verify that the balls stock has been reduced by the number of flavors in the ordered cone
        int expectedBallsLeft = initialBallsStock - noOfBallsOrdered;
        Assertions.assertEquals(expectedBallsLeft, stock.getBalls(),"Balls stock should be reduced after ordering a cone");
    }


    @Test
    public void testIceRocketLeftInStockAfterOrder() {

        // Set initial stock to a sufficient value for testing
        stock.setIceRockets(10);

        // Save initial balls stock
        int initialIceRocketStock = stock.getIceRockets();
        IceRocket orderedIceRocket = iceCreamCar.orderIceRocket();

        // Verify that the ordered cone is not null
        Assertions.assertNotNull(orderedIceRocket,"Ordered iceRocket should not be null" );

        // Verify that the ice rocket stock has been reduced by 1
        int expectedBallsLeft = initialIceRocketStock - 1 ;
        Assertions.assertEquals(expectedBallsLeft, stock.getIceRockets()," Ice Rocket stock should be reduced after ordering one IceRocket");
    }

    @Test
    public void testMagnumLeftInStockAfterOrder() {


        // Set initial stock to a sufficient value for testing
        stock.setMagni(10);

        // Save initial balls stock
        int initialStockOfMagnum = stock.getMagni();
        Magnum orderedMagnum = iceCreamCar.orderMagnum(Magnum.MagnumType.MILKCHOCOLATE);

        // Verify that the ordered cone is not null
        Assertions.assertNotNull(orderedMagnum,"Ordered Magnum should not be null" );

        // Verify that the Magnum stock has been reduced by 1(only one magnum can be ordered in one time)
        int expectedMagnumLeft = initialStockOfMagnum - 1 ;
        Assertions.assertEquals(expectedMagnumLeft, stock.getMagni()," Magnum stock should be reduced after ordering one IceRocket");
    }

    @Test
    public void testCalculateConeProfit() {

        stock.setCones(10);
        stock.setBalls(50);

        // Save initial profit

        double initialProfit = iceCreamCar.getProfit();
        Cone.Flavor[] flavorOrdered = new Cone.Flavor[]{Cone.Flavor.VANILLA, Cone.Flavor.CHOCOLATE};
        Cone orderedCone = iceCreamCar.orderCone(flavorOrdered);

        int noOfBallsOrdered = flavorOrdered.length;

        // Verify that the ordered cone is not null
        Assertions.assertNotNull(orderedCone,"Ordered Cone should not be null");

        // Verify that the profit has been updated correctly
        double expectedProfit = initialProfit + priceList.getBallPrice()*noOfBallsOrdered * 0.25; // Assuming 2 balls in the ordered cone

        Assertions.assertEquals( expectedProfit, iceCreamCar.getProfit(),"in this case expected profit is 1 and actual profit is also 1" );

    }

    @Test
    public void testCalculateIceRocketProfit() {

        stock.setIceRockets(10);

        // Save initial profit
        double initialProfit = iceCreamCar.getProfit();

        IceRocket orderedIceRocket = iceCreamCar.orderIceRocket();

        // Verify that the ordered cone is not null
        Assertions.assertNotNull(orderedIceRocket,"Ordered IceRocket should not be null");

        // Verify that the profit has been updated correctly
        double expectedProfit = initialProfit + priceList.getRocketPrice() * 0.20;

        Assertions.assertEquals( expectedProfit, iceCreamCar.getProfit(),"in this case expected profit is .40 and actual profit is also .40" );

    }


    @Test
    public void testCalculateMagnumProfit() {

        stock.setMagni(10);

        // Save initial profit
        double initialProfit = iceCreamCar.getProfit();

        // Order a cone with two flavors
        Magnum orderedMagnum = iceCreamCar.orderMagnum(Magnum.MagnumType.MILKCHOCOLATE);

        // Verify that the ordered cone is not null
        Assertions.assertNotNull(orderedMagnum,"Ordered Magnum should not be null");

        // Verify that the profit has been updated correctly
        double expectedProfit = initialProfit + priceList.getMagnumPrice(Magnum.MagnumType.MILKCHOCOLATE) * 0.01;

        Assertions.assertEquals( expectedProfit, iceCreamCar.getProfit(),"in this case expected profit is 0.055 and actual profit is also 0.055" );

    }
}


