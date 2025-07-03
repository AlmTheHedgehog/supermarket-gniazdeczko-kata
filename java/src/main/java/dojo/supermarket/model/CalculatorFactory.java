package dojo.supermarket.model;

class CalculatorFactory {
    public static DiscountCalculator instanceOf(AppliedOffer appliedOffer) {
        return switch (appliedOffer.getOfferType()) {
            case TWO_FOR_AMOUNT -> new TwoForAmountCalculator(appliedOffer.getArgument());
            case THREE_FOR_TWO -> new ThreeForTwoCalculator();
            case TEN_PERCENT_DISCOUNT -> new TenPercentCalculator(appliedOffer.getArgument());
            case FIVE_FOR_AMOUNT -> new FiveForAmountCalculator(appliedOffer.getArgument());
        };
    }
}
