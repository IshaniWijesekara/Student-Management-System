export class AttendanceModel {
    public attendanceId: number;
    public inTime: string;
    public outTime: string;
    public date: Date;
    public studentId: string;
    public classType: string;
    public nic: string;
    public month: string;


    constructor(attendanceId: number, inTime: string, outTime: string, date: Date, studentId: string, classType: string, nic: string, month: string) {
        this.attendanceId = attendanceId;
        this.inTime = inTime;
        this.outTime = outTime;
        this.date = date;
        this.studentId = studentId;
        this.classType = classType;
        this.nic = nic;
        this.month = month;
    }
}