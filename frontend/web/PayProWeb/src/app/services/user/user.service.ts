import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment, fakeOrganisation} from '../../environement/env';
import {UserDto} from '../../model/UserDto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private  http: HttpClient) { }

  async fetchUsers() {
    return this.http.get<UserDto[]>(environment.api + `/users/organisation/${fakeOrganisation.id}`);
  }
}
