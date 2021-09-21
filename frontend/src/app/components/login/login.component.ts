import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'app/service/authentication.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
    //user: User = new User();
    failed: boolean;

    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    error = '';

   
   
    constructor( private formBuilder: FormBuilder,
        private authenticationService: AuthenticationService,
        private route: ActivatedRoute,
        private router: Router
        ){}

        ngOnInit() {
          this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        }


 // convenience getter for easy access to form fields
 get f() { return this.loginForm.controls; }

 onSubmit() {
     this.submitted = true;

     // stop here if form is invalid
     if (this.loginForm.invalid) {
         return;
     }

     this.loading = true;
     this.authenticationService.login(this.f.username.value, this.f.password.value)
         .subscribe(
             data => {
              // this.router.navigate(['/main']);
             },
             error => {
                 this.error = error;
                 this.loading = false;
             });
 }
}
