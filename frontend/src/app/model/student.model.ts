export class StudentDTO {
    public studentId: number;
    public firstName: string;
    public lastName: string;
    public middleName: string;
    public address: string;
    public schoolName: string;
    public gurdianName: string;
    public nic: string;
    public status: string;
    public gurdianContact: string;
    public registrationNo: number;
    public registrationId: string;
    public classes:string;

    public birthday: Date;
    public regDate: Date;
    public email: string;


    constructor(studentId: number, firstName: string, lastName: string, middleName: string, address: string,
         schoolName: string, gurdianName: string, nic: string, status: string, gurdianContact: string, registrationNo: number,
          registrationId: string,classes:string) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.schoolName = schoolName;
        this.gurdianName = gurdianName;
        this.nic = nic;
        this.status = status;
        this.gurdianContact = gurdianContact;
        this.registrationNo = registrationNo;
        this.registrationId = registrationId;
        this.classes = classes;

    }
}
