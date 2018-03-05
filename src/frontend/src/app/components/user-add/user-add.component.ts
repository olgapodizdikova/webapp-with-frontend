///<reference path="../../../../node_modules/@angular/forms/src/form_builder.d.ts"/>
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from "../../service/user.service";
import {User} from "../../domain/user";
import {ConfirmationService, Message} from "primeng/api";
import {Role} from "../../domain/role";
import {RoleService} from "../../service/role.service";

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css'],
  providers: [ConfirmationService]
})
export class UserAddComponent implements OnInit {

  submittedAdd: boolean = false;

  submittedEdit: boolean = false;

  roles: Role[];

  selectedRoles: string[] = [];

  user: User = new User();

  msgs: Message[] = [];

  constructor(private userService: UserService,
              private roleService: RoleService,
              private route: ActivatedRoute,
              private router: Router,
              private confirmationService: ConfirmationService) {
  }


  ngOnInit() {
    this.getRole();
    this.getUser();
  }

  getUser() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id != null) {
      this.userService.findById(+id).subscribe(data => {
          this.user = data as User;
          this.user.datedday = new Date(this.user.datedday);
          this.selectedRoles = this.user.roles.map(role => role.role);
        },
        err => {
          console.log(err);
        });
    }
  }

  getRole() {
    this.roleService.findAll()
      .subscribe(data => {
          this.roles = data
        },
        err => {
          console.log(err);
        });
  }

  getRoleByName(name: string): Role {
    return this.roles.filter((role) => {
      return role.role == name
    })[0];
  }

  setBirthdate(d: Date) {
    d.setTime(d.getTime() + (new Date().getTimezoneOffset() * 60 * 1000));
    this.user.datedday = d;
  }

  confirm() {
    this.confirmationService.confirm({
      message: 'Сохранить данные?',
      accept: () => {
        this.save();
      },
      reject: () => {
        this.msgs = [{severity:'info', summary:'Rejected', detail:'Сохранение отменено.'}];
      }
    });
  }

  save() {
    this.user.roles = this.selectedRoles.map(name => this.getRoleByName(name));
    if (this.user.id != null) {
      console.log(this.user.datedday);
      this.userService.update(this.user, this.user.id)
        .subscribe(data => {
            this.user = null;
            this.router.navigate(['userList']);
          },
          err => {
            this.submittedEdit = true;
            console.log(err);
          });
    } else {
      this.userService.save(this.user)
        .subscribe(data => {
            alert("Новый пользователь добавлен.");
            this.user = null;
            this.router.navigate(['userList']);
          },
          err => {
            this.submittedAdd = true;
            console.log(err);
          });
    }
    }


  cancel() {
    this.router.navigate(['userList']);
  }

  get errorAddUser() {
    return JSON.stringify("Error add User!!!");
  }

  get errorEditUser() {
    return JSON.stringify("Error add User!!!");
  }
}
