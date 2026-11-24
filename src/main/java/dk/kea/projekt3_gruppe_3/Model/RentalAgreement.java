package dk.kea.projekt3_gruppe_3.Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentalAgreement {
    private int agreementId;
    private Car car;
    private Customer customer;
    private String pickupLocation;
    private LocalDate startDate;
    private LocalDate endDate;
    private double monthlyPrice;
    private SubscriptionType subscriptionType;
    private User createdBy;

    public RentalAgreement(int agreementId, Car car, Customer customer, String pickupLocation,
                           LocalDate startDate, LocalDate endDate, double monthlyPrice,
                           SubscriptionType subscriptionType, User createdBy) {
        this.agreementId = agreementId;
        this.car = car;
        this.customer = customer;
        this.pickupLocation = pickupLocation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyPrice = monthlyPrice;
        this.subscriptionType = subscriptionType;
        this.createdBy = createdBy;
    }

    // Aftalen er aktiv, når dags dato ligger mellem start- og slutdato
    public boolean isActive() {
        LocalDate today = LocalDate.now();
        return !today.isBefore(startDate) && !today.isAfter(endDate);
    }

    // Lejeperioden er slut, når slutdatoen er passeret
    public boolean hasEnded() {
        return LocalDate.now().isAfter(endDate);
    }

    public int getDurationInMonths() {
        return (int) ChronoUnit.MONTHS.between(startDate, endDate);
    }

    public double getTotalPrice() {
        return monthlyPrice * getDurationInMonths();
    }

    public int getAgreementId() {
        return agreementId;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setAgreementId(int agreementId) {
        this.agreementId = agreementId;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setMonthlyPrice(double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}