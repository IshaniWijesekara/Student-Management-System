export class EmployeeAttendanceModel {
    public attendanceId: number;
    public inTime: string;
    public outTime: string;
    public date: Date;
    public user: string;
    public nic: string;
    public role: string;


    constructor(attendanceId: number, inTime: string, outTime: string, date: Date, user: string, nic: string, role: string) {
        this.attendanceId = attendanceId;
        this.inTime = inTime;
        this.outTime = outTime;
        this.date = date;
        this.user = user;
        this.nic = nic;
        this.role = role;
    }
}