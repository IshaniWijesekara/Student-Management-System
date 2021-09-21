import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {LoginService} from '../../service/login.service';
import {Router} from '@angular/router';
import {UserModel} from '../../model/user.model';

@Component({
    selector: 'app-forgot-password',
    templateUrl: './forgot-password.component.html',
    styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit{
    public loginForm: FormGroup;
    public userMode: UserModel;
    constructor(private loginService: LoginService,
                private router: Router){}

    ngOnInit(): void {
        this.loginForm = new FormGroup({
            userName: new FormControl(null),
            passwordNew: new FormControl(null),
            passwordConfirm: new FormControl(null),
        });
    }

    // reset(){
    //     if (this.loginForm.value.passwordNew === this.loginForm.value.passwordConfirm)  {
    //         const loginDTO = new LoginModel(this.loginForm.value.userName,this.loginForm.value.passwordNew);
    //         this.loginService.forgotPassword(loginDTO).subscribe(
    //             (res: boolean) =>{
    //                 if(res === true) {
    //                     swal.fire('Success', 'Password Reset successfully', 'success');
    //                     this.router.navigate(['/login/']);
    //                 }else {
    //                     swal.fire('Sorry!', 'Something went wrong...!', 'error');
    //                 }
    //             }
    //         );
    //     }else {
    //         swal.fire('Sorry!', 'Passwords are not matched...!', 'error');
    //     }
    // }
}
