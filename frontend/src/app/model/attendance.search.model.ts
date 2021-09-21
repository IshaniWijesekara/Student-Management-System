export class AttendanceSearchModel {
    public attendanceId: number;
    public inTime: string;
    public outTime: string;
    public date: Date;
    public studentId: string;
    public firstName: string;
    public lastName: string;
    public middleName: string;
    public nic: string;
    public classType: string;


    constructor(attendanceId: number, inTime: string, outTime: string, date: Date, studentId: string, firstName: string, lastName: string, middleName: string, nic: string, classType: string) {
        this.attendanceId = attendanceId;
        this.inTime = inTime;
        this.outTime = outTime;
        this.date = date;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.nic = nic;
        this.classType = classType;
    }
}