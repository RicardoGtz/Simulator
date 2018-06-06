import java.util.Comparator;

public class MachineRepairComparator implements Comparator<Machine> {

    @Override
    public int compare(Machine o1, Machine o2) {
        return Double.compare(o1.getRepairHour(),o2.getRepairHour());
        /*if(o1.getRepairHour()==o2.getRepairHour())
            return 0;
        else if(o1.getRepairHour()>o2.getRepairHour())
            return 1;
        else
            return -1;*/
    }
}
