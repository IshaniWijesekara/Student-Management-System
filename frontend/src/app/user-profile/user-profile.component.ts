import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {UserService} from '../service/user.service';
import {UserModel} from '../model/user.model';
import swal from 'sweetalert2';
import {UserRoleMode} from '../model/user-role.mode';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  public userForm: FormGroup;
  public isValidNIC: boolean = false;
  public isValidUserName: boolean = false;
  public userRoles: UserRoleMode[] = [];
  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit() {
      this.userForm = new FormGroup({
          fullName: new FormControl(null),
          nic: new FormControl(null),
          email: new FormControl(null),
          address: new FormControl(null),
          userRole: new FormControl(null),
          contactNo: new FormControl(null),
          userName: new FormControl(null),
          password: new FormControl(null),
      });

      this.getAllUserRoles();
  }

  getAllUserRoles() {
      this.userService.getAllUserRoles().subscribe(
          (res: UserRoleMode[]) => {
              this.userRoles = res;
          }
      );
  }

    saveProfile(){
      this.checkNIC();
      this.checkUserName();
       const userDTO =  new UserModel(0,this.userForm.value.fullName,
           this.userForm.value.contactNo,this.userForm.value.email,
           this.userForm.value.userName,this.userForm.value.password,this.userForm.value.nic,this.userForm.value.address,
           this.userForm.value.userRole);

       if (this.isValidNIC === false && this.isValidUserName === false) {
           this.userService.saveUserDetails(userDTO).subscribe(
               (res: boolean) => {
                   if(res === true) {
                       swal.fire('Success', 'Details submitted successfully', 'success');
                   }else {
                       swal.fire('Sorry!', 'Something went wrong...!', 'error');
                   }
               }
           )
        }else {
           swal.fire('Sorry!', 'NIC OR UserName Already Exist...!', 'error');
       }
    }

    back() {
        this.router.navigate(['/view-user/']);
    }

    checkNIC() {
      this.userService.checkNICExist(this.userForm.value.nic).subscribe(
          (res:boolean) => {
            if (res === false) {
              this.isValidNIC === true;
            }else {
              this.isValidNIC === false;
            }
          }
      )
    }

    checkUserName() {
      this.userService.checkUserNameExist(this.userForm.value.userName).subscribe(
          (res: boolean) => {
            if (res === false) {
                this.isValidUserName === true;
            }else {
                this.isValidUserName === false;
            }
          }
      )
    }
}
