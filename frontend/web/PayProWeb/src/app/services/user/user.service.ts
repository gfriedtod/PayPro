import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environement/env';
import {UserDto} from '../../model/UserDto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private  http: HttpClient) { }

  async fetchUsers(id:string) {
    return this.http.get<UserDto[]>(environment.api + `/users/organisation/${id}`);
  }

  async postUser(user: UserDto) {
    return this.http.post<UserDto>(environment.api + `/users`, user);
  }
}
