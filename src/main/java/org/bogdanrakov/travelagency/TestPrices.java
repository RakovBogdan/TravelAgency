package org.bogdanrakov.travelagency;

import org.bogdanrakov.travelagency.model.entity.Client;
import org.bogdanrakov.travelagency.model.entity.Order;
import org.bogdanrakov.travelagency.model.entity.Tour;

public class TestPrices {

    public static void main(String[] args) {

        Tour tour = new Tour.Builder()
                .price(12376)
                .discount(34)
                .build();

        Client client = new Client.Builder()
                .discount(34)
                .build();

        Order order = new Order.Builder()
                .toursAmount(2)
                .build();

        System.out.println(2 * 34 / 100.);

        int tourPrice = tour.getPrice();
        System.out.println(tour.getDiscount() / (100. * 100.));
        double tourDiscount = (tourPrice * tour.getDiscount() / (100. * 100.));
        System.out.println(tourDiscount);
        double clientDiscount = (tourPrice * client.getDiscount() / (100. * 100.));
        System.out.println(clientDiscount);

        double amountToPay = (tourPrice - tourDiscount - clientDiscount ) *
                order.getToursAmount();

        int result = (int)Math.round(amountToPay);
        System.out.println(amountToPay);
        System.out.println(result);
    }
}
