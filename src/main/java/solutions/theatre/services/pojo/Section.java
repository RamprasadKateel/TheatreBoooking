package solutions.theatre.services.pojo;
public class Section {
    private int rowNumber;
    private int sectionNumber;
    private int sectionCapacity;
    private int sectionCapacityRemaining;

    public Section(int rowNumber, int sectionNumber, int sectionCapacity){
        this.rowNumber = rowNumber;
        this.sectionNumber = sectionNumber;
        this.sectionCapacity = sectionCapacity;
        this.sectionCapacityRemaining = sectionCapacity;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public int getSectionCapacity() {
        return sectionCapacity;
    }

    public int getSectionCapacityRemaining() {
        return sectionCapacityRemaining;
    }

    public int bookSeats(int numberOfSeats) {
        if(numberOfSeats > sectionCapacityRemaining) {
            return -1;
        }
        this.sectionCapacityRemaining = sectionCapacityRemaining - numberOfSeats;
        return 0;
    }
}
