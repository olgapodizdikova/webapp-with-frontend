///<reference path="../../node_modules/primeng/components/common/menuitem.d.ts"/>
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {ModuleWithProviders} from "@angular/compiler/src/core";

import {ButtonModule} from 'primeng/button';
import {CardModule} from 'primeng/card';
import {DataTableModule} from 'primeng/datatable';
import {DialogModule} from 'primeng/dialog';
import {DropdownModule} from 'primeng/dropdown';
import {PanelModule} from 'primeng/panel';
import {GrowlModule} from "primeng/growl";
import {CalendarModule} from 'primeng/calendar';
import {TabMenuModule} from 'primeng/tabmenu';
import {MenubarModule} from 'primeng/menubar';
import {CheckboxModule} from 'primeng/checkbox';
import {SelectButtonModule} from 'primeng/selectbutton';
import {InputTextModule} from 'primeng/inputtext';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {ConfirmationService} from 'primeng/api';

import {Interceptor} from './core/interceptor';
import {AppComponent} from './app.component';
import {UserService} from "./service/user.service";
import {RoleService} from "./service/role.service";
import {UserAddComponent} from './components/user-add/user-add.component';
import {UserListComponent} from './components/user-list/user-list.component';
import {UserLoginComponent} from './components/user-login/user-login.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {UserMenuComponent} from './components/user-menu/user-menu.component';
import {KeyFilterModule} from 'primeng/keyfilter';
import {AuthService} from "./core/auth.service";
import {TokenStorage} from "./core/token.storage";
import { UserWelcomeComponent } from './components/user-welcome/user-welcome.component';

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {path: 'login', component: UserLoginComponent,},
  {path: 'userList', component: UserListComponent},
  {path: 'userAdd', component: UserAddComponent},
  {path: 'userAdd/:id', component: UserAddComponent},
  {path: 'userWelcome', component: UserWelcomeComponent},
  {
    path: 'delete',
    redirectTo: '/userList',
    pathMatch: 'full',
  },
  {
    path: 'logout',
    redirectTo: '/login',
    pathMatch: 'full',
    data: {title: 'LogOut Form'}
  }
];


@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserAddComponent,
    UserLoginComponent,
    UserMenuComponent,
    UserWelcomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ButtonModule,
    CardModule,
    DataTableModule,
    DialogModule,
    DropdownModule,
    PanelModule,
    ReactiveFormsModule,
    GrowlModule,
    CalendarModule,
    TabMenuModule,
    BrowserAnimationsModule,
    MenubarModule,
    HttpClientModule,
    CheckboxModule,
    SelectButtonModule,
    InputTextModule,
    KeyFilterModule,
    ConfirmDialogModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true}
    )
  ],
  providers: [
    AuthService,
    TokenStorage,
    UserService,
    RoleService,
    RoleService,
    ConfirmationService,
    {provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
