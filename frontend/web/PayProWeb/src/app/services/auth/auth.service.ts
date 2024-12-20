import { Injectable } from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http';
import {AdminDto} from '../../model/AdminDto';
import {environment} from '../../environement/env';
import {LoginRequest, LoginResponse} from '../../model/LoginRequest';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

   async  signUp(admin: AdminDto) {
    return this.http.post(environment.api + '/auth/signup', admin);
  }


  async login(log: LoginRequest) {
    return this.http.post<LoginResponse>(environment.api + '/auth/login', log);
  }
}
