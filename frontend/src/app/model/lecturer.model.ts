export class LecturerModel {
    public id: number;
    public name: string;
    public contactNo: string;
    public email: string;
    public nic: string;
    public address: string;
    public subject: string;


    constructor(id: number, name: string, contactNo: string, email: string, nic: string, address: string, subject: string) {
        this.id = id;
        this.name = name;
        this.contactNo = contactNo;
        this.email = email;
        this.nic = nic;
        this.address = address;
        this.subject = subject;
    }
}