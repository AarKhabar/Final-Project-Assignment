package algonquin.cst2335.finalprojectassignment;

public class CasesHolder {
    String date;
    String TotalCases;
    String TotalFatalities;
    String TotalHospitalizations;
    String TotalVaccinations;
    String TotalRecoveries;

    public CasesHolder(){

    }

    public CasesHolder(String date, String totalCases, String totalFatalities, String totalHospitalizations, String totalVaccinations, String totalRecoveries) {
        this.date = date;
        TotalCases = totalCases;
        TotalFatalities = totalFatalities;
        TotalHospitalizations = totalHospitalizations;
        TotalVaccinations = totalVaccinations;
        TotalRecoveries = totalRecoveries;
    }

    public String getDate() {
        return date;
    }

    public String getTotalCases() {
        return TotalCases;
    }

    public String getTotalFatalities() {
        return TotalFatalities;
    }

    public String getTotalHospitalizations() {
        return TotalHospitalizations;
    }

    public String getTotalVaccinations() {
        return TotalVaccinations;
    }

    public String getTotalRecoveries() {
        return TotalRecoveries;
    }

    @Override
    public boolean equals(Object o) {
        return this.date.equals(((CasesHolder)o).getDate());
    }

}
