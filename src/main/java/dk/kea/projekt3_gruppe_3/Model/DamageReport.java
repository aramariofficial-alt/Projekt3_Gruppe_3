package dk.kea.projekt3_gruppe_3.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DamageReport {
    private int reportId;
    private RentalAgreement rentalAgreement;
    private LocalDate reportDate;
    private List<Damage> damages;
    private User createdBy;

    public DamageReport(int reportId, RentalAgreement rentalAgreement, LocalDate reportDate, User createdBy) {
        this.reportId = reportId;
        this.rentalAgreement = rentalAgreement;
        this.reportDate = reportDate;
        this.createdBy = createdBy;
        this.damages = new ArrayList<>();
    }

    public void addDamage(Damage damage) {
        damages.add(damage);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Damage damage : damages) {
            total += damage.getPrice();
        }
        return total;
    }

    public int getReportId() {
        return reportId;
    }

    public RentalAgreement getRentalAgreement() {
        return rentalAgreement;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public List<Damage> getDamages() {
        return damages;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public void setRentalAgreement(RentalAgreement rentalAgreement) {
        this.rentalAgreement = rentalAgreement;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public void setDamages(List<Damage> damages) {
        this.damages = damages;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
