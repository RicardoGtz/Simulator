public class Machine {
    private RandomVars varGen;
    private double meanFailure;
    private double meanRepair;
    private double failureHour;
    private double totalBreakingTime;
    private double repairHour;
    private double totalRepairingTime;

    public Machine(RandomVars rand, double meanReapir, double meanFailure, double currentTime){
        this.varGen=rand;
        this.meanFailure=meanFailure;
        this.meanRepair=meanReapir;
        this.setNewFailureHour(currentTime);
        this.totalBreakingTime=0;
        this.totalRepairingTime=0;
    }

    public void setNewFailureHour(Double currentTime) {
        this.failureHour=this.varGen.getExpo(this.meanFailure)+currentTime;
    }

    public void setNewRepairHour(double entryTime) {
        double aux=this.varGen.getExpo(this.meanRepair);
        this.repairHour=aux+entryTime;
        this.totalBreakingTime+=entryTime-this.failureHour;
        this.totalRepairingTime+=aux;
    }

    public void addTotalBreakingTime(double currentTime) {
        this.totalBreakingTime+=currentTime-this.failureHour;
    }

    public double getFailureHour() {
        return failureHour;
    }

    public double getRepairHour() {
        return repairHour;
    }

    public double getTotalBreakingTime() {
        return totalBreakingTime;
    }

    public double getTotalRepairingTime() {
        return totalRepairingTime;
    }

    @Override
    public String toString(){
        return this.failureHour+"";
    }
}
