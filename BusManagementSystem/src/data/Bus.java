package data;

public class Bus {
    private int busNum;
    private String licensePlate;
    private String type;
    private int numOfSeat;
    
    public Bus() {}
    
    public Bus(int busNum, String licensePlate, String type, int numOfSeat) {
        setBusNum(busNum);
        setLicensePlate(licensePlate);
        setType(type);
        setNumOfSeat(numOfSeat);
    }

    public int getBusNum() {
        return busNum;
    }

    public void setBusNum(int busNum) {
        this.busNum = busNum;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumOfSeat() {
        return numOfSeat;
    }

    public void setNumOfSeat(int numOfSeat) {
        this.numOfSeat = numOfSeat;
    }
    
    
}