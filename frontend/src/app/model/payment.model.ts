export class PaymentModel {
    public paymentId: number;
    public month: string;
    public type: string;
    public amount: number;
    public fee: number;
    public paymentDate: Date;
    public studentId: number;
    public name: string;
    public nic: string;
    public year: number;
    public class_type: number;


    constructor(paymentId: number, month: string, type: string, amount: number, fee: number, paymentDate: Date, studentId: number, name: string, nic: string, year: number, class_type: number) {
        this.paymentId = paymentId;
        this.month = month;
        this.type = type;
        this.amount = amount;
        this.fee = fee;
        this.paymentDate = paymentDate;
        this.studentId = studentId;
        this.name = name;
        this.nic = nic;
        this.year = year;
        this.class_type = class_type;
    }
}