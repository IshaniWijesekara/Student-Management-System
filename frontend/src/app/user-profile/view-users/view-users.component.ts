import {Component, Input, OnInit} from '@angular/core';
import {UserService} from '../../service/user.service';
import {UserModel} from '../../model/user.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css']
})
export class ViewUsersComponent implements OnInit {
  public userModel: UserModel[] = [];

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe(
        (res: UserModel[]) => {
          this.userModel = res;
        }
    );
  }

    addNewUser() {
        this.router.navigate(['/user-profile/']);
    }

    updateUser(id: number) {
        this.router.navigate(['/update-user/', id, 'update']);
    }

    viewUser(id: number) {
        this.router.navigate(['/update-user/', id, 'view']);
    }
}
