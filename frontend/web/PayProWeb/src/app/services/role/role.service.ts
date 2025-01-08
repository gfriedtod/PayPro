import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environement/env';
import {async} from 'rxjs';
import {RoleDto} from '../../model/AdminDto';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) { }

  async fetchAll(){
    return  this.http.get<RoleDto[]>(`${environment.api}/role`);
  }
}
