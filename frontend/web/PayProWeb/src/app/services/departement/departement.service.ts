import { Injectable } from '@angular/core';
import {UserDto} from '../../model/UserDto';
import {environment} from '../../environement/env';
import {HttpClient} from '@angular/common/http';
import {DepartementDto} from '../../model/DepartementDto';

@Injectable({
  providedIn: 'root'
})
export class DepartementService {

  constructor(private http:HttpClient) { }
  async fetchDepatments(id :string) {
    return this.http.get<DepartementDto[]>(environment.api + `/department/organisation/${id}`);
  }

  async  saveDepartement(departement:DepartementDto) {
    return this.http.post<DepartementDto>(`${environment.api}/department`, departement);
  }
}
