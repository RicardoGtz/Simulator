import java.util.Comparator;

public class MachineFailureComparator implements Comparator<Machine> {

    @Override
    public int compare(Machine o1, Machine o2) {
        return Double.compare(o1.getFailureHour(),o2.getFailureHour());
        /*if(o1.getFailureHour()==o2.getFailureHour())
            return 0;
        else if(o1.getFailureHour()>o2.getFailureHour())
            return 1;
        else
            return -1;*/
    }
}
