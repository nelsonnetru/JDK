package ru.personacode;



import java.util.Date;

public class Worker {
    private int tabNumber;
    private String phoneNumber;
    private String nameWorker;
    private Date startWork;

    public Worker(int tabNumber, String phoneNumber, String nameWorker, Date startWork) {
        this.tabNumber = tabNumber;
        this.phoneNumber = phoneNumber;
        this.nameWorker = nameWorker;
        this.startWork = startWork;
    }

    public int getTabNumber() {
        return tabNumber;
    }

    public String getNameWorker() {
        return nameWorker;
    }

    private long getDiff() {
        return new Date().getTime() - startWork.getTime();
    }

    public int getStageYear() {
        long diff = getDiff();
        int years = 0;
        int days = (int) (diff / (24 * 60 * 60 * 1000));
        if (days > 365) years = days / 365;
        return years;
    }

    public String getStageByString() {
        String yearsTxt = "";
        long diff = getDiff();
        int days = (int) (diff / (24 * 60 * 60 * 1000));

        if (days > 365) {
            int years = days / 365;
            yearsTxt =  years + " year(s), ";
            days = days - 365 * years;
        }

        String daysTxt = days + " day(s).";
        return yearsTxt + daysTxt;
    }

    @Override
    public String toString() {
        return "phoneNumber='" + phoneNumber + ", nameWorker='" + nameWorker + '\'' +
                ", startWork=" + startWork + ", stage=" + getStageByString() + "\n";
    }
}
