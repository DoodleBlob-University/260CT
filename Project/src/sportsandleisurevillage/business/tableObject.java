package sportsandleisurevillage.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class tableObject {
    private int invoiceid;
    private int customerid;
    private String invoicedate;
    private int items;
    private int totalcost;
    private Boolean requested;
    private Boolean paid;
    private String deadline;

    public int getInvoiceid(){
        return invoiceid;
    }

    public int getCustomerid(){
        return customerid;
    }

    public String getInvoicedate(){
        return invoicedate;
    }

    public int getItems(){
        return items;
    }

    public int getTotalcost(){
        return totalcost;
    }

    public Boolean getRequested(){
        return requested;
    }

    public Boolean getPaid(){
        return paid;
    }

    public String getDeadline(){
        return deadline;
    }

    public tableObject(int invoiceid, int customerid, Date invoicedate, int items, int totalcost, Boolean requested, Boolean paid, Date deadline){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.invoiceid = invoiceid;
        this.customerid = customerid;
        this.invoicedate = dateFormat.format(invoicedate);
        this.items = items;
        this.totalcost = totalcost;
        this.requested = requested;
        this.paid = paid;
        this.deadline = dateFormat.format(deadline);
    }

}
