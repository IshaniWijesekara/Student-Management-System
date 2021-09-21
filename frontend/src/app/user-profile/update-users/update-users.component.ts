import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {UserService} from '../../service/user.service';
import {UserModel} from '../../model/user.model';
import {UserRoleMode} from '../../model/user-role.mode';
import swal from "sweetalert2";

@Component({
  selector: 'app-update-users',
  templateUrl: './update-users.component.html',
  styleUrls: ['./update-users.component.css']
})
export class UpdateUsersComponent implements OnInit {
    public userForm: FormGroup;
    public id: string;
    public type: string;
    public userDTO: UserModel;
    public userRoles: UserRoleMode[] = [];
  constructor(private route: ActivatedRoute,
              private router: Router,
              private userService: UserService) { }

  ngOnInit(): void {
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
      this.route.params.subscribe(
          (param: Params) => {
              this.id = param.id;
              this.type = param.type;
          }
      );
      this.getUserDetails();
      this.getAllUserRoles();
  }
    get FormControls() {
        return this.userForm.controls;
    }

  getUserDetails() {
    this.userService.getUserById(this.id).subscribe(
        (res: UserModel) => {
          this.userDTO = res;
        }
    );
  }

    getAllUserRoles() {
        this.userService.getAllUserRoles().subscribe(
            (res: UserRoleMode[]) => {
                this.userRoles = res;
            }
        );
    }

    back() {
      this.router.navigate(['/view-user/']);
    }

    saveProfile(){
        const userDTO =  new UserModel(0,this.userForm.value.fullName,
            this.userForm.value.contactNo,this.userForm.value.email,
            this.userDTO.userName,this.userDTO.password,this.userForm.value.nic,this.userForm.value.address,
            this.userForm.value.userRole);

            this.userService.updateUserDetails(userDTO).subscribe(
                (res: boolean) => {
                    if(res === true) {
                        swal.fire('Success', 'Details submitted successfully', 'success');
                    }else {
                        swal.fire('Sorry!', 'Something went wrong...!', 'error');
                    }
                }
            )
    }
}
