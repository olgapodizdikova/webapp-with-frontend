import {Role} from "./role";

export class User {

  id: number;
  login: string;
  password: string;
  email: string;
  name: string;
  datedday: Date = new Date();
  age: number;
  salary: number;
  roles: Role[];

  /*
  get dateddayAsDate(): Date {
    console.log(this.datedday)
    if (this.datedday != null) {
      return new Date(this.datedday);
    } else {
      return null;
    }
  }

  set dateddayAsDate(value: Date) {
    console.log(value+ "setter");
    if(value!=null){
      this.datedday = value.toISOString();
    }
    else{
      this.datedday = null;
    }
  }*/
}
