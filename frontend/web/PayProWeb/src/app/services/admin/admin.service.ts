import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environement/env';
import {AdminDto} from '../../model/AdminDto';
import {SignupResponseDto} from '../../model/SignupResponseDto';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private  http :HttpClient) { }

  async fetchAdminBySpaceId(id: string) {
    return this.http.get<AdminDto[]>(`${environment.api}/admin/space/${id}`);
  }

  async  createAdmin(admin: AdminDto) {
    return this.http.post<SignupResponseDto>(environment.api + '/auth/signup', admin);
  }
}
