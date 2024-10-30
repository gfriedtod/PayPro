import { Injectable } from '@angular/core';
import {UserDto} from '../../model/UserDto';
import {environment, fakeOrganisation} from '../../environement/env';
import {HttpClient} from '@angular/common/http';
import {DepartementDto} from '../../model/DepartementDto';

@Injectable({
  providedIn: 'root'
})
export class DepartementService {

  constructor(private http:HttpClient) { }
  async fetchDepatments() {
    return this.http.get<DepartementDto[]>(environment.api + `/department/organisation/${fakeOrganisation.id}`);
  }
}
