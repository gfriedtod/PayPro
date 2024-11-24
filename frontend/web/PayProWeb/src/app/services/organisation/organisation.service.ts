import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserDto} from '../../model/UserDto';
import {environment} from '../../environement/env';
import {OrganisationDto} from '../../model/OrganisationDto';
import {AdminDto} from '../../model/AdminDto';
import {AdminRowDto} from '../../model/AdminRowDto';

@Injectable({
  providedIn: 'root'
})
export class OrganisationService {

  constructor(private http: HttpClient) { }

 async fetchOrganisationByUserId(user:AdminDto) {
    return this.http.get<OrganisationDto[]>(`${environment.api}/organisation/admin/${user.id}`);
  }

  async saveOrganisation(organisation:OrganisationDto) {
    return this.http.post<OrganisationDto>(`${environment.api}/organisation`, organisation);
  }


}
