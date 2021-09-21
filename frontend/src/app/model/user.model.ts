export class UserModel {
    public userId: number;
    public name: string;
    public contactNo: string;
    public email: string;
    public userName: string;
    public password: string;
    public nic: string;
    public address: string;
    public userRoleCode: string;


    constructor(userId: number, name: string, contactNo: string, email: string, userName: string, password: string, nic: string, address: string, userRoleCode: string) {
        this.userId = userId;
        this.name = name;
        this.contactNo = contactNo;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.nic = nic;
        this.address = address;
        this.userRoleCode = userRoleCode;
    }
}